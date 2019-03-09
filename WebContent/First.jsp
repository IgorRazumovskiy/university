<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="ua.com.foxminded.dao.implementation.GroupDAOImpl"%>
<%@page import="ua.com.foxminded.domain.Group"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>First JSP</title>
</head>
<body>
	<h1>Testing JSP</h1>
	<p>
		<% 
        Date now = new Date();
        String s = "Current date: " + now;
        out.println(s);
        GroupDAOImpl groupDAO = new GroupDAOImpl();
        List<Group> actual = groupDAO.findAll();
        for (Group x : actual) {
            System.out.println(x);
            System.out.println(x.getStudents());
        }        
        %>
	</p>
</body>
</html>