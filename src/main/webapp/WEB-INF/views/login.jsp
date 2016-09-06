<%@include file="common/login-header.jsp" %>
<body>
<div class="main-container">
		<div class="sbk-reg-header">
			<img src="${root}/resources/img/sbk_mvt_logo.png" /> <br />
			<span>Log in to Sobakaisti</span>
		</div>	
		<div class="form-wrapper">
		<form id="sbk-login-form">			
			<div class="sbk-input-div">
				<input type="text" name="principal" id="sbk-username" class="sbk-form-input" placeholder='<spring:message code="form.lebel.usernameOrMail"/>'/>				
			</div>
			<div class="sbk-input-div">
				<input type="password" name="credential" id="sbk-password" class="sbk-form-input" placeholder="<spring:message code='form.label.password'/>"/>
			</div>
<!-- 			<div class="sbk-input-div"> -->
<!-- 				<input type="checkbox" id="sbk-remember" /><label>Remember me</label> -->
<!-- 			</div> -->
			<div class="sbk-err-msg" id="sbk-login-err"></div>
			<div class="form-loding"><img src="${root}/resources/img/rolling.svg" /></div>
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