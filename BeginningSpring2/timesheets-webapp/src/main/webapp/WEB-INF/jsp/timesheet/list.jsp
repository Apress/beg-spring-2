<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.apress.com/tags/spring/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Manage Timesheets</title>
		<link rel="stylesheet" type="text/css" HREF="${ctx}/css/timesheet.css">
	</head>
	<body>
		<div class="command">
			<a href="${ctx}/">Home</a>
			<a href="${ctx}/j_acegi_logout">Logout</a>
			<a href="${ctx}/timesheets/create">Create Timesheet</a>
		</div>
		<h1>Manage Timesheets</h1>
		<c:forEach var="timesheet" items="${timesheets}">
			<div id="user">${timesheet.id} <a href="${ctx}/timesheets/view?id=${timesheet.id}">${f:format(timesheet.startDate)}</a></div>
		</c:forEach>
	</body>
</html>