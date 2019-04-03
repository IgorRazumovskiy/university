<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>group-update</title>
<base href="${pageContext.request.contextPath}/">
</head>
<body>
    <h2>Update Group</h2>
    <form action="group-update" method="POST">
        <input type="hidden" name="id" value="${group.id}" />
        <table>
            <tbody>
                <tr>
                    <td><label>Name:</label></td>
                    <td><input type="text" name="name"
                        value="${group.name}" /></td>
                </tr>
                <tr>
                    <td><label></label></td>
                    <td><input type="submit" name="Save" /></td>
                </tr>
            </tbody>
        </table>
    </form>
    <p>
        <a href="groups">Back to Groups</a>
    </p>
</body>
</html>
