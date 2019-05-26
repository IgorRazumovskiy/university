<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>groups</title>
<base href="${pageContext.request.contextPath}/">
</head>
<body>
    <h2>Groups</h2>
    <input type="button" value="Add Group"
        onclick="window.location.href='groups-add.jsp'; return false;"/>
    <br></br>
    <table border="1">
        <tr>
            <th>id</th>
            <th>group</th>
            <th>action</th>
        </tr>
        <c:forEach var="tempGroup" items="${groupList}">
            <tr>
                <td>${tempGroup.id}</td>
                <td><a href="group?id=${tempGroup.id}">${tempGroup.name}</a></td>
                <td><a href="group/update?id=${tempGroup.id}"><button>Update</button></a>
                    <form action="group/delete" method="POST">
                        <input type="hidden" name="id" value="${tempGroup.id}"/>
                        <input type="submit" value="Delete"
                            onclick="if (!(confirm('Delete this group?'))) return false"/>
                    </form></td>
            </tr>
        </c:forEach>
    </table>
    <p>
        <a href="/university">Back to Main</a>
    </p>
</body>
</html>
