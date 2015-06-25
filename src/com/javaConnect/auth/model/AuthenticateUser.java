package com.javaConnect.auth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaConnect.Databases.ConnectionManager;

public class AuthenticateUser {


	public static User authenticate(User user){
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn;
		conn = cm.getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		try {
			pstmt = conn.prepareStatement("select id, username from users where email = ? and password = ?");
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()){
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				return user;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				pstmt.close();
				rs.close();			
				cm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
