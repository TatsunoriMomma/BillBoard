package BillBoard.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BillBoard.beans.User;
import BillBoard.service.UserService;

@WebFilter("/*")
public class LoginFilter implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
		try{

			String target = ((HttpServletRequest)request).getRequestURI();
			HttpSession session = ((HttpServletRequest)request).getSession(false);
			String loginURI = "/BillBoard/login";
			List<String> messages = new ArrayList<String>() ;

			//ログインサーブレット,JSP,.cssじゃない時
			if (!target.equals(loginURI) && !target.equals("/login.jsp") && !target.matches(".+.css")){
				if (session == null){
					session = ((HttpServletRequest)request).getSession(true);
					session.setAttribute("target", target);
					messages.add("ログインしてください");
					session.setAttribute("errorMessages", messages);
					((HttpServletResponse)response).sendRedirect(loginURI);
					return;
				} else {
					Object loginCheck = session.getAttribute("loginUser");
					if (loginCheck == null) {
						session.setAttribute("target", target);
						messages.add("ログインしてください");
						session.setAttribute("errorMessages", messages);
						((HttpServletResponse)response).sendRedirect(loginURI);
						return;
					} else {
						//DBからユーザー停止復活チェック
						User loginUser = (User) loginCheck;
						String loginId = loginUser.getLogin_id();
						UserService userService = new UserService();
						int isWorking = userService.checkUserExistance(loginId).getIs_working();
						if (isWorking == 1){
							session.setAttribute("target", target);
							messages.add("ログインしてください");
							session.setAttribute("errorMessages", messages);
							((HttpServletResponse)response).sendRedirect(loginURI);
							return;
						}

					}
				}
			}
			//System.out.println("LoginFilterは実行されました。");
			chain.doFilter(request, response);

		}catch (ServletException e){
		}catch (IOException e){
		}
	}

	@Override
	public void destroy() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ

	}
}
