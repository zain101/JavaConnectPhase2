package com.javaConnect.filter;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.javaConnect.auth.model.User;
import com.javaConnect.main.model.Post;

public class LoginFilter implements Filter {

  
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Hey in filter of authenticateFilter");
		User user = new User();
		Connection conn;
		ServletContext context = request.getServletContext();
		HttpServletRequest req = (HttpServletRequest) request;
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("passwd"));
		conn = (Connection)context.getAttribute("conn");

		ArrayList<Post> posts;

		if((user=User.authenticate(user, conn)) != null){
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			posts = Post.getPost(conn);
			if(posts != null)
				session.setAttribute("posts", posts);
			request.setAttribute("auth", "success");
		}
		else{
			request.setAttribute("auth", "failure");
		}
		chain.doFilter(request, response);
	}

	 
	public void init(FilterConfig fConfig) throws ServletException {
	}


	@Override
	public void destroy() {
		
	}

}
