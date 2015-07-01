package com.javaConnect.main.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Post {
	private  String  body;
	private  String  timestamp;
	private  int   author_id;
	private  String  title;
	private  long  views;
	private String author_name;
	private int pid;

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getViews() {
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
	
	public static boolean insertPost(Post post, Connection conn){
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
			e.printStackTrace();
			return false;
		}
		finally{
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
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
		System.out.println("from get post 1" + conn);
		ResultSet rs = null;
		Post posts = new Post();;
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
