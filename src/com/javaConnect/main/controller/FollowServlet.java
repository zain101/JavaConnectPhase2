package com.javaConnect.main.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaConnect.auth.model.User;
import com.javaConnect.main.model.Follow;

public class FollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User cuser ;
		HttpSession session = request.getSession();
		ServletContext context = request.getServletContext();
		Connection conn = (Connection) context.getAttribute("conn");
		cuser = (User) session.getAttribute("user");
		int id  = cuser.getId();
		String user = request.getParameter("user");
		System.out.println("In FollowServlet");
		if(Follow.notFollowing(id, user, conn)){
			Follow.follow(id, user, conn);
			request.setAttribute("message", "successfully followed user!");
			
		}
		else{
			request.setAttribute("message", "Already following OR invalid user !");
		}
	/*	RequestDispatcher rd = request.getRequestDispatcher("profile?username="+user);
		rd.forward(request, response);*/
		response.sendRedirect("profile?username="+user);
	}

}
