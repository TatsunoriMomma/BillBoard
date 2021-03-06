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
		String checkPassword = request.getParameter("checkPassword");
		int branch = Integer.parseInt(request.getParameter("branchId"));
		int department = Integer.parseInt(request.getParameter("departmentId"));

		User checkExistUser = new UserService().checkUserExistance(loginId);

		if (StringUtils.isBlank(loginId) == true) {
			messages.add("ログインIDを入力してください");
		} else if (!loginId.matches("\\w*") == true){
			messages.add("入力できる文字は半角英数字のみです");
		} else if (loginId.length() < 6 || loginId.length() > 20){
				messages.add("ログインIDの桁数に誤りがあります");
		}
		if (checkExistUser != null){
			messages.add("指定されたログインIDは既に使用されています");
		}
		if(StringUtils.isBlank(name) == true){
			messages.add("名前を入力してください");
		} else if(name.length() > 10){
			messages.add("名前の桁数に誤りがあります");
		}
		if(StringUtils.isBlank(password) == true){
			messages.add("パスワードを入力してください");
		} else if (password.length() < 6 || password.length() > 20){
			messages.add("パスワードの桁数に誤りがあります");
		}
		if (!checkPassword.equals(password)){
		messages.add("パスワード（確認用）が正しくありません");
		}
		if(branch == 0){
			messages.add("支店を選択してください");
		}
		if(department == 0){
			messages.add("部署を選択してください");
		} else if(branch != 1 && department <= 2){
			messages.add("支店と部署の組み合わせが正しくありません");
		} else if(branch == 1 && department > 2){
			messages.add("支店と部署の組み合わせが正しくありません");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
