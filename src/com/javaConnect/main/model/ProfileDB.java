package com.javaConnect.main.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaConnect.Databases.ConnectionManager;
import com.javaConnect.auth.model.User;
import com.sun.org.apache.regexp.internal.recompile;

public class ProfileDB {

	public static User getProfile(User user){
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn ;
		conn = cm.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String sql = "select username, email, about_me, last_seen, location, member_since from users where username = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			rs = pstmt.executeQuery();
			if(rs.next()){
				user.setUsername(rs.getString(1));
				user.setEmail(rs.getString(2));
				user.setAbout(rs.getString(3));
				user.setLast_seen(rs.getString(4));
				user.setLocation(rs.getString(5));
				user.setMember_since(rs.getString(6));
				return user;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				pstmt.close();
				rs.close();
				cm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
