<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib prefix = "jstl" uri = "http://java.sun.com/jsp/jstl/core" %>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>

<body>
	<jsp:include page="../masterpage/header.jsp"/>

	<input type="text" id="searchTerm"/>
	<input type="button" value="Search" onclick="javascript: window.location.replace('/prueba/list?name=' + document.getElementById('searchTerm').value)">
	<br>

	<b>pruebas:<br></b>
	
	<table border="1">
		<tr>
			<th>Name</th>
		</tr>
		
		<jstl:forEach items="${pruebas}" var="i">
			<tr>
				<td><jstl:out value="${i.name}" /></td>
				<td><a href="/prueba/update?id=${i.id}">Edit</a></td>
				<td><a href="/prueba/delete?id=${i.id}">Delete</a></td>
			</tr>
		</jstl:forEach>
	</table>
	
	<br>
	
	<jstl:if test="${authUser != null && authUser.role.equals('ADMIN')}">
		<input type="button" onclick="javascript: window.location.replace('/prueba/create')" value="Create">
	</jstl:if>
		
	<jstl:if test="${errorMessage != null}">
		<jstl:out value="${errorMessage}" />
	</jstl:if>
</body>
</html>