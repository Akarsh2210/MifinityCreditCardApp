<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Screen</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<div class="conatiner p-5">
		<div class="row"></div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-6">
				<h3>Mifinity Log In Portal</h3>
			</div>

		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-6">
				<form method="POST" action="${contextPath}/login">
					<div ${error != null ? 'has-error' : ''}>
						<div class="row">
							<div class="col-md-6">
								<span class="text-danger">${message}</span> <br>
								<h4>Username</h4>
								<input name="username" type="text" placeholder="Username"
									autofocus="true" />
								<h4>Password</h4>
								<input name="password" type="password" placeholder="Password" /><br>
								<span class="text-danger">${error}</span> <input type="hidden"
									name="${_csrf.parameterName}" value="${_csrf.token}" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 p-3">
								<button class="btn btn-primary" type="submit">Log In</button>
								<h5 class="p-2">
									<a href="${contextPath}/registration">Create Account</a>
								</h5>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>