<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>view-group</title>
</head>
<body>
    <h2>Group ${group.name}</h2>
    <table border="1">
        <tr>
            <th>id</th>
            <th>student name</th>
        </tr>
        <c:if test="${not empty group}">
            <c:forEach var="tempStudent" items="${group.students}">
                <tr>
                    <td>${tempStudent.id}</td>
                    <td>${tempStudent.name}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</body>
</html>
