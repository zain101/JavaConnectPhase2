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

public class ProfilePageFilter implements Filter {

	public void destroy() {
	
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		User user = new User();
		ArrayList<Post> userPost;
		ServletContext context = request.getServletContext();
		user.setUsername(request.getParameter("username"));
		Connection conn = (Connection) context.getAttribute("conn");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session =  req.getSession();
		user = User.getProfile(user, conn);
		userPost = Post.getUserPosts(user, conn);
		System.out.println("user is "+ user.getUsername());
		if(user != null && userPost != null){
			request.setAttribute("userList",userPost);
			request.setAttribute("fromProfile", "true");
			session.setAttribute("user1", user);
			request.setAttribute("user1", user);
			request.setAttribute("status", "success");
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
