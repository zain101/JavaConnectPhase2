package com.javaConnect.filter;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.javaConnect.auth.model.User;

public class EditProfileFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setOldUsername(request.getParameter("oldUsername"));
		user.setPassword(request.getParameter("password"));
		user.setOldPassword(request.getParameter("oldPassword"));
		user.setEmail(request.getParameter("email"));
		user.setLocation(request.getParameter("location"));
		user.setAbout(request.getParameter("about_you"));
		System.out.println("Testing: "+request.getParameter("test"));
		System.out.println("old passwd: " + request.getParameter("oldUsername"));
		System.out.println("new username" + user.getOldUsername());
		System.out.println("email: "+user.getEmail() +" "+ request.getParameter("email"));
		Connection conn;
		ServletContext context  = request.getServletContext();
		conn = (Connection)context.getAttribute("conn");
		HttpServletRequest req = (HttpServletRequest) request;
		InputStream is=null;
		Part part =  req.getPart("pic");
		if(part!=null)
			 is = part.getInputStream();
		if(is != null)
			user.setProfilePic(is);
		
		if(User.editProfile(user, conn)){
			request.setAttribute("status", "true");
			request.setAttribute("error", "false");
		}
		else{
			request.setAttribute("error", "true");
			request.setAttribute("status", "false");
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
