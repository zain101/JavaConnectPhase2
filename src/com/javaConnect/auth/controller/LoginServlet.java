package com.javaConnect.auth.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaConnect.auth.model.FetchPost;
import com.javaConnect.auth.model.PostModel;
import com.javaConnect.auth.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User) request.getAttribute("user");
		ArrayList<PostModel> posts;
		
		if(User.authenticate(user, (Connection)request.getAttribute("conn")) != null){
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUsername());
			session.setAttribute("id", user.getId());
			posts = FetchPost.getPost();
			if(posts != null)
				session.setAttribute("posts", posts);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			
		}
		else{
			request.setAttribute("invalid", "true");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

}
