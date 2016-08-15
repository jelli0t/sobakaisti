<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    
	<spring:url value="${pageContext.servletContext.contextPath}" var="root" context=""/>
	<link href="${root}/resources/css/registration.css" rel="stylesheet">
	
	<title>Registration</title>
</head>
<body>	
	<div class="main-container">
	<form id="sbk-reg-form">
		<div class="sbk-input-div">
			<input type="text" id="sbk-username" class="sbk-form-input" placeholder="Username"/>
			<div class="sbk-err-msg"></div>
		</div>
		<div class="sbk-input-div">
			<input type="text" id="sbk-email" class="sbk-form-input" placeholder="e-mail"/>
			<div class="sbk-err-msg"></div>
		</div>
		<div class="sbk-input-div">
			<input type="password" id="sbk-password" class="sbk-form-input" placeholder="Password"/>
			<div class="sbk-err-msg"></div>
		</div>
		<div>
			<button type="submit" id="sbk-form-submit" class="sbk-btn">Sign up</button>
		</div>
	</form>
	</div>
	
			
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="${root}/resources/js/login.js"></script>
</body>
</html>