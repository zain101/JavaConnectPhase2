package com.javaConnect.auth.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaConnect.auth.model.AuthenticateUser;
import com.javaConnect.auth.model.FetchPost;
import com.javaConnect.auth.model.PostModel;
import com.javaConnect.auth.model.User;

/**
 * Servlet implementation class Authenticate
 */
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password  = request.getParameter("passwd");
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		ArrayList<PostModel> posts;
		if(AuthenticateUser.authenticate(user) != null){
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
