package com.javaConnect.auth.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditProfileServlet
 */
@MultipartConfig(maxFileSize = 16177215)
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = (String) request.getAttribute("status");
		if(status.equals("true")){
		RequestDispatcher dispatch  = request.getRequestDispatcher("login.jsp");
		dispatch.forward(request, response);
		}
		else{
			RequestDispatcher dispatch  = request.getRequestDispatcher("editProfile.jsp");
			dispatch.forward(request, response);
		}
	}

}
