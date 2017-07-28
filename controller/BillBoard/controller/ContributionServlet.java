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
		List<String> categories = new ArrayList<String>();
		Contribution contribution = new Contribution();
		ContributionService contributionService = new ContributionService();
		categories = contributionService.getAllCategory();
		HttpSession session = request.getSession();

		User user = new User();
		user = (User) session.getAttribute("loginUser");
		contribution.setSubject(request.getParameter("subject"));
		contribution.setText(request.getParameter("text"));
		contribution.setUser_id(user.getId());

		String selectCategory = request.getParameter("selectCategory");
		String newCategory = request.getParameter("newCategory");
		if (!selectCategory.isEmpty() && newCategory.isEmpty()) {
			contribution.setCategory(selectCategory);
		} else if(selectCategory.isEmpty() && !newCategory.isEmpty()) {
			contribution.setCategory(newCategory);
		}

		if(isValid(request, messages) == true) {

			new ContributionService().register(contribution);
			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);

			request.setAttribute("selectCategory", selectCategory);
			request.setAttribute("newCategory", newCategory);
			request.setAttribute("contribution", contribution);
			request.setAttribute("categories", categories);
			request.getRequestDispatcher("contribution.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String subject = request.getParameter("subject");
		String text = request.getParameter("text");
		String selectCategory = request.getParameter("selectCategory");
		String newCategory = request.getParameter("newCategory");

		if (StringUtils.isBlank(subject) == true) {
			messages.add("件名を入力してください");
		}
		if (subject.length() > 30){
			messages.add("件名は30字以下です");
		}
		if (StringUtils.isBlank(text) == true) {
			messages.add("本文を入力してください");
		}
		if (text.length() > 1000){
			messages.add("本文は1000字以下です");
		}
		if (selectCategory.isEmpty() && newCategory.isEmpty()) {
			messages.add("カテゴリーを入力、または選択してください");
		}
		if (!selectCategory.isEmpty() && !newCategory.isEmpty()) {
			messages.add("カテゴリーは入力、または選択のどちらかひとつしかできません");
		}
		if (newCategory.length() > 10){
			messages.add("カテゴリーは10字以下です");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
