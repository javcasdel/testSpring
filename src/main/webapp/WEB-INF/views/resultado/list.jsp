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

	<b>resultados:<br></b>
	
	<table border="1">
		<tr>
			<th>Time</th>
		</tr>
		
		<jstl:forEach items="${resultados}" var="i">
			<tr>
				<td><jstl:out value="${i.seconds}" /></td>
				<td><a href="/resultado/update?id=${i.id}">Edit</a></td>
				<td><a href="/resultado/delete?id=${i.id}">Delete</a></td>
			</tr>
		</jstl:forEach>
	</table>
	
	<br>
	
	<input type="button" onclick="javascript: window.location.replace('/resultado/create')" value="Create">
	
	<jstl:if test="${errorMessage != null}">
		<jstl:out value="${errorMessage}" />
	</jstl:if>
</body>
</html>