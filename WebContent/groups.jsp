<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>groups</title>
</head>
<body>
    <h2>Groups</h2>
    <table border="1">
        <tr>
            <th>id</th>
            <th>group</th>
        </tr>
        <c:forEach var="tempGroup" items="${groupList}">
            <tr>
                <td>${tempGroup.id}</td>
                <td><a href="group?id=${tempGroup.id}">${tempGroup.name}</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
