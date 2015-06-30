package com.javaConnect.auth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.javaConnect.main.model.Post;

public class FetchPost {
	
	public static ArrayList<Post> getPost(Connection conn){
		Statement stmt = null;
		ResultSet rs = null;
		Post posts = new Post();;
		ArrayList<Post> postsList = new ArrayList<Post>();
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
				posts = new Post();
				
			}
			if (postsList != null) {
				for (Post post : postsList) {
					System.out.println("### " +post.getAuthor_name());
					System.out.println("### " +post.getPid());

				}
				return postsList;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	public static Post getPost1(String id, Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Post posts = new Post();;
		ArrayList<Post> postsList = new ArrayList<Post>();
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
		}
		finally{
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
		
	}
}
