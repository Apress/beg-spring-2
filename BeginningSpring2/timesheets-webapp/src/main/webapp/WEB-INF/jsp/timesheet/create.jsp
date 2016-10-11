<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Create Timesheet</title>
		<link rel="stylesheet" type="text/css" HREF="${ctx}/css/timesheet.css">
	</head>
	<body>
		<h1>Create Timesheet</h1>
		<form:form>
			<fieldset>
				<form:errors cssClass="error" path="startDate"/>
				<label>Start Date: <form:input path="startDate"/></label>
				<form:errors cssClass="error" path="note"/>
				<label>Notes: <form:textarea path="note" rows="5"/></label>
				<input type="submit" name="cancel" value="Cancel"/>
				<input type="submit" name="create" value="Create Timesheet"/>
			</fieldset>
		</form:form>
	</body>
</html>