package com.javaConnect.main.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaConnect.auth.model.FetchPost;
import com.javaConnect.main.model.Post;

/**
 * Servlet implementation class ViewPost
 */
public class ViewPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		String id = request.getParameter("id");
		Post post = new Post();
		Connection conn =  (Connection) request.getAttribute("conn");
		post = Post.getPost1(id, conn);
		String str = post.getBody();
		/*PegDownProcessor peg = new PegDownProcessor();
		str = peg.markdownToHtml(str);
		post.setBody(str);*/
		request.setAttribute("post", post);
		RequestDispatcher rd = request.getRequestDispatcher("post.jsp");
		rd.forward(request, response);
	}

}