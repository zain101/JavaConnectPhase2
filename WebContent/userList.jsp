<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

    <!-- Bootstrap theme -->
    <link href="bootstrap-3.3.4-dist/css/bootstrap-theme.min.css" rel="stylesheet">

<style type="text/css">
.boxing{
	border-radius: 25px;
    width: 280px;
    height: 290px;
    padding-left: 2em;
   	box-shadow: 2px 2px 2px 2px #B8B8B8;
}

div.boxing:hover{
		background-color: #e7e7e7;
}
</style>
</head>

<body role="document">
<%@ include file="base.jsp" %>

<br /><br /><br /><br />
<c:set  var="ul" value="${requestScope.userList}"></c:set>
      <c:out value="${current.username}" />

			 
	
 	 <div class="page-header" style="padding-left: 5em;">
    <c:forEach items="${ul}" var="current">
    	<div class="boxing" style="float: left; padding-right: 5%; min-height: 10px;  margin: 12px 12px 12px 10px;">
        <div class="page-header" style="float: left; padding-right: 5%; min-height: 10px;  margin: 12px 0px 0px 0px; ">
        <img class="img-rounded profile-thumbnail " style=" margin: 0px 0px 0px 0px;" src="DisplayImage?username=${current.username}" height="80px" width="80px" />
        <div class="profile-header" style=" margin: 0px;">
        <a href="profile?username=${current.username}"><h3 style=" margin: -2em 5px 30px 90px;">${current.username}</h3></a>
        <p style="font-size: small; margin: 0px 0px 0px 0px;">
            ${current.username}
                From <a href="http://maps.google.com/?q=${current.location}">${current.location}</a>
        </p>
        
            <p style=" margin: 0px 0px 0px 0px;"><a href="mailto: ${current.email}">${current.email}</a></p>
            <p> ${current.about}</p>
        <p style=" margin: 0px 0px 0px 0px;">
            Member since  ${current.member_since}.<br />
            Last seen  ${current.last_seen}.
            <p style=" margin: 0px 0px 0px 0px;">${current.postCount} blog posts.</p>
        </p>

        <p style=" margin: 0px 0px 0px 0px;">
                 <c:if test="${current.username == sessionScope.username}"> 
                    <a class="btn btn-warning" href="#">Edit Profile</a>
                </c:if>  
                <c:if test="${sessionScope.username == 'admin' && current.username != sessionScope.username}">
                    <a class="btn btn-danger" href="#">Edit Profile [Admin]</a>
              	</c:if> 
        </p>  
		
        </div>
        </div>
        </div>
        </c:forEach>
</div> 
  <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

</body>
</html>