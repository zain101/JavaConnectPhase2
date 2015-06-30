package com.javaConnect.auth.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private int id;
	private String username ;
	private String email ;
	private String password ;
	private String about ;
	private String last_seen;
	private String location;
	private String member_since;
	private InputStream profilePic;
	private int confirm ;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getLast_seen() {
		return last_seen;
	}
	public void setLast_seen(String last_seen) {
		this.last_seen = last_seen;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMember_since() {
		return member_since;
	}
	public void setMember_since(String member_since) {
		this.member_since = member_since;
	}
	public InputStream getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(InputStream profilePic) {
		this.profilePic = profilePic;
	}
	public int getConfirm() {
		return confirm;
	}
	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public static User authenticate(User user, Connection conn){
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
				if(rs!=null)
				rs.close();			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	

		public static boolean validateUserName(String username, Connection conn){
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			 }
		}
		
		public static boolean insertUser(User user, Connection conn){
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
				 	if (rows> 0){
				 		return true;
				 	}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			 return true;
			 
		}
	

		public static User getProfile(User user, Connection conn){
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
				e.printStackTrace();
				return null;
			}
			finally{
				try {
					pstmt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
}
