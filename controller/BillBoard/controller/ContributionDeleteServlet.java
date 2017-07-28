package BillBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BillBoard.service.ContributionService;

@WebServlet(urlPatterns = { "/deleteContribution"})
public class ContributionDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		int contributionId = Integer.parseInt(request.getParameter("contributionId"));
		new ContributionService().delete(contributionId);
		response.sendRedirect("./");

	}

}
