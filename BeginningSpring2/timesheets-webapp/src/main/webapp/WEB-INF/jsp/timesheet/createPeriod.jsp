<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="f" uri="http://www.apress.com/tags/spring/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Create Timesheet Period</title>
		<link rel="stylesheet" type="text/css" HREF="${ctx}/css/timesheet.css">
	</head>
	<body>
		<h1>Create Timesheet Period</h1>
		<form:form>
			<form:errors cssClass="error" path="startTime"/>
			<label>Start Time: <input type="text" name="startTime" value="${f:formatDateTime(command.startTime)}"/></label>
			<form:errors cssClass="error" path="endTime"/>
			<label>End Time: <input type="text" name="endTime" value="${f:formatDateTime(command.endTime)}"/></label>
			<form:errors cssClass="error" path="note"/>
			<label>Note: <form:textarea path="note" rows="5"/></label>
			<form:errors cssClass="error" path="rate"/>
			<label>Rate: <form:input path="rate"/></label>
			<form:errors cssClass="error" path="rateTypeId"/>
			<label>Rate Type:
				<form:select path="rateTypeId">
					<form:options items="${rateTypeList}" itemLabel="id" itemValue="id"/>
				</form:select>
			</label>
			<input type="submit" name="cancel" value="Cancel"/>
			<input type="submit" name="create" value="Create Period"/>
		</form:form>
	</body>
</html>