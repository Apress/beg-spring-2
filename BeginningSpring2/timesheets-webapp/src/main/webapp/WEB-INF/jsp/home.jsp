<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="authz" uri="http://acegisecurity.org/authz" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Timesheet Home</title>
		<link rel="stylesheet" type="text/css" HREF="${ctx}/css/timesheet.css">
	</head>
	<body>
		<div class="command">
			<a href="${ctx}/j_acegi_logout">Logout</a>
			<a href="${ctx}/timesheets">Manage Timesheets</a>
		<authz:authorize ifAllGranted="ROLE_ADMINISTRATOR">
				<a href="${ctx}/admin">Administration</a>
		</authz:authorize>
		</div>
		<h1>Home</h1>
	</body>
</html>