package com.javaConnect.auth.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaConnect.auth.model.User;

@MultipartConfig(maxFileSize = 16177215)
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getAttribute("user");
		System.out.println(user);
		Connection conn = (Connection) request.getAttribute("conn");
		
		if(User.validateUserName(user.getUsername(), conn)){
			boolean rows = User.insertUser(user, conn);
			if(rows){
				request.setAttribute("status", "true");
				request.setAttribute("error", "false");
				RequestDispatcher dispatch  = request.getRequestDispatcher("register.jsp");
				dispatch.forward(request, response);
			}
		}else{
			request.setAttribute("error", "true");
			request.setAttribute("status", "false");
			RequestDispatcher dispatch  = request.getRequestDispatcher("register.jsp");
			dispatch.forward(request, response);
		}
		
	}
}
