<%@include file="common/login-header.jsp" %>
<body>
	<form action="login_process" method="POST">
		<label>Username: </label><input type="text" name="username"/><br />
		<label>Password: </label><input type="password" name="password"/><br />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>		
		<button type="submit">Prijavi se</button>
	</form>
<div class="main-container">
		<div class="sbk-reg-header">
			<img src="${root}/resources/img/sbk_mvt_logo.png" /> <br />
			<span>Log in to Sobakaisti</span>
		</div>	
		<div class="form-wrapper">
		<form id="sbk-login-form">
			<div class="sbk-input-div">
				<input type="text" id="sbk-username" class="sbk-form-input" placeholder='<spring:message code="form.lebel.usernameOrMail"/>'/>
				<div class="sbk-err-msg"></div>
			</div>
			<div class="sbk-input-div">
				<input type="password" id="sbk-password" class="sbk-form-input" placeholder="<spring:message code='form.label.password'/>"/>
				<div class="sbk-err-msg"></div>
			</div>
			<div>
				<button type="submit" id="sbk-form-submit" class="sbk-btn">Log in</button>
			</div>
		</form>
		</div>
		<div class="sbk-reg-footer">
			<span>Nemate nalog? <a href="" class="text-link">registruj se</a></span>
		</div>	
	</div>
			
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="${root}/resources/js/login.js"></script>


</body>
</html>