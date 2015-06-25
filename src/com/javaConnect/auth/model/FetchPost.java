package com.javaConnect.auth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.javaConnect.Databases.ConnectionManager;

public class FetchPost {
	
	public static ArrayList<PostModel> getPost(){
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = cm.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		PostModel posts = new PostModel();;
		ArrayList<PostModel> postsList = new ArrayList<PostModel>();
		String sql = "select u.username, p.title, p.id, p.timestamp from users as u join posts as p on u.id = p.author_id limit 100";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				posts.setAuthor_name(rs.getString(1));
				posts.setTitle(rs.getString(2));
				posts.setPid(rs.getInt(3));
				posts.setTimestamp(rs.getString(4));
				
				postsList.add(posts);
				posts = new PostModel();
				
			}
			if (postsList != null) {
				for (PostModel post : postsList) {
					System.out.println("### " +post.getAuthor_name());
					System.out.println("### " +post.getPid());

				}
				return postsList;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			try {
				stmt.close();
				rs.close();
				cm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	
	public static PostModel getPost1(String id){
		ConnectionManager cm = ConnectionManager.getInstance();
		Connection conn = cm.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostModel posts = new PostModel();;
		ArrayList<PostModel> postsList = new ArrayList<PostModel>();
		String sql = "select u.username, p.title, p.body,  p.id, p.timestamp from users as u join posts as p on u.id = p.author_id where p.id= ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				posts.setAuthor_name(rs.getString(1));
				posts.setTitle(rs.getString(2));
				posts.setBody(rs.getString(3));
				posts.setPid(rs.getInt(4));
				posts.setTimestamp(rs.getString(5));				
			}
			if (posts != null) {
				System.out.println("*** "+posts.getBody());
					return posts;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				pstmt.close();
				rs.close();
				cm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
