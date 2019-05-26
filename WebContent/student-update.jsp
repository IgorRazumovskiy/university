<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>student-update</title>
<base href="${pageContext.request.contextPath}/">
</head>
<body>
    <h2>Update Student</h2>
    <form action="student/update" method="POST">
        <input type="hidden" name="id" value="${student.id}"/>
        <table>
            <tbody>
                <tr>
                    <td><label>Name:</label></td>
                    <td><input type="text" name="name"
                        value="${student.name}"/></td>
                </tr>
                <tr>
                    <td><label></label></td>
                    <td><input type="submit" name="Save"
                        class="save"/></td>
                </tr>
            </tbody>
        </table>
    </form>
    <p>
        <a href="students">Back to Students</a>
    </p>
</body>
</html>
