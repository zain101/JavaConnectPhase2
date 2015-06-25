package com.javaConnect.main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaConnect.auth.model.FetchPost;
import com.javaConnect.auth.model.PostModel;

/**
 * Servlet implementation class ViewPost
 */
public class ViewPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		PostModel post = new PostModel();
		
		post = FetchPost.getPost1(id);
		String str = post.getBody();
		/*PegDownProcessor peg = new PegDownProcessor();
		str = peg.markdownToHtml(str);
		post.setBody(str);*/
		request.setAttribute("post", post);
		RequestDispatcher rd = request.getRequestDispatcher("post.jsp");
		rd.forward(request, response);
	}

}