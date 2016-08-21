<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<title></title>
</head>