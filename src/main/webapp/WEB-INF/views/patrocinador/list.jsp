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

	<script type="text/javascript">
		function changeAssignmentButton(idpatrocinador){
			select = document.getElementById("assign_s_" + idpatrocinador);
			button = document.getElementById("assign_b_" + idpatrocinador);
			
			button.onclick = function(){
				window.location.replace("/patrocinador/assign?idclub=" + select[select.selectedIndex].id 
						+ "&idpatrocinador=" + idpatrocinador);	
			};
		}
	</script>

	<input type="text" id="searchTerm"/>
	<input type="button" value="Search" onclick="javascript: window.location.replace('/patrocinador/list?name=' + document.getElementById('searchTerm').value)">
	<br>

	<b>Patrocinadores:<br></b>
	
	<table border="1">
		<tr>
			<th>Name</th>
		</tr>
		
		<jstl:forEach items="${patrocinadores}" var="i">
			<tr>
				<td><jstl:out value="${i.name}" /></td>
				<td><a href="/patrocinador/update?id=${i.id}">Edit</a></td>
				<td><a href="/patrocinador/delete?id=${i.id}">Delete</a></td>
				<td>
					<select id="assign_s_${i.id}" onclick="changeAssignmentButton(${i.id})">
						<jstl:forEach items="${clubs}" var="j">
							<option id="${j.id}"><jstl:out value="${j.name}"/></option>
						</jstl:forEach>
					</select>
					<input type="button" id="assign_b_${i.id}" onclick="javascript: window.location.replace('/patrocinador/assign')" value="Assign">
				</td>
			</tr>
		</jstl:forEach>
	</table>
	
	<br>
	
	<jstl:if test="${authUser != null && authUser.role.equals('ADMIN')}">
		<input type="button" onclick="javascript: window.location.replace('/patrocinador/create')" value="Create">
	</jstl:if>
		
	<jstl:if test="${errorMessage != null}">
		<jstl:out value="${errorMessage}" />
	</jstl:if>
	
	<jstl:forEach items="${patrocinadores}" var="i">
		<script type="text/javascript">
			changeAssignmentButton(${i.id});		
		</script>
	</jstl:forEach>
</body>
</html>