package com.javaConnect.auth.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	private int postCount;
	private int roleId;
	private String oldPassword;
	private String oldUsername;
	private int followerCount;
	private int followedCount;
	public String getOldUsername() {
		return oldUsername;
	}
	public void setOldUsername(String oldUsername) {
		this.oldUsername = oldUsername;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
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
			pstmt = conn.prepareStatement("select id, username, email, location, about_me, role_id from users where email = ? and password = ?");
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()){
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setLocation(rs.getString(4));
				user.setAbout(rs.getString(5));
				user.setRoleId(rs.getInt(6));
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
			String sql = "select  username, email, about_me, last_seen, location, member_since, id, count(*) as followers , q.following from users "
					+ "left join follows as f on f.followed_id = id  "
					+ "join (select   count(*) as following  from users "
					+ "join follows as ff on ff.follower_id = id where username = ? ) as q where username = ? ";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getUsername());
				pstmt.setString(2, user.getUsername());
				rs = pstmt.executeQuery();
				if(rs.next()){
					user.setUsername(rs.getString(1));
					user.setEmail(rs.getString(2));
					user.setAbout(rs.getString(3));
					user.setLast_seen(rs.getString(4));
					user.setLocation(rs.getString(5));
					user.setMember_since(rs.getString(6));
					user.setId(rs.getInt(7));
					user.setFollowerCount(rs.getInt(8));
					user.setFollowedCount(rs.getInt(9));
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
		
		public static ArrayList<User> getUserList(Connection conn){
			Statement stmt = null;
			ResultSet rs = null;
			User user ;
			ArrayList<User> userList = new ArrayList<User>();
			try {
				stmt = conn.createStatement();
				rs  = stmt.executeQuery("select u.username, u.email, u.about_me, u.last_seen, u.location, u.member_since, IFNULL(COUNT(p.author_id), 0) as postCount from users as u left join (select author_id from posts) as p on p.author_id = u.id group by u.id order by postCount desc");
				while(rs.next()){
					user = new User();
					user.setUsername(rs.getString(1));
					user.setEmail(rs.getString(2));
					user.setAbout(rs.getString(3));
					user.setLast_seen(rs.getString(4));
					user.setLocation(rs.getString(5));
					user.setMember_since(rs.getString(6));
					user.setPostCount(rs.getInt(7));
					userList.add(user);
					
				}
				return userList;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		public int getRoleId() {
			return roleId;
		}
		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}
		
		public static boolean editProfile(User user, Connection conn){
			PreparedStatement pstmt = null;
			String sql = "update users set username = ?, email = ?, password  = ?, about_me = ?, location = ?, profile_pic = ? where password= ? and username = ?";
			int rows=0;
			try {
				System.out.println("old passwd: " + user.getOldPassword());
				System.out.println("new username" + user.getOldUsername());
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getUsername());
				pstmt.setString(2, user.getEmail());
				pstmt.setString(3, user.getPassword());
				pstmt.setString(4, user.getAbout());
				pstmt.setString(5, user.getLocation());
				if (user.getProfilePic() != null)
					pstmt.setBlob(6, user.getProfilePic());
				pstmt.setString(7, user.getOldPassword());
				pstmt.setString(8, user.getOldUsername());
				rows = pstmt.executeUpdate();
				System.out.println("rows: "+ rows);
				if(rows > 0)
					return true;
							
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				try {
					//conn.commit();
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return false;
		}
		public int getFollowerCount() {
			return followerCount;
		}
		public void setFollowerCount(int followerCount) {
			this.followerCount = followerCount;
		}
		public int getFollowedCount() {
			return followedCount;
		}
		public void setFollowedCount(int followedCount) {
			this.followedCount = followedCount;
		}
		
		
}
