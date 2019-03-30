<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <input type="button" value="Add Student"
        onclick="window.location.href='add-student-form.jsp'; return false;" />
    <br></br>
    <table border="1">
        <tr>
            <th>id</th>
            <th>name</th>
            <th>action</th>
        </tr>
        <c:forEach var="tempStudent" items="${studentList}">
            <c:url var="tempUpdate" value="/students">
                <c:param name="command" value="READ" />
                <c:param name="id" value="${tempStudent.id}" />
            </c:url>
            <c:url var="tempDelete" value="/students">
                <c:param name="command" value="DELETE" />
                <c:param name="id" value="${tempStudent.id}" />
            </c:url>
            <tr>
                <td>${tempStudent.id}</td>
                <c:url var="tempLink" value="/students">
                    <c:param name="command" value="FIND" />
                    <c:param name="id" value="${tempStudent.id}" />
                </c:url>
                <td><a href="${tempLink}">${tempStudent.name}</a></td>
                <td><a href="${tempUpdate}">Update</a> | 
                    <a href="${tempDelete}"
                    onclick="if (!(confirm('Delete this student?'))) return false">
                        Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p>
        <a href="/university">Back to Main</a>
    </p>
</body>
</html>
