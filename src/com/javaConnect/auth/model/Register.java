package com.javaConnect.auth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.javaConnect.Databases.ConnectionManager;

public class Register {

	public static boolean validateUserName(String username){
		ConnectionManager  cm= ConnectionManager.getInstance();
		Connection conn = cm.getConnection();
		 String sql = "select username from users where username = ? ";
		 ResultSet rs = null;
		 PreparedStatement pstmt=null;
		 try  {
			
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, username);
			 rs = pstmt.executeQuery();
			 if(rs.next()){
				 return false;
			 }
			 else 
				 return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		 finally{
			 try {
				rs.close();
				 pstmt.close();
				 cm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 }
	}
	
	public static boolean insertUser(User user){
		ConnectionManager  cm= ConnectionManager.getInstance();
		Connection conn = cm.getConnection();
		 String sql = "INSERT INTO users (username, password, email, about_me, last_seen, member_since, profile_pic, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		 String date = sdf.format(new Date());
		 try{
				PreparedStatement pstmt = conn.prepareStatement(sql);
			 	pstmt.setString(1, user.getUsername());
			 	pstmt.setString(2, user.getPassword());
			 	pstmt.setString(3, user.getEmail());
			 	pstmt.setString(4, user.getAbout());
			 	pstmt.setString(5, date);
			 	pstmt.setString(6, date);
			 	if (user.getProfilePic() != null)
			 		pstmt.setBlob(7, user.getProfilePic());
			 	pstmt.setString(8, user.getLocation());
			 	int rows = pstmt.executeUpdate();
			 	 cm.close();
			 	if (rows> 0){
			 		return true;
			 	}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		 return true;
		 
	}
	
}
