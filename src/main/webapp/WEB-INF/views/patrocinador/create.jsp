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

	<b>Create patrocinador:<br></b>
	
	<form:form action="/patrocinador/save" modelAttribute="patrocinador">
		<form:hidden path="id"/>

		<form:label path="name">Name</form:label>
		<form:input path="name" required="required"/>
		<form:errors path="name"/>
		
		<input type="submit" value="Create"/>
	</form:form>
</body>
</html>