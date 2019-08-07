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
<title>Search Credit Cards</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script type="text/javascript">
	function fnCheckField() {
		var creditCardNumber = document.getElementById("creditCardNumber").value;
		if (creditCardNumber == null || creditCardNumber == "") {
			alert("Please enter the card number");
			return false;
		}

	}
</script>
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
					<form method="POST" action="${contextPath}/searchCard"
						onsubmit="return fnCheckField()">
						<h3 class="text-primary">Search Credit Cards</h3>
						<h5>Enter credit card number</h5>
						<input name="creditCardNumber" id="creditCardNumber" type="text"
							placeholder="Credit Card Number" autofocus="true" /> <br> <br>
						<input type="hidden" name="username"
							value="${pageContext.request.userPrincipal.name}"></input> <input
							type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<button class="btn btn-secondary" type="submit">Search</button>
					</form>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
