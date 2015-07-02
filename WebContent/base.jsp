<%@page import="com.javaConnect.auth.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
         
          <a class="navbar-brand active"  href="#"> <span style="color: #FFD700;"><img src="bootstrap-3.3.4-dist/brand.ico" height="40px" width="40px" style="padding-bottom: 5px; padding-top: -10px;">
          <b style="padding-bottom: 10em;">JavaConnect</b><small style="font-size: xx-small;  ">beta</small></span></a>
          
        </div>
        <% User user = (User)session.getAttribute("user") ;
        	String username = null;
        	if(user != null)
        		username =user.getUsername();
        %>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="index.jsp">Home</a></li>
            <li><a href="profile?username=<%=username%>">Profile</a></li>
            <li><a href="UserListServlet" target="_blank" >Users</a></li>
            <li><a href="editor.jsp" >Create</a></li>

          </ul>
		<div class="navbar-collapse collapse" >
		      <li  class="nav navbar-nav navbar-right" style="float: right; padding-top: 1em;">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" style="color: #FFFFFF; text-decoration: none;">
              <%String username1;if((username1=username) != null ) {%>
				
              <img  src="DisplayImage?username=<%=username1 %>" height="30px" width="30px">
              <%}%>
              <b>Account</b><span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu" style="float: right;">
                <% if(username != null ){ %>
                	<li><a href="#">Hello <%=user.getUsername() %></a></li>
                	<li><a href="logout">logout</a></li>
                	<li><a href="editProfile.jsp">edit</a></li>
                
                <%}else{ %>
                <li><a href="login.jsp">sign-in</a></li>
                <li><a href="register.jsp">sign-up</a></li>
                <%} %>
              </ul>
            </li>
		
		</div>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    

