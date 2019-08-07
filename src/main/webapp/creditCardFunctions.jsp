<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Credit Card Functions</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<div class="conatiner p-5">
		<div class="row"></div>
		<div class="row">
			<div class="col-md-6">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<form id="logoutForm" method="POST" action="${contextPath}/logout">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
					<div class="row">
						<h3>Welcome ${pageContext.request.userPrincipal.name} |</h3>
						&nbsp;
						<button class="btn btn-warning"
							onclick="document.forms['logoutForm'].submit()">Logout</button>
					</div>
					<br>
					<h4>Please select an option to proceed :</h4>
					<hr>
					<a href="${contextPath}/createCard">1. Create a new credit card</a>
					<br>
					<a href="${contextPath}/searchCard">2. Search for existing card</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>