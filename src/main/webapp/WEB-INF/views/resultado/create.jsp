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

	<b>Create resultado:<br></b>
	
	<form:form action="/resultado/save" modelAttribute="resultado">
		<form:hidden path="id"/>
	
		<select id="idRunner" name="idRunner" required="required"> 
			<jstl:forEach items="${corredores}" var="i">
				<option value="${i.id}">
					<jstl:out value="${i.name}"/>
				</option>			
			</jstl:forEach>
		</select>
		
		<select id="idprueba" name="idprueba" required="required"> 
			<jstl:forEach items="${pruebas}" var="i">
				<option value="${i.id}">
					<jstl:out value="${i.name}"/>
				</option>			
			</jstl:forEach>
		</select>
		
		<form:label path="seconds">Seconds</form:label>
		<form:input path="seconds" required="required"/>
		<form:errors path="seconds"/>
		
		<input type="submit" value="Create"/>
	</form:form>
</body>
</html>