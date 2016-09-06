<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome @ Sobakaland!</h1>
	<h3><a href="login">Log in</a></h3>
	
	<security:authorize access="isAuthenticated()">
		Ovaj sadržaj vide samo autorizovani korisnici
	</security:authorize>
</body>
</html>