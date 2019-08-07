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
<title>Create Credit Card</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script type="text/javascript">
function fnCheckFlag(){
	
	if(${flag} == true){
		alert("New Card Created");
		window.location.href="${contextPath}/creditCardFunctions";
		flag = "";
	}else{
		alert("There is something wrong");
		window.location.href="${contextPath}/createCreditCard";
	}
}
</script>
</head>
<body onLoad="fnCheckFlag()" />
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
				<form:form method="POST" modelAttribute="cardDetailsForm"
					action="${contextPath}/createCard">
					<h3 class="text-primary">Create Credit Card</h3>
					<spring:bind path="cardholderName">
						<div ${status.error ? 'has-error' : ''}>
							<h5>Cardholder Name</h5>
							<form:input type="text" path="cardholderName"
								placeholder="Cardholder Name" autofocus="true"></form:input>
							<form:errors path="cardholderName" class="text-danger"></form:errors>
						</div>
					</spring:bind>
					<br>

					<spring:bind path="cardNumber">
						<div ${status.error ? 'has-error' : ''}>
							<h5>Credit Card Number</h5>
							<form:input type="text" path="cardNumber"
								placeholder="Credit Card Number" autofocus="true"></form:input>
							<form:errors path="cardNumber" class="text-danger"></form:errors>
						</div>
					</spring:bind>
					<br>

					<spring:bind path="expiryDate">
						<div ${status.error ? 'has-error' : ''}>
							<h5>Expiry Date</h5>
							<form:input type="text" path="expiryDate"
								placeholder="Expiry Date" autofocus="true"></form:input>
							<form:errors path="expiryDate" class="text-danger"></form:errors>
						</div>
					</spring:bind>

					<input type="hidden" name="username"
						value="${pageContext.request.userPrincipal.name}"></input>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />

					<br>


					<button class="btn btn-success" type="submit">Create</button>
				</form:form>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>