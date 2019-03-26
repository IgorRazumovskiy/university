<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>student</title>
</head>
<body>
    <h2>Student ${student.name}</h2>
    <table border="1">
        <tr>
            <th>id</th>
            <th>name</th>
        </tr>
        <c:if test="${not empty student}">
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
            </tr>
        </c:if>
    </table>
</body>
</html>
