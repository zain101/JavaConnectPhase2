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

public class AuthenticateFilter implements Filter {

  
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Hey in filter of authenticateFilter");
		User user = new User();
		Connection conn;
		ServletContext context = request.getServletContext();
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("passwd"));
		conn = (Connection)context.getAttribute("conn");
		request.setAttribute("user", user);
		request.setAttribute("conn", conn);
		
		chain.doFilter(request, response);
	}

	 
	public void init(FilterConfig fConfig) throws ServletException {
	}


	@Override
	public void destroy() {
		
	}

}
