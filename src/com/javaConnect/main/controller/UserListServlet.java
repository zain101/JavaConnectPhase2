package com.javaConnect.main.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaConnect.auth.model.User;

public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> user ;
		ServletContext context = request.getServletContext();
		Connection conn = (Connection) context.getAttribute("conn");
		user = User.getUserList(conn);
		for (User user2 : user) {
			System.out.println(user2.getUsername());
		}
		request.setAttribute("userList", user);
		RequestDispatcher rd = request.getRequestDispatcher("userList.jsp");
		rd.forward(request, response);
	}


}
