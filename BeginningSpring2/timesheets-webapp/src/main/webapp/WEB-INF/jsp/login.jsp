<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Timesheet Login</title>
		<link rel="stylesheet" type="text/css" HREF="${ctx}/css/timesheet.css">
	</head>
	<body>
		<h1>Spring Timesheet Application</h1>
		<form id="login" action="j_acegi_security_check" method="post">
				<label>Username: <input id="username" type="text" name="j_username"/></label>
				<label>Password: <input id="password" type="password" name="j_password"/></label>
				<input type="submit" name="login" value="Login"/>
		</form>
	</body>
</html>
