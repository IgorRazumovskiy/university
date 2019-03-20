<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*"%>
<%@page import="ua.com.foxminded.dao.implementation.StudentDAOImpl"%>
<%@page import="ua.com.foxminded.domain.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>students</title>
</head>
<body>
	<h2>Students</h2>
	<table border="1">
		<tr>
			<th>id</th>
			<th>name</th>
		</tr>
		<%
		    StudentDAOImpl studentDAO = new StudentDAOImpl();
		    List<Student> studentList = studentDAO.findAll();
		    pageContext.setAttribute("studentList", studentList);
		%>
		<c:forEach var="tempStudent" items="${studentList}">
			<tr>
				<td>${tempStudent.id}</td>
				<td>${tempStudent.name}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
