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
	<% User user1 = (User)request.getAttribute("user1"); %>
	<br><br>
    <div class="page-header" style="padding-left: 2em; padding-top: 3em;">
    <img class="img-rounded profile-thumbnail" src="DisplayImage?username=<%=user1.getUsername()%>" height="275px" width="275px" />
<div class="profile-header" style="padding-left: 8em; padding-top: 1em;">
    <h1><%=user1.getUsername() %></h1>
    <p>
         <%=user1.getUsername() %> 
        
            From <a href="http://maps.google.com/?q=<%=user.getLocation()%>"><%=user.getLocation()%></a>
        
    </p>
        <p><a href="mailto: <%= user1.getEmail()%>"><%= user1.getEmail()%></a></p>
        <p> <%= user1.getAbout()%></p>
    <p>
        Member since <%=user1.getMember_since() %>.<br />
        Last seen <%=user1.getLast_seen() %>.

    </p>

    <p>
             <%if(user1.getUsername().equals(username1)){ %>
                <a class="btn btn-warning" href="{{ url_for('main.edit_profile') }}">Edit Profile</a>
            <%} %>
    </p>
    <p>
      </p>

<br />
<h3 >Posts by <%=user1.getUsername() %></h3>
	
    </div>
 </div>
		<div class="container" style="margin-top: -10em;">
		<%@ include file="test.jsp" %>
		</div>
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  </body>
</html>
    