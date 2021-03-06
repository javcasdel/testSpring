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
	<b>Login:<br></b>
	
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>
	<script type="text/javascript">
		if(${logout}){
			Cookies.set("auth", "false");
		}
	</script>

	<form:form action="/usuario/check" modelAttribute="usuario">
		<form:label path="name">Name</form:label>
		<form:input path="name"/>
		<form:errors path="name"/>
		
		<form:label path="password">Password</form:label>
		<form:input path="password" type="password"/>
		<form:errors path="password"/>
		
		<input type="submit" value="Login"/>
	</form:form>
	
	<button onclick="window.location.replace('/usuario/create')">Create new user</button>
	
	<jstl:if test="${errorMessage != null}">
		<jstl:out value="${errorMessage}" />
	</jstl:if>
</body>
</html>