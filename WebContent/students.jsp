<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>students</title>
<base href="${pageContext.request.contextPath}/">
</head>
<body>
    <h2>Students</h2>
    <input type="button" value="Add Student"
        onclick="window.location.href='students-add.jsp'; return false;"/>
    <br></br>
    <table border="1">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>action</th>
        </tr>
        <c:forEach var="tempStudent" items="${studentList}">
            <tr>
                <td>${tempStudent.id}</td>
                <td><a href="student?id=${tempStudent.id}">${tempStudent.name}</a></td>
                <td><a href="student/update?id=${tempStudent.id}"><button>Update</button></a>
                    <form action="student/delete" method="POST">
                        <input type="hidden" name="id" value="${tempStudent.id}"/>
                        <input type="submit" value="Delete"
                            onclick="if (!(confirm('Delete this student?'))) return false"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p>
        <a href="/university">Back to Main</a>
    </p>
</body>
</html>
