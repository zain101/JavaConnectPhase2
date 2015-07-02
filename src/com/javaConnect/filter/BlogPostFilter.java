package com.javaConnect.filter;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.javaConnect.auth.model.User;
import com.javaConnect.main.model.Post;

public class BlogPostFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String editSucess = request.getParameter("editSuccess");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session  = req.getSession();
		ServletContext context = request.getServletContext();
		Connection conn;
		User user = (User) session.getAttribute("user");
		Post post = new Post();
		int pid = Integer.parseInt(request.getParameter("pid"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		String date = sdf.format(new Date());
		post.setPid(pid);
		post.setTitle(request.getParameter("title"));
		post.setBody(request.getParameter("post"));
		post.setAuthor_id(user.getId());
		post.setTimestamp(date);
		conn = (Connection)context.getAttribute("conn");
		
		if(editSucess.equals("success")){
			Post.updatePost(post, conn);
			request.setAttribute("postStatus", "success");
		}
		else{
			if(Post.insertPost(post, conn)){
				request.setAttribute("postStatus", "success");
			}
			else{
				request.setAttribute("postStatus", "failure");
			}
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
