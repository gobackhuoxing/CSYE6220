<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Final Project</title>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   
</head>
<body onload='document.f.j_username.focus();'>
<nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/">Home</a>
     
      <a class="navbar-brand welcome">Welcome <c:out value="${pageContext.request.userPrincipal.name}"></c:out> </a>
      <sec:authorize access="isAuthenticated()">
		<p>
			<a class="navbar-brand logout" href="<c:url value ='/j_spring_security_logout' />">log out </a>
		</p>
	</sec:authorize>
     
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="${pageContext.request.contextPath}/login">Join Me</a></li>
        <li><a href="${pageContext.request.contextPath}/comment">Tell Me</a></li>
        <li><a href="${pageContext.request.contextPath}/newappointment">Date Me</a></li>
        <li><a href="${pageContext.request.contextPath}/map">Find Me</a></li>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li><a href="${pageContext.request.contextPath}/confirmappointment">Confirm Appointment</a></li>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li><a href="${pageContext.request.contextPath}/admin">Admin</a></li>
        </sec:authorize>
      </ul>
    </div>
  </div>
</nav>

<div class="container-fluid bg-2 text-center">
<h3 class="margin">What are You waiting for?</h3><br>
  <c:if test="${param.error != null}">
		<p class="error">Try again</p>

	</c:if>
	<form class = "t1" name='f'
		action='${pageContext.request.contextPath}/j_spring_security_check'
		method='POST'>
		<table class="t1">
			<tr>
				<td>User Name:</td>
				<td><input class="tabletext" type='text' style= "width: 300px;" name='j_username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input class="tabletext" type='password' style= "width: 300px;" name='j_password' /></td>
			</tr>
			<tr>
				<td>Remember me:</td>
				<td><input class="tabletext" type='checkbox' name='_spring_security_remember_me'
					checked="checked" /></td>
			</tr>
			<tr>
				<td colspan='2'> <input class="loginbutton"  name="submit" type="submit" value="Login" /></td>
			
				
				</tr>
		</table>
		
	</form>
	<p >
		<a class="createaccount" href="<c:url value ="/newaccount" />">Create a account </a>
	</p>
</div>

<div class="container-fluid bg-3 text-center">    
  <h3 class="margin copyright">Copyright, Web Design, Resource, Picture......Almost Everything @ 2016 XiaoCase</h3><br>
</div>

</body>
</html>