<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manufactures</title>
</head>
<body>

	<p>This is page for contact person add.</p>

	<c:if test="${not empty information}">
		<div>${information}</div>
	</c:if>
	<c:if test="${not empty exception}">
		<div>${exception}</div>
	</c:if>


	<form:form action="${pageContext.request.contextPath}/contactperson/confirm"
		modelAttribute="contactPersonDto" method="post">


		<div>First Name:</div>
		<div>
			<form:input type="text" path="firstName" />
		</div>
		<div>
			<form:errors path="firstName" />
		</div>
		<div>Last Name:</div>
		<div>
			<form:input type="text" path="lastName" />
		</div>
		<div>
			<form:errors path="lastName" />
		</div>

		<div>manufacturers:</div>
		<div>
			<form:select path="manufacturerDto" multiple="false">
		
				<form:options items="${manufactures}" itemValue="id"
					itemLabel="name" />
			</form:select>
		</div>
		<div>
			<form:errors path="manufacturerDto" />
		</div>

		<button id=save>Save</button>
	</form:form>
</body>
</html>