<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manufactures</title>
</head>
<body>
	This is page for CONTACT PERSONS.
	<div>
		<h1> contact persons list</h1>
		<table>
			<thead>
				<tr>
					<th>first Name</th>
					<th>last Name</th>
					<th>manufacturer Name</th>
					<th>Details</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="cp" items="${contactPersons}">
				<tr>
					<td>${cp.firstName}</td>
					<td>${cp.lastName}</td>
 					<td>${cp.manufacturerDto.name}</td> 
					<td><a href="${pageContext.request.contextPath}/contactperson/details/id/${cp.id}"> details</a></td>
					<td><a href="${pageContext.request.contextPath }/contactperson/delete/${cp.id }" >Delete</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>