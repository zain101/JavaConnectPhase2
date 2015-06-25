package com.javaConnect.main.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaConnect.main.model.Post;
import com.javaConnect.main.model.PostDB;

public class Blogpost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String body = request.getParameter("post");
		HttpSession session  = request.getSession();
		int id = (int)session.getAttribute("id");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		String date = sdf.format(new Date());
		Post post = new Post();
		
		post.setTitle(title);
		post.setBody(body);
		post.setAuthor_id(id);
		post.setTimestamp(date);
		
		if(PostDB.insertPost(post)){
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
