package com.javaConnect.filter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.javaConnect.auth.model.User;

/**
 * Servlet Filter implementation class RegisterFilter
 */
public class RegisterFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Inside Register Filter");
		User user = new User();
		Connection conn;
		ServletContext context = request.getServletContext();
		user.setUsername(request.getParameter("username"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setAbout(request.getParameter("about_you"));
		user.setLocation(request.getParameter("location"));
		InputStream inputstream = null;
		HttpServletRequest req  = (HttpServletRequest) request ;
		Part filePart =  req.getPart("pic");
		if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            inputstream = filePart.getInputStream();
    
        }
		if(inputstream != null)
			user.setProfilePic(inputstream);
		conn = (Connection)context.getAttribute("conn");
		
		
		if(User.validateUserName(user.getUsername(), conn)){
			boolean rows = User.insertUser(user, conn);
			request.setAttribute("validUsername", "success");
			if(rows){
				request.setAttribute("status", "true");
				request.setAttribute("error", "false");
			}
			
		}
		else{
			request.setAttribute("validUsername", "failure");
			request.setAttribute("error", "true");
			request.setAttribute("status", "false");
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
