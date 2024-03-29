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
<title>Search Result and Update</title>
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
					<h3 class="text-primary">Search Result:</h3>
					<br />
					<div class="row">
						<table class="table table-striped">
							<thead>
								<tr>

									<th>Cardholder Name</th>
									<th>Card Number</th>
									<th>Expiry Date</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${creditCardList}" var="creditCardList">
									<tr>
										<td>${creditCardList.getCardholderName()}</td>
										<td>${creditCardList.getCardNumber()}</td>
										<td>${creditCardList.getExpiryDate()}</td>
										<!-- <button type="submit">Update</button> -->
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<hr size="5">
					<div class="row">
						<form:form method="POST" modelAttribute="updateForm"
							action="${contextPath}/updateCard">
							<h4 class="text-primary">Update Card Details</h4>
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

							<button class="btn btn-secondary" type="submit">Update</button>
						</form:form>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>