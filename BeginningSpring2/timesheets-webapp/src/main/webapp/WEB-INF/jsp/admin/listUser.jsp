<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Timesheet Admin</title>
		<link rel="stylesheet" type="text/css" HREF="${ctx}/css/timesheet.css">
	</head>
	<body>
		<div class="command">
			<a href="${ctx}/">Home</a>
			<a href="${ctx}/j_acegi_logout">Logout</a>
			<a href="${ctx}/admin/create?_flowId=createUser-flow">Add New User</a>
		</div>
	    <h1>Administration</h1>
	    <div id="userList">
	    	<h2>Users</h2>
			<c:forEach var="user" items="${users}">
				<div id="user"><a href="${ctx}/admin/view/${user.accountName}">${user.accountName}</a></div>
			</c:forEach>
		</div>
	</body>
</html>