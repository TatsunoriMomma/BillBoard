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
import BillBoard.exception.NoRowsUpdatedRuntimeException;
import BillBoard.service.BranchService;
import BillBoard.service.DepartmentService;
import BillBoard.service.UserService;

@WebServlet(urlPatterns = { "/edit" })
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User editUser = new User();
		int editUserId = Integer.parseInt(request.getParameter("editUserId"));
		UserService userService = new UserService();
		editUser = userService.getUser(editUserId);

		List<Branch> branches = new ArrayList<Branch>();
		List<Department> departments = new ArrayList<Department>();
		BranchService branchService = new BranchService();
		branches = branchService.getAllBranch();
		DepartmentService departmentService = new DepartmentService();
		departments = departmentService.getAllDepartment();

		request.setAttribute("editUser", editUser);
		request.setAttribute("branches", branches);
		request.setAttribute("departments", departments);

		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{

		List<String> messages = new ArrayList<String>();

		List<Branch> branches = new ArrayList<Branch>();
		List<Department> departments = new ArrayList<Department>();
		BranchService branchService = new BranchService();
		branches = branchService.getAllBranch();
		DepartmentService departmentService = new DepartmentService();
		departments = departmentService.getAllDepartment();


		HttpSession session = request.getSession();


		User user = new User();
		user.setId(Integer.parseInt(request.getParameter("id")));
		user.setLogin_id(request.getParameter("loginId"));
		user.setName(request.getParameter("name"));
		user.setBranch_id(Integer.parseInt(request.getParameter("branchId")));
		user.setDepartment_id(Integer.parseInt(request.getParameter("departmentId")));
		//パスワードが空でなかった場合
		if(StringUtils.isBlank(request.getParameter("password")) != true){
			user.setPassword(request.getParameter("password"));
		}


		if (isValid(request, messages) == true) {

			try {
				new UserService().update(user);
			} catch (NoRowsUpdatedRuntimeException e) {
				messages.add("他の人によって更新されています。最新のデータを表示しました。データを確認してください。");
				session.setAttribute("errorMessages", messages);
				request.getRequestDispatcher("/edit.jsp");
			}

			response.sendRedirect("management");
			//request.setAttribute("branches", branches);
			//request.setAttribute("departments", departments);
			//request.getRequestDispatcher("/management").forward(request, response);
		} else {
			session.setAttribute("errorMessages", messages);
			request.setAttribute("editUser", user);
			request.setAttribute("branches", branches);
			request.setAttribute("departments", departments);
			request.getRequestDispatcher("/edit.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		int id = Integer.parseInt(request.getParameter("id"));
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

		if (checkExistUser != null && id != checkExistUser.getId() ){
			messages.add("指定されたログインIDは既に使用されています");
		}


		if(StringUtils.isBlank(request.getParameter("password")) != true) {
			if (password.length() < 6 || password.length() > 255){
				messages.add("パスワードの桁数に誤りがあります");
			}
		}
		if(!checkPassword.equals(password)){
		messages.add("パスワード（確認用）が正しくありません");
		}
		if(branch != 1 && department <= 2){
			messages.add("支店と部署の組み合わせが正しくありません");
		}
		if(branch == 1 && department > 2){
			messages.add("支店と部署の組み合わせが正しくありません");
		}
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
