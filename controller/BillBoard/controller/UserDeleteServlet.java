package BillBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BillBoard.service.UserService;

@WebServlet(urlPatterns = {"/deleteUser"})
public class UserDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		int userId = Integer.parseInt(request.getParameter("editUserId"));
		new UserService().delete(userId);
		response.sendRedirect("management");
	}
}
