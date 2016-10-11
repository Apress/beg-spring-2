<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://www.apress.com/tags/spring/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>View Timesheet</title>
		<link rel="stylesheet" type="text/css" HREF="${ctx}/css/timesheet.css">
	</head>
	<body>
		<div class="command">
			<a href="${ctx}/">Home</a>
			<a href="${ctx}/j_acegi_logout">Logout</a>
			<a href="${ctx}/timesheets/addPeriod?timesheetId=${timesheet.id}">Add Period</a>
			<a href="${ctx}/timesheets/update?id=${timesheet.id}">Update Timesheet</a>
			<a href="${ctx}/timesheets/delete?id=${timesheet.id}">Delete Timesheet</a>
		</div>
		<h1>View Timesheet</h1>
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
					<th>Type</th>
					<th>&nbsp;</th>
				</tr>
			<c:forEach var="period" items="${timesheet.periods}">
				<tr>
					<td>${f:formatDateTime(period.startTime)}</td>
					<td>${f:formatDateTime(period.endTime)}</td>
					<td>${period.note}</td>
					<td>${period.rate.rate}</td>
					<td>${period.rate.rateType.id}</td>
					<td><a href="${ctx}/timesheets/deletePeriod?id=${period.id}&timesheetId=${timesheet.id}">Delete Period</a></td>
				</tr>
			</c:forEach>
			</table>
		</div>
	</body>
</html>