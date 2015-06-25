package com.javaConnect.Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static ConnectionManager instance = null;
	
	private final String username = "root";
	private final String password = "root";
	private final String conn_string = "jdbc:mysql://localhost/JavaConnect";
	
	private Connection conn = null;
	
	private ConnectionManager(){}
	
	public static ConnectionManager getInstance(){
		if(instance == null){
			instance = new ConnectionManager();
		}
		return instance;
	}
	
	public boolean openConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(conn_string, username, password);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public Connection getConnection(){
		if(conn == null){
			if(openConnection()){
				System.out.println("Connection opened successfully "+conn);
				return conn;
			}else{
				return null;
			}
		}
		return conn;
	}
	
	public void close(){
		System.out.println("Closing connection");
		try {
			conn.close();
			conn = null;
		} catch (Exception e) {
		}
	}

}

