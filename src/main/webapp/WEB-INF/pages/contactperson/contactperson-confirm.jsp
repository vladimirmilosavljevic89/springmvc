<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>confirm page</p>

	<c:if test="${not empty information}">
		<div>${information}</div>
	</c:if>
	<c:if test="${not empty exception}">
		<div>${exception}</div>
	</c:if>


	<form:form
		action="${pageContext.request.contextPath}/contactperson/saveOrUpdate"
		method="post" modelAttribute="contactPersonDto">

		<form:hidden path="id" />

		<div>First name:</div>
		<div>
			<form:input type="text" path="firstName" readonly="true" size="60" />
		</div>


		<div>Last name:</div>
		<div>
			<div>
				<form:input type="text" path="lastName" readonly="true" size="60" />
			</div>
		</div>
			
		<div>Manufacturer id:</div>
		<div>
			<div>
				<form:input type="text" path="manufacturerDto.id" readonly="true"
					size="60" />
			</div>
		</div>
		<div>
			<form:errors path="manufacturerDto" />
		</div>
	
		<div>Manufacturer name:</div>
		<div>
			<div>
				<form:input type="text" path="manufacturerDto.name" readonly="true"
					size="60" />
			</div>
		</div>
		<div>
			<form:errors path="manufacturerDto" />
		</div>

		<div>
			<button id=save>Confirm</button>
		</div>
	</form:form>
</body>
</html>