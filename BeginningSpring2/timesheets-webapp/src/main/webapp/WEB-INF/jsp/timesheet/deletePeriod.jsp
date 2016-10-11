<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.apress.com/tags/spring/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Delete Timesheet Period</title>
		<link rel="stylesheet" type="text/css" HREF="${ctx}/css/timesheet.css">
	</head>
	<body>
		<h1>Delete Timesheet Period</h1>
		<table>
			<tr>
				<th>Start</th>
				<th>Finish</th>
				<th>Note</th>
				<th>Rate</th>
				<th>Type</th>
			</tr>
			<tr>
				<td>${f:formatDateTime(period.startTime)}</td>
				<td>${f:formatDateTime(period.endTime)}</td>
				<td>${period.note}</td>
				<td>${period.rate.rate}</td>
				<td>${period.rate.rateType.id}</td>				
			</tr>
		</table>
		<form:form action="${ctx}/timesheets/deletePeriod">
			<form:hidden path="id"/>
			<form:hidden path="timesheetId"/>
			<input type="submit" name="cancel" value="Cancel"/>
			<input type="submit" name="delete" value="Delete Period"/>
		</form:form>
	</body>
</html>