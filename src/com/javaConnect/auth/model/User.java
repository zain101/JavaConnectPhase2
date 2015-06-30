package com.javaConnect.auth.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
