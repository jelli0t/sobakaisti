<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login | Sobakaisti</title>
</head>
<body>
	<h1>LOG IN</h1>
	<form action="login_process" method="POST">
		<label>Username: </label><input type="text" name="username"/><br />
		<label>Password: </label><input type="password" name="password"/><br />
		<button type="submit">Prijavi se</button>
	
		
	</form>
</body>
</html>