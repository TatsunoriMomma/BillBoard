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

import BillBoard.beans.User;
import BillBoard.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		User user = new User();
		user.setLogin_id(request.getParameter("loginId"));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setBranch(Integer.parseInt(request.getParameter("branch")));
		user.setDepartment(Integer.parseInt(request.getParameter("department")));
		user.setStatus("continue");


		if (isValid(request, messages) == true) {

			new UserService().register(user);

			response.sendRedirect("userManagement.jsp");
		} else {
			session.setAttribute("errorMessages", messages);

//			response.sendRedirect("signup");
			request.setAttribute("user", user);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		if (StringUtils.isEmpty(loginId) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		// TODO アカウントが既に利用されていないか、メールアドレスが既に登録されていないかなどの確認も必要
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
