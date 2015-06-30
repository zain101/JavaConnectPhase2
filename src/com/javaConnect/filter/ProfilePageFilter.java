package com.javaConnect.filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.javaConnect.auth.model.User;

public class ProfilePageFilter implements Filter {

	public void destroy() {
	
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		User user = new User();
		ServletContext context = request.getServletContext();
		user.setUsername(request.getParameter("username"));
		Connection conn = (Connection) context.getAttribute("conn");
		
		user = User.getProfile(user, conn);
		if(user != null){
			request.setAttribute("user", user);
			request.setAttribute("status", "success");
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
