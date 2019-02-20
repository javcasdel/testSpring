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
	<b>Create account:<br></b>
	
	<form:form action="/usuario/save" modelAttribute="usuario">
		<form:label path="name">Name</form:label>
		<form:input path="name"/>
		<form:errors path="name"/>
		
		<form:label path="password">Password</form:label>
		<form:input path="password" type="password"/>
		<form:errors path="password"/>
		
		<form:select path="role">
			<form:option value="USER">User</form:option>
			<form:option value="ADMIN">Administrator</form:option>
		</form:select>
		
		<input type="submit" value="Create"/>
	</form:form>
	
	<button onclick="window.location.replace('/usuario/login')">Login as existing user</button>
	
	<jstl:if test="${errorMessage != null}">
		<jstl:out value="${errorMessage}" />
	</jstl:if>
</body>
</html>