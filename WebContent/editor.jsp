<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <title>PageDown-Bootstrap Demo Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet/less" href="editor/demo/browser/demo.less">
    <script src="editor/demo/browser/less/less-1.2.2.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
    
    <style>
        body {
            background-color: White;
            padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="editor/demo/browser/bootstrap/js/bootstrap-transition.js"></script>
    <script src="editor/demo/browser/bootstrap/js/bootstrap-modal.js"></script>
    <script src="editor/demo/browser/bootstrap/js/bootstrap-tooltip.js"></script>
    <script type="text/javascript" src="editor/Markdown.Converter.js"></script>
    <script type="text/javascript" src="editor/Markdown.Sanitizer.js"></script>
    <script type="text/javascript" src="editor/Markdown.Editor.js"></script>

</head>
<body>


    <div class="container">
    <h3 style="float: center"><a href="index.jsp">HOME</a></h3> 
    <% if (request.getAttribute("postSuccess") != null){%>
    	<div class="alert alert-success alert-dismissible" role="alert">
  		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  		<strong>Success!</strong> Your post have been saved
		</div>
<%} %>
 <% if (request.getAttribute("postFail") != null || request.getAttribute("permission") != null){%>
    	  <div class="alert alert-danger alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <strong>Warning!</strong> Better check yourself, you're not looking too good.
		  </div>
<%} %>
<c:set var="post" value="${requestScope.post }"></c:set>
 	<form action="blogpost" method="post">
 		        <h1>PageDown-Bootstrap Demo Page</h1>
				<p><b>Title: </b> <input type="text" name="title" value="${post.title }"> </p>
				
				<input type="hidden" name="editSuccess" value = "${requestScope.editSuccess}">
        		<input type="hidden" name="pid" value="${post.pid }"/>

        	<div class="wmd-panel">
            	<div id="wmd-button-bar"></div>
			<textarea class="wmd-input" id="wmd-input" name="post">
				<c:if test="${post != null }">
					<c:out value="${post.body }"></c:out>
				</c:if>
				<c:if test="${post == null }">
					This is the *first* editor.
					------------------------------
				
					Just plain **Markdown**, except that the input is sanitized:
	
					<marquee>I'm the ghost from the past!</marquee>
				</c:if>
				
			</textarea>
        	</div>
        <div id="wmd-preview" class="wmd-panel wmd-preview"></div>
   	<button type="submit" class="btn  btn-primary">Save</button>
   </form>
   </div> 
    

    <script type="text/javascript">
        (function () {
            var converter1 = Markdown.getSanitizingConverter();
            var editor1 = new Markdown.Editor(converter1);
            editor1.run();
            
            var converter2 = new Markdown.Converter();

            converter2.hooks.chain("preConversion", function (text) {
                return text.replace(/\b(a\w*)/gi, "*$1*");
            });

            converter2.hooks.chain("plainLinkText", function (url) {
                return "This is a link to " + url.replace(/^https?:\/\//, "");
            });
            
            var help = function () { alert("Do you need help?"); }
            
            var editor2 = new Markdown.Editor(converter2, "-second", { handler: help });
            
            editor2.run();
        })();
    </script>
</body>
</html>
    