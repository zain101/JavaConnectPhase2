<%@page import="com.javaConnect.main.model.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<link href="bootstrap-3.3.4-dist/styles.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap theme -->
    <link href="bootstrap-3.3.4-dist/css/bootstrap-theme.min.css" rel="stylesheet">

 </head>
  
  <body role="document">
     <%@ include file="base.jsp" %>

   	<%
	//HttpSession session = request.getSession();
		Post post =(Post) request.getAttribute("post");
	%>
	
  <ul class="posts" style="margin-top: 4em; ">
            <li class="post">
                <div class="post-thumbnail">
                	<% String username2 = post.getAuthor_name();%>
                   <a href="profile?username=<%=username%>" >
                <img class="img-rounded profile-thumbnail" src="DisplayImage?username=<%=username2 %>" height="100px" width="100px" >
            </a>
                </div>
                <div class="post-content" style="padding-left:  5em;">
                    <div class="post-date"><%=post.getTimestamp() %></div>
                    <div class="post-author">
                        <a href="profile?username=<%=username2%>">
                           <%= username2%>
                        </a>
                    </div>
                    <div  class="post-body">
                   <h1> <%=post.getTitle() %></h1>
                   <br><br>
                    <% String str = post.getBody(); 
                    %>
                    <% out.write(str); %>
                    </div>
                    <div class="post-footer">
                        <%if (user.getUsername().equals(username2)){ %>
                            <a href="edit?id=<%=post.getPid() %>">
                                <span class="label label-success">Edit</span>
                            </a>
                        <%} %>
                        <span class="label label-info">Views | <%=post.getPid() %></span>
                        <a href="post?id=<%=post.getPid()%>">
                            <span class="label label-primary">Permalink</span>
                        </a>
                        <a href="#comments">
                            <span class="label label-primary">Comments</span>
                         </a>
                    </div>
                </div>
            </li>
    </ul>
   
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  </body>
</html>
    