package com.javaConnect.auth.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.javaConnect.auth.model.User;
import com.javaConnect.auth.model.Register;

/**
 * Servlet implementation class Register
 */
@MultipartConfig(maxFileSize = 16177215)
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String about = request.getParameter("about_you");
		String location = request.getParameter("location");

		InputStream inputstream = null;
		Part filePart =  request.getPart("pic");
		if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            inputstream = filePart.getInputStream();
    
        }
		
		User user = new User();
		
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setAbout(about);
		user.setLocation(location);
		if(inputstream != null)
		user.setProfilePic(inputstream);
		
		if(Register.validateUserName(user.getUsername())){
			boolean rows = Register.insertUser(user);
			if(rows){
				request.setAttribute("status", "true");
				request.setAttribute("error", "false");
				RequestDispatcher dispatch  = request.getRequestDispatcher("register.jsp");
				dispatch.forward(request, response);
			}
		}else{
			request.setAttribute("error", "true");
			request.setAttribute("status", "false");
			RequestDispatcher dispatch  = request.getRequestDispatcher("register.jsp");
			dispatch.forward(request, response);
		}
		
	}
}
