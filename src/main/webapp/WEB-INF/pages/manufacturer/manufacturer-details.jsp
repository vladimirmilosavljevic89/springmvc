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
	<p>This is page for MANUFACTURER DETAILS...</p>
	
	<c:if test="${not empty information}">
		<div>${information}</div>
	</c:if>
	<c:if test="${not empty exception}">
		<div>${exception}</div>
	</c:if>
	
	
	<form:form modelAttribute="manufacturerDto">
		<div>Name:</div>
		<div><form:input type="text" path="name" readonly="true" size="60"/></div>
		<div><form:errors path="name" /></div>
		
		<div>City number:</div>
		<div>
			<div><form:input type="text" path="cityDto.number" readonly="true" size="60"/></div>
		</div>
		
		<div>City name:</div>
		<div>
			<div><form:input type="text" path="cityDto.name" readonly="true" size="60"/></div>
		</div>
		<div><form:errors path="cityDto" /></div>
		
		<div>
			<a href="${pageContext.request.contextPath}/manufacturer/edit/id/${manufacturerDto.id}">Edit</a>		
		</div>
	</form:form>
</body>
</html>