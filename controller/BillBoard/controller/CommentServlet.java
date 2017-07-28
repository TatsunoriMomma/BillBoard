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

import BillBoard.beans.Comment;
import BillBoard.beans.User;
import BillBoard.service.CommentService;

@WebServlet(urlPatterns = { "/comment"})
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		Comment comment = new Comment();
		User user = new User();
		user = (User) session.getAttribute("loginUser");
		comment.setText(request.getParameter("text"));
		comment.setUser_id(user.getId());
		comment.setContribution_id(Integer.parseInt(request.getParameter("contributionId")));

		if(isValid(request,messages) == true){
			new CommentService().register(comment);
			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
		}
	}
	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String text = request.getParameter("text");

		if (StringUtils.isBlank(text) == true) {
			messages.add("コメントを入力してください");
		}
		if (text.length() > 500) {
			messages.add("コメントは500字以下です");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
