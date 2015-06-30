package com.javaConnect.listener;


import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextListener() {
    	System.out.println("Context listener execution started...");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	Connection conn ;
    	ServletContext context = event.getServletContext();
    	String driver = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String username = context.getInitParameter("username");
		String passwd = context.getInitParameter("password");
		try {
			Class.forName(driver);
			conn  = DriverManager.getConnection(url, username, passwd);
			context.setAttribute("conn", conn); 
		} catch (Exception e) {
			System.err.println(e);
		}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("context Listener execution ended.");
    }
	
}
