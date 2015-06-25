package com.javaConnect.main.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaConnect.Databases.ConnectionManager;

public class PostDB {

	public static boolean insertPost(Post post){
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn ;
		conn = cm.getConnection();
		PreparedStatement pstmt = null;
		
		int rows;
		try {
			pstmt = conn.prepareStatement("insert into posts (body, timestamp, author_id, title) values (?, ?, ?, ?)");
			pstmt.setString(1, post.getBody());
			pstmt.setString(2, post.getTimestamp());
			pstmt.setLong(3, post.getAuthor_id());
			pstmt.setString(4, post.getTitle());
			rows =pstmt.executeUpdate();
			if(rows > 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally{
			try {
				pstmt.close();
				cm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
