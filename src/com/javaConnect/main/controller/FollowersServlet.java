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

import com.javaConnect.main.model.Follow;

public class FollowersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("str");
		int  id = Integer.parseInt(request.getParameter("id"));
		ServletContext context = request.getServletContext();
		Connection conn = (Connection) context.getAttribute("conn");
		ArrayList<String> users = null;
		request.setAttribute("str", str);
		if(str.equals("followers")){
			users = Follow.getFollowers(id, conn);
			
		}
		else if(str.equals("following")){
			users = Follow.getFollowing(id, conn);
		}
		request.setAttribute("users", users);
		RequestDispatcher rd = request.getRequestDispatcher("follower.jsp");
		rd.forward(request, response);
	}
}
