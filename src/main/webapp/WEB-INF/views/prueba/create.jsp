<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix = "jstl" uri = "http://java.sun.com/jsp/jstl/core" %>
	
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>

<body>
	<jsp:include page="../masterpage/header.jsp"/>

	<b>Create prueba:<br></b>
	
	<form:form action="/prueba/save" modelAttribute="prueba">
		<form:hidden path="id"/>

		<form:label path="name">Name</form:label>
		<form:input path="name" required="required"/>
		<form:errors path="name"/>

		<form:label path="date">Date</form:label>
		<form:input path="date" required="required"/>
		<form:errors path="date"/>
		
		<input type="submit" value="Create"/>
	</form:form>
</body>
</html>