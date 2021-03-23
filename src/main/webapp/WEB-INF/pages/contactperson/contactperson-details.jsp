<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p> CONTACT PERSON DETAILS</p>
	
	<c:if test="${not empty information}">
		<div>${information}</div>
	</c:if>
	<c:if test="${not empty exception}">
		<div>${exception}</div>
	</c:if>
	
	
	<form:form modelAttribute="contactPersonDto">
		<div>First Name:</div>
		<div><form:input type="text" path="firstName" readonly="true" size="60"/></div>
		<div><form:errors path="firstName" /></div>
			<div>Last Name:</div>
		<div><form:input type="text" path="lastName" readonly="true" size="60"/></div>
		<div><form:errors path="lastName" /></div>
		
		<div>Manufacturer name:</div>
		<div>
			<div><form:input type="text" path="manufacturerDto.name" readonly="true" size="60"/></div>
		</div>
		
		<div>
			<a href="${pageContext.request.contextPath}/contactperson/edit/id/${contactPersonDto.id}">Edit</a>		
		</div>
	</form:form>
</body>
</html>