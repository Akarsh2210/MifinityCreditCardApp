<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration Screen</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<div class="conatiner p-5">
		<div class="row"></div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-6">

				<form:form method="POST" modelAttribute="registrationForm">
					<h3 class="text-primary">Registration Form</h3>
					<spring:bind path="username">
						<div ${status.error ? 'has-error' : ''}>
							<h4>Username</h4>
							<form:input type="text" path="username" placeholder="Username"
								autofocus="true"></form:input>
							<form:errors path="username" class="text-danger"></form:errors>
						</div>
					</spring:bind>
					<br>

					<spring:bind path="password">
						<div ${status.error ? 'has-error' : ''}>
							<h4>Password</h4>
							<form:input type="password" path="password"
								placeholder="Password"></form:input>
							<form:errors path="password" class="text-danger"></form:errors>
						</div>
					</spring:bind>
					<br>

					<spring:bind path="passwordConfirm">
						<div ${status.error ? 'has-error' : ''}>
							<h4>Confirm Password</h4>
							<form:input type="password" path="passwordConfirm"
								placeholder="Confirm your password"></form:input>
							<form:errors path="passwordConfirm" class="text-danger"></form:errors>
						</div>
					</spring:bind>
					<br>

					<button class="btn btn-primary" type="submit">Submit</button>
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>