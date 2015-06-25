package com.javaConnect.auth.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaConnect.Databases.ConnectionManager;

/**
 * Servlet implementation class DisplayImage
 */
public class DisplayImage extends HttpServlet {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn;
		conn = cm.getConnection();
		ResultSet rs1 = null ;
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement("select profile_pic from users where username=?");
			String str =  (String)request.getParameter("username");
			System.out.println("Got username: " + str);
			stmt.setString(1,str);
			rs1 = stmt.executeQuery();
			if(rs1.next()){

				Blob imagelink = rs1.getBlob(1);
		        String m = rs1.getString(1);
	          
	          //System.out.println(imagelink.toString());
	          //System.out.println(m);
	           
	          response.setHeader("expires", "0"); 
	          response.setContentType("image/jpg");
	          //Blob img = rs1.getBlob(1);
	          byte[] imgData = imagelink.getBytes(1,(int)imagelink.length());		 
	          OutputStream o = response.getOutputStream();
	          o.write(imgData);
	          o.flush();
	          o.close();
	          //System.out.println(o.toString());
	          //System.out.println(imgData.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs1 != null && stmt !=null){
				rs1.close();
				stmt.close();
				cm.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
