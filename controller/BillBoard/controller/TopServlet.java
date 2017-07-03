package BillBoard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BillBoard.beans.Comment;
import BillBoard.beans.Contribution;
import BillBoard.service.CommentService;
import BillBoard.service.ContributionService;

@WebServlet(urlPatterns = { "/index.jsp"})
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<Contribution> contributions = new ArrayList<Contribution>();
		List<Comment> comments = new ArrayList<Comment>();

		ContributionService contributionService = new ContributionService();
		contributions = contributionService.getAllContribution();
		CommentService commentService = new CommentService();
		comments = commentService.getAllComment();
		request.setAttribute("contributions", contributions);
		request.setAttribute("comments", comments);

		request.getRequestDispatcher("/home.jsp").forward(request,response);
	}
}
