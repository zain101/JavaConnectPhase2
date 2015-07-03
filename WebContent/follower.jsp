<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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

</head>
<body>

<%@ include file="base.jsp" %>


<div class="page-header">
	<br /> <br />
    <h1 align="center">${requestScope.str } of <%=user.getUsername() %> </h1>
</div>
<c:set  var="usersf" value="${requestScope.users}"></c:set>

<table class="table table-hover followers table-responsive">
    <thead><tr><th>Users</th></tr></thead>
    <c:forEach items="${usersf}" var="current"> 
    <tr>
        <td><h3>
            <a href="profile?username=${current}">
                <img class="img-rounded" src="DisplayImage?username=${current}" height="60px" width="60px" >
                ${current}
            </a></h3>
        </td>
    </tr>
    </c:forEach>
</table>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
</body>
</html>