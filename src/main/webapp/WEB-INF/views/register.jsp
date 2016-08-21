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
	<link rel="icon" type="image/ico" sizes="16x16" href="${root}/resources/img/favicon.ico">
	<title>Registration</title>
</head>
<body>	
	<div class="main-container">
		<div class="sbk-reg-header">
			<img src="${root}/resources/img/sbk_mvt_logo.png" /> <br />
			<span>Sign in to Sobakaisti</span>
		</div>	
		<div class="form-wrapper">
		<form id="sbk-reg-form">
			<div class="sbk-input-div">
				<input type="text" id="sbk-username" class="sbk-form-input" placeholder='<spring:message code="form.label.username"/>'/>
				<div class="sbk-err-msg"></div>
			</div>
			<div class="sbk-input-div">
				<input type="text" id="sbk-email" class="sbk-form-input" placeholder="<spring:message code="form.label.email"/>"/>
				<div class="sbk-err-msg"></div>
			</div>
			<div class="sbk-input-div">
				<input type="password" id="sbk-password" class="sbk-form-input" placeholder="<spring:message code="form.label.password"/>"/>
				<div class="sbk-err-msg"></div>
			</div>
			<div>
				<button type="submit" id="sbk-form-submit" class="sbk-btn">Sign up</button>
			</div>
		</form>
		</div>
		<div class="sbk-reg-footer">
			<span>Već ste registrovani? <a href="" class="text-link">prijavi se</a></span>
		</div>	
	</div>
			
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="${root}/resources/js/login.js"></script>
</body>
</html>