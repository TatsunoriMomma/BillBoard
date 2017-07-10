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

@WebFilter({"/management","/signup","/isWorking","/edit"})
public class AffairsDepartmentFilter implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
		try{

			HttpSession session = ((HttpServletRequest)request).getSession(false);
			User loginUser = (User) session.getAttribute("loginUser");

			String loginURI = "/BillBoard/login";
			List<String> messages = new ArrayList<String>() ;

			if (loginUser == null){
				session = ((HttpServletRequest)request).getSession(true);
				messages.add("ログインしてください");
				session.setAttribute("errorMessages", messages);
				((HttpServletResponse)response).sendRedirect(loginURI);
				return;
			} else {
				int branchId = loginUser.getBranch_id();
				int departmentId = loginUser.getDepartment_id();
				if (!(branchId == 1 && departmentId == 1)) {
					messages.add("権限がありません");
					session.setAttribute("errorMessages", messages);
					((HttpServletResponse)response).sendRedirect("./");
					return;
				}

			}
			System.out.println("AffairsDepartmentFilterが実行されました。");
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
