package BillBoard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BillBoard.beans.Branch;
import BillBoard.beans.Department;
import BillBoard.beans.User;
import BillBoard.service.BranchService;
import BillBoard.service.DepartmentService;
import BillBoard.service.UserService;

@WebServlet(urlPatterns = { "/isWorking" })
public class IsWorkingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<User> users = new ArrayList<User>();
		List<Branch> branches = new ArrayList<Branch>();
		List<Department> departments = new ArrayList<Department>();

		UserService userService = new UserService();
		users = userService.getAllUser();
		BranchService branchService = new BranchService();
		branches = branchService.getAllBranch();
		DepartmentService departmentService = new DepartmentService();
		departments = departmentService.getAllDepartment();

		request.setAttribute("users", users);
		request.setAttribute("branches", branches);
		request.setAttribute("departments", departments);

		request.getRequestDispatcher("/management.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		UserService userService = new UserService();
		User user = userService.getUser(Integer.parseInt(request.getParameter("editUserId")));

		userService.isWorkingSwitch(user.getId(), user.getIs_working());
		response.sendRedirect("management");
	}

}
