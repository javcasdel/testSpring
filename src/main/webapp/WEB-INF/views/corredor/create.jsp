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

	<b>Create corredor:<br></b>
	
	<form:form action="/corredor/save" modelAttribute="corredor">
		<form:hidden path="id"/>

		<form:label path="name">Name</form:label>
		<form:input path="name" required="required"/>
		<form:errors path="name"/>

		<form:label path="year">Year</form:label>
		<form:input path="year" required="required" type="number"/>
		<form:errors path="year"/>

		<select id="idclub" name="idclub" required="required"> 
			<jstl:forEach items="${clubs}" var="i">
				<option value="${i.id}">
					<jstl:out value="${i.name}"/>
				</option>			
			</jstl:forEach>
		</select>
		
		<input type="submit" value="Create"/>
	</form:form>
</body>
</html>