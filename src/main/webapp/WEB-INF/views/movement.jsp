<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/resources/css/movement.css"  rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/movement.js"></script>
<title></title>
</head>
<body>
<div id="header-container">
	<div id="header-wrapper">
		<div id="logo-container">
			<img src="${pageContext.request.contextPath}/resources/img/_sbk_logo_s.jpg" id="sbk-logo-big" alt=""/>
			<img alt="Sobakaisti" src="${pageContext.request.contextPath}/resources/img/_sbk_logo48.jpg" id="sbk-logo-small" >
		</div>
		<div id="header-prim">
			<div id="header-nav-shrinked" ></div>
			<div id="header-search-cont" class="header-prim-box">
				<input type="text" id="header-search-input"/>
			</div>
		</div>
		<div class="header-prim-right"> 
			<ul id="header-login">
				<li>social</li>
				<li id="login-link"><a href="">log in</a></li>
			</ul>
		</div>
		<div id="header-sec">
			<ul id="header-nav-menu">
				<li class="header-nav-item"><a href="">Manifest</a></li>
				<li class="header-nav-item"><a href="">radovi</a>
					<ul class="header-nav-submenu">
					<li><a href="">Književnost</a></li>
		            <li><a href="">Sub Deep 2</a></li>
		            <li><a href="">Sub Deep 3</a></li>
		            <li><a href="">Sub Deep 4</a></li>
					</ul>
				</li>
				<li class="header-nav-item"><a href="">Autori</a>
					<ul class="header-nav-submenu">
					<li><a href="#">Andrea Kane</a></li>
		            <li><a href="#">Astor Lajka</a></li>
		            <li><a href="#">Sub Deep 3</a></li>
		            <li><a href="#">Stefan Stefanović</a></li>
		            <li><a href="#">Andrea Kane</a></li>
		            <li><a href="#">Astor Lajka</a></li>
		            <li><a href="#">Sub Deep 3</a></li>
		            <li><a href="#">Stefan Stefanović</a></li>
					</ul>
				</li>
				<li class="header-nav-item"><a href="">Serija</a></li>
				<li class="header-nav-item"><a href="">Kontakt</a></li>
			</ul>
		</div>
	</div>
</div>
<div id="test-cont"></div>
</body>
</html>