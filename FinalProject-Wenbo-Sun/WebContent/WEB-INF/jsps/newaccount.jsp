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
   
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery.js"></script>
<script>

function go() {
		$("#password").keyup(match);
		$("#confirmpass").keyup(match);
		$("#details").submit(submit);
	}

	function match() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();

		if (password.length > 3 || confirmpass.length > 3) {

			if (password == confirmpass) {
				$("#matchpass").text("Passwords match.");
				$("#matchpass").addClass("valid");
				$("#matchpass").removeClass("error");
			} else {
				$("#matchpass").text("Passwords do not match.");
				$("#matchpass").addClass("error");
				$("#matchpass").removeClass("valid");
			}
		}
	}

	function submit() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();

		if (password != confirmpass) {
			alert("not match password")
			return false;
		} else {
			return true;
		}
	}
$(document).ready(go);
</script>
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
  <h2 class="margin">Create your account</h2>
  <sf:form id="details" method="post"
		action="${pageContext.request.contextPath}/createaccount"
		commandName="user">
		<table class="t1">
			<tr>
				<td>Gender:</td>
				<td>Male <input class="t2" type="radio" name="gender" value="male">
					Female <input class="t2" type="radio" name="gender" value="female">
					other <input class="t2" type="radio" name="gender" value="other"checked="checked"></td>
			</tr>
			<tr>
				<td>UserName:</td>
				<td><sf:input class="t2 tabletext" path="username" type="text" /></td>
					<td class="error">
						<sf:errors path="username" cssClass="error">
						</sf:errors>
					</td>
			</tr>
			<tr>
				<td>Name:</td>
				<td><sf:input class="t2 tabletext" path="name" type="text" /></td>
					<td class="error">
						<sf:errors path="name" cssClass="error">
						</sf:errors>
					</td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><sf:input class="t2 tabletext" path="email" type="text" /></td>
					<td class="error">
						<sf:errors path="email" cssClass="error">
						</sf:errors>
					</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><sf:input id="password" class="t2 tabletext" path="password" name="password" type="password" /></td>
					<td class="error">
						<sf:errors path="password" cssClass="error">
						</sf:errors>
					</td>
			</tr>
			<tr>
				<td>Renter password :</td>
				<td><input id="confirmpass" class="t2 tabletext" name="confirmpass" type="password" /> </td>
				<td><div id="matchpass"> </div></td>
			</tr>
			<tr>
				<td></td>
				<td class="createaccount"><input class="loginbutton" value="create account"
					type="submit"></td>
			</tr>
		</table>
	</sf:form>
</div>

<div class="container-fluid bg-3 text-center">    
  <h3 class="margin copyright">Copyright, Web Design, Resource, Picture......Almost Everything @ 2016 XiaoCase</h3><br>
</div>
	

	<h2>Create a new account</h2>

	
</body>
</html>