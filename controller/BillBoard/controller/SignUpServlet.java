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

import BillBoard.beans.Branch;
import BillBoard.beans.Department;
import BillBoard.beans.User;
import BillBoard.service.BranchService;
import BillBoard.service.DepartmentService;
import BillBoard.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Branch> branches = new BranchService().getAllBranch();
		List<Department> departments = new DepartmentService().getAllDepartment();

		request.setAttribute("branches", branches);
		request.setAttribute("departments", departments);
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		List<Branch> branches = new BranchService().getAllBranch();
		List<Department> departments = new DepartmentService().getAllDepartment();


		HttpSession session = request.getSession();
		User user = new User();
		user.setLogin_id(request.getParameter("loginId"));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setBranch_id(Integer.parseInt(request.getParameter("branchId")));
		user.setDepartment_id(Integer.parseInt(request.getParameter("departmentId")));
		user.setIs_working(0);


		if (isValid(request, messages) == true) {

			new UserService().register(user);

			response.sendRedirect("management");
		} else {
			session.setAttribute("errorMessages", messages);

			request.setAttribute("user", user);
			request.setAttribute("branches", branches);
			request.setAttribute("departments", departments);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		if (StringUtils.isBlank(loginId) == true) {
			messages.add("ログインIDを入力してください");
		} else if (!loginId.matches("\\w*") == true){
			messages.add("入力できる文字は半角英数字のみです");
		} else if (loginId.length() < 6 || loginId.length() > 20){
				messages.add("ログインIDの桁数に誤りがあります");
		}

		//else if (isLoginId != null && isLoginId.getId() != id ){
		//	messages.add("指定されたログインIDは既に使用されています");
		//}

		if (password.length() < 6 || password.length() > 255){
			messages.add("パスワードの桁数に誤りがあります");
		}
		//else if (!passwordConfirmation.equals(password)){
		//messages.add("パスワード（確認用）が正しくありません");
		//}

		if(StringUtils.isBlank(name) == true){
			messages.add("名前を入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
