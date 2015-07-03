package com.javaConnect.main.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Follow {
	private String follower_id;
	private String followed_id;
	public String getFollower_id() {
		return follower_id;
	}
	public void setFollower_id(String follower_id) {
		this.follower_id = follower_id;
	}
	public String getFollowed_id() {
		return followed_id;
	}
	public void setFollowed_id(String followed_id) {
		this.followed_id = followed_id;
	}	
	
	public static boolean notFollowing(int id, String user, Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt  = conn.prepareStatement("select f.* from follows  f join (select id from users where username = ? ) as q where f.follower_id = ? and f.followed_id = q.id");
			pstmt.setString(1, user);
			pstmt.setInt(2, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println(user+ "is following" +  id );
				return false;
			}
			else{
				System.out.println(  user  + "is not following" + id);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	public static void follow(int id, String user, Connection conn) {
		String sql = "insert into follows (follower_id, followed_id ) "
				+ "select q.id, u.id from users as u "
				+ "join (select id from users where id = ?) as q where u.username = ? ";
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, user);
			rows = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	public static void unFollow(int id, String user, Connection conn) {
		String sql = "delete from follows where follower_id=  ? and followed_id in (select id from users where username = ? ) ";
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, user);
			rows = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static ArrayList<String> getFollowers(int id, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		ArrayList<String> users = new ArrayList<String>();
		String sql = "select distinct username from users as u join follows as f where u.id in (SELECT follower_id FROM follows where followed_id = ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs =pstmt.executeQuery();
			while(rs.next()){
				users.add(rs.getString(1));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static ArrayList<String> getFollowing(int id, Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		ArrayList<String> users = new ArrayList<String>();
		String sql = "select distinct username from users as u "
				+ "join follows as f  "
				+ "where u.id in (SELECT followed_id FROM follows where follower_id = ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs =pstmt.executeQuery();
			while(rs.next()){
				users.add(rs.getString(1));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				pstmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
