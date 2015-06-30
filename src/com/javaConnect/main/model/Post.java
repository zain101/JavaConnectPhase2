package com.javaConnect.main.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Post {
	private  String  body;
	private  String  timestamp;
	private  int   author_id;
	private  String  title;
	private  long  views;
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
