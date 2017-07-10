package BillBoard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import BillBoard.beans.Contribution;
import BillBoard.beans.User;
import BillBoard.service.ContributionService;

@WebServlet(urlPatterns = { "/contribution"})
public class ContributionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> categories = new ArrayList<String>();
		ContributionService contributionService = new ContributionService();
		categories = contributionService.getAllCategory();

		request.setAttribute("categories", categories);
		request.getRequestDispatcher("contribution.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		Contribution contribution = new Contribution();

		List<String> categories = new ArrayList<String>();
		ContributionService contributionService = new ContributionService();
		categories = contributionService.getAllCategory();

		User user = new User();
		user = (User) session.getAttribute("loginUser");
		contribution.setSubject(request.getParameter("subject"));
		contribution.setText(request.getParameter("text"));
		contribution.setCategory(request.getParameter("category"));
		contribution.setUser_id(user.getId());

		if(isValid(request, messages) == true) {

			new ContributionService().register(contribution);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);

			request.setAttribute("contribution", contribution);
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("contribution.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String subject = request.getParameter("subject");
		String text = request.getParameter("text");
		String category = request.getParameter("category");

		if (StringUtils.isEmpty(subject) == true) {
			messages.add("件名を入力してください");
		}
		if (StringUtils.isEmpty(text) == true) {
			messages.add("本文を入力してください");
		}
		if (StringUtils.isEmpty(category) == true) {
			messages.add("カテゴリーを入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
