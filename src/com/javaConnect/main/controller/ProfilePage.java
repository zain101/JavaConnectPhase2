package com.javaConnect.main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaConnect.auth.model.User;
import com.javaConnect.main.model.ProfileDB;

/**
 * Servlet implementation class ProfilePage
 */
public class ProfilePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		User user = new User();
		user.setUsername(username);
		System.out.println("username got from param: "+ username);
		user = ProfileDB.getProfile(user);
		if(user != null){
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
			rd.forward(request, response);
		}
		
	}

	
}
