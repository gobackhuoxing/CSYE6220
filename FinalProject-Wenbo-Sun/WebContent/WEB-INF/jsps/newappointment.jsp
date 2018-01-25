<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
<body>
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
  <h3 class="margin">When, Where, and What?</h3>
  <sf:form method="post"
		action="${pageContext.request.contextPath}/appointmentcreated"
		commandName="appointments">
		<sf:input type="hidden" path="id" />
		<table class="t1">
			<tr>
				<td class="tabletext">Time:</td>
				<td><sf:textarea class="t2 tabletext" path="time" rows="1" cols="20"></sf:textarea><br />
					<sf:errors path="time" cssClass="error">
					</sf:errors></td>
			</tr>
			<tr>
				<td class="tabletext">Detail:</td>
				<td><sf:textarea class="t2 tabletext" path="detail" rows="5" cols="20"></sf:textarea><br />
					<sf:errors path="detail" cssClass="error">
					</sf:errors></td>
			</tr>
			<tr>
				<td></td>
				<td class="tabletext"><input class="t2 tabletext" value="Submit appointment"
					type="submit"></td>
			</tr>


			<tr>
				<td></td>
				<td class="tabletext"><input class="t2 tabletext" name="delete"
					value="Delete appointment" type="submit"></td>
			</tr>
		</table>

	</sf:form>
	<sf:form method="post"
		action="${pageContext.request.contextPath}/myappointment"
		commandName="appointments">
		<sf:input type="hidden" path="username" value="${appointments.user.username}" />
		<input class="checkapp" value="Check my appointment status" type="submit">
	</sf:form>
</div>

<div class="container-fluid bg-3 text-center">    
  <h3 class="margin copyright">Copyright, Web Design, Resource, Picture......Almost Everything @ 2016 XiaoCase</h3><br>
</div>
</body>
</html>