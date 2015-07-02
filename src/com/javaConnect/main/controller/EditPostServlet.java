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
import com.javaConnect.main.model.Post;

public class EditPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session  = request.getSession();
		ServletContext context = request.getServletContext();
		Connection conn  = (Connection) context.getAttribute("conn");
		User user = (User)session.getAttribute("user");
		Post post = new Post();
		int pid  = Integer.parseInt(request.getParameter("id"));
		int uid  = user.getId();
		post.setPid(pid);
		if((post =Post.checkEditPermission(post, pid, uid, conn)) != null){
			request.setAttribute("post", post);
			request.setAttribute("editSuccess", "success");
		}
		else{
			request.setAttribute("permission", "error");
		}
		RequestDispatcher rd = request.getRequestDispatcher("editor.jsp");
		rd.forward(request, response);
	}

}
