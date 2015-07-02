<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
    <!-- Bootstrap core CSS -->
    <link href="bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
    
</head>
<body>
<c:set var="user" value="${sessionScope.user}"></c:set>
    <div class="container">
  
    <legend ><h3 style="float: center"><a href="index.jsp">HOME</a></h3> </legend>
    
	<form class="form-horizontal" action="EditProfileServlet" method="post" enctype="multipart/form-data">
  <fieldset>
    <div id="legend">
      <legend class="">Edit Profile</legend>
    </div>
    <div class="control-group">
      <label class="control-label"  for="oldUsername">Old Username</label>
      <div class="controls">
        <input type="text" id="oldUsername" name="oldUsername"  class="input-xlarge"  value="${user.username }">
        <p class="help-block">Username can contain any letters or numbers, without spaces</p>
      </div>
    </div>
   <div class="control-group">
      <label class="control-label"  for="username">New Username</label>
      <div class="controls">
        <input type="text" id="username" name="username"  class="input-xlarge" value="${user.username }">
        <p class="help-block">Username</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- E-mail -->
      <label class="control-label" for="email">E-mail</label>
      <div class="controls">
        <input type="text" id="email" name="email" placeholder="Email-ID" class="input-xlarge" >
        <p class="help-block">Please provide your E-mail</p>
      </div>
    </div>
  <div class="control-group">
      <!-- Password -->
      <label class="control-label"  for="oldPassword">Old Password</label>
      <div class="controls">
        <input type="password" id="oldPassword" name="oldPassword" placeholder="type your previous password" class="input-xlarge">
        <p class="help-block">Old Password</p>
      </div>
    </div>
    <div class="control-group">
      <!-- Password-->
      <label class="control-label" for="password">New Password</label>
      <div class="controls">
        <input type="password" id="password" name="password" placeholder="strong passwd" class="input-xlarge">
        <p class="help-block">Password should be at least 4 characters</p>
      </div>
    </div>
 
   
    <div class="control-group">
      <!-- location -->
      <label class="control-label"  for="location">Location</label>
      <div class="controls">
        <input type="text" id="location" name="location" placeholder="location" class="input-xlarge" value="${user.location }">
        <p class="help-block">Where you live</p>
      </div>
    </div>
        <div class="control-group">
      <!-- about -->
      <label class="control-label"  for="about">About You</label>
      <div class="controls">
        <textarea  id="about" name="about_you"  rows="5" cols="60"  class="input-xlarge"  >${user.about}</textarea>
        <p class="help-block">A statement that defines you</p>
      </div>
    </div>
    
    <div class="control-group">
      <!-- about -->
      <label class="control-label"  for="pic">Profile Picture</label>
      <div class="controls">
        <input type="file" id="pic" name="pic"   class="input-xlarge"></input>
        <p class="help-block">Upload your profile picture</p>
      </div>
    </div>
 
 
    <div class="control-group">
      <!-- Button -->
      <div class="controls">
        <button class="btn  btn-primary">Submit</button>
      </div>
    </div>
  </fieldset>
</form>

    </div> <!-- /container -->

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>