<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.apress.com/tags/spring/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Delete Timesheet</title>
		<link rel="stylesheet" type="text/css" HREF="${ctx}/css/timesheet.css">
	</head>
	<body>
		<h1>Delete Timesheet</h1>
		<div id="timesheet">
			<p>Owner: ${timesheet.consultant.accountName}</p>
			<p>Timesheet# ${timesheet.id}</p>
			<p>Created on: ${f:format(timesheet.created)}</p>
			<p>Starts on: ${f:format(timesheet.startDate)}</p>
			<p>Notes: ${timesheet.note}</p>
			<table>
				<tr>
					<th>Start</th>
					<th>Finish</th>
					<th>Note</th>
					<th>Rate</th>
				</tr>
			<c:forEach var="period" items="${timesheet.periods}">
				<tr>
					<td>${f:formatDateTime(period.startTime)}</td>
					<td>${f:formatDateTime(period.endTime)}</td>
					<td>${period.note}</td>
					<td>${period.rate}</td>
				</tr>
			</c:forEach>
			</table>
		</div>
		<form:form action="${ctx}/timesheets/delete">
			<form:hidden path="id"/>
			<input type="submit" name="cancel" value="Cancel"/>
			<input type="submit" name="delete" value="Delete Timesheet"/>
		</form:form>
	</body>
</html>