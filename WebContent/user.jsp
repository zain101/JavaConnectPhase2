<%@page import="com.javaConnect.auth.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="bootstrap-3.3.4-dist/brand.ico">

    <title>JavaConnect</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet">
    	<link href="bootstrap-3.3.4-dist/styles.css" rel="stylesheet">
    

    <!-- Bootstrap theme -->
    <link href="bootstrap-3.3.4-dist/css/bootstrap-theme.min.css" rel="stylesheet">

 </head>
  
  <body role="document">
     <%@ include file="base.jsp" %>
	<% User user = (User)request.getAttribute("user"); %>
	<br><br>
    <div class="page-header" style="padding-left: 2em; padding-top: 3em;">
    <img class="img-rounded profile-thumbnail" src="DisplayImage?username=<%=user.getUsername()%>" height="275px" width="275px" />
<div class="profile-header" style="padding-left: 8em; padding-top: 1em;">
    <h1><%=user.getUsername() %></h1>
    <p>
         <%=user.getUsername() %> 
        
            From <a href="http://maps.google.com/?q=<%=user.getLocation()%>"><%=user.getLocation()%></a>
        
    </p>
        <p><a href="mailto: <%= user.getEmail()%>"><%= user.getEmail()%></a></p>
        <p> <%= user.getAbout()%></p>
    <p>
        Member since <%=user.getMember_since() %>.<br />
        Last seen <%=user.getLast_seen() %>.

    </p>

    <p>
             <%if(user.getUsername().equals(username1)){ %>
                <a class="btn btn-warning" href="{{ url_for('main.edit_profile') }}">Edit Profile</a>
            <%} %>
    </p>
    <p>
      
      </p>

    </div>
 </div>

    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  </body>
</html>
    