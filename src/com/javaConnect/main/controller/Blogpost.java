package com.javaConnect.main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Blogpost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String status = (String) request.getAttribute("postStatus");
		if(status.equals("success")){
			RequestDispatcher rd = request.getRequestDispatcher("editor.jsp");
			request.setAttribute("postSuccess", "true");
			rd.forward(request, response);
		}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("editor.jsp");
			request.setAttribute("postFail", "true");
			rd.forward(request, response);
		}
		
	}

}
