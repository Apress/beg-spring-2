<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Timesheet Preview User</title>
		<link rel="stylesheet" type="text/css" HREF="${ctx}/css/timesheet.css">
	</head>
	<body>
		<h2>Preview User</h2>
		<form:form>
		    <form:errors cssClass="error" path="username"/>
			<label>Username: ${command.username}</label>
			<form:hidden path="username"/>
			<input type="submit" name="_eventId_edit" value="Cancel"/>
			<input type="submit" name="_eventId_save" value="Save User"/>
		</form:form>
	</body>
</html>