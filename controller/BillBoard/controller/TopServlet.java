package BillBoard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BillBoard.beans.UserComment;
import BillBoard.beans.UserContribution;
import BillBoard.service.ContributionService;
import BillBoard.service.UserCommentService;
import BillBoard.service.UserContributionService;

@WebServlet(urlPatterns = { "/index.jsp"})
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<UserContribution> contributions = new ArrayList<UserContribution>();
		List<UserComment> comments = new ArrayList<UserComment>();
		List<String> categories = new ArrayList<String>();

		UserContributionService userContributionService = new UserContributionService();
		contributions = userContributionService.getAllUserContribution();

		UserCommentService userCommentService = new UserCommentService();
		comments = userCommentService.getAllUserComment();

		ContributionService contributionService = new ContributionService();
		categories = contributionService.getAllCategory();

		request.setAttribute("contributions", contributions);
		request.setAttribute("comments", comments);
		request.setAttribute("categories", categories);

		request.getRequestDispatcher("/home.jsp").forward(request,response);
	}
}
