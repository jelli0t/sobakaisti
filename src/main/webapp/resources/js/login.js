$(function() {	
	$("#sbk-reg-form").on('submit', function(evt) {
		disableButton($("#sbk-form-submit"), true);	// disables submit button		
		evt.preventDefault();
		submitRegistrationForm();
	});
	$("#sbk-login-form").on('submit', function(evt) {
		disableButton($("#sbk-form-submit"), true);	// disables submit button		
		evt.preventDefault();
		submitLoginForm();
	});
	$("#sbk-reg-form input").on("focus", function(){
		$(this).addClass("input-active");
	})
});	// END of document.ready()
/*
	on submit registration form
*/
function submitRegistrationForm(){
	var credentials = {};
	var csrf = getCsrfParams();
	
	credentials["username"] = $("#sbk-username").val();
	credentials["email"] = $("#sbk-email").val();
	credentials["password"] = $("#sbk-password").val();
		
	$.ajax({
		url : 'registration',
		type : 'POST',
		dataType: 'json',
		contentType : 'application/json; charset=utf-8',
		data : JSON.stringify(credentials),		
		beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        },
		success : function(report) {
			displayErrorMsgs(report);
		},	
		error:function(er, st, msg) { 
			console.log("ERROR: ", msg);
        }
	})
		.done(function(){
			console.log("sbk@Done ~ enabling button again.");
			disableButton($("#sbk-form-submit"), false);
		});		
}
function submitLoginForm(){
	var credentials = {};
	var csrf = getCsrfParams();
	
	credentials["principal"] = $("#sbk-username").val();
	credentials["credential"] = $("#sbk-password").val();
	credentials["remembered"] = $("#sbk-remember").prop('checked');
	$.ajax({
		url : 'do.login',
		type : 'POST',
		dataType: 'json',
		contentType : 'application/json; charset=utf-8',
		data : JSON.stringify(credentials),		
		beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        },
		success : function(report) {
			displayLoginErrorMsgs(report);
		},	
		error:function(er, st, msg) { 
			console.log("ERROR: ", msg);
        }
	})
		.done(function(){
			console.log("sbk@Done ~ enabling button again.");
			disableButton($("#sbk-form-submit"), false);
		});		
}
/*
	simple set disabled property on true or false of current button.
*/
function disableButton($button, flag){
	$($button).prop("disabled", flag);
}
/*
	get -csrf parameters from meta tags
*/
function getCsrfParams() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	return [ header, token ];
}
/*
	display error box with masagges and hide it if field is filled ok
*/
function displayErrorMsgs(report){
	for(i in report){
		if(report[i].status=='ERR'){
			$(".sbk-err-msg").eq(i).show().html("<span>"+report[i].massage+"</span>");
			$(".sbk-form-input").eq(i).addClass("sbk-input-focus-red");
			console.log("sbk@error: "+report[i].status, report[i].massage);
		}else
			$(".sbk-err-msg").eq(i).hide();
	}	
	$("#sbk-reg-form input").on("keypress", function(){
		$(this).removeClass("sbk-input-focus-red").next(".sbk-err-msg").hide();
	});
}
function displayLoginErrorMsgs(report){
	if(report[0].status=='ERR'){
		$(".sbk-err-msg").eq(0).show().html("<span>"+report[0].massage+"</span>");
		$(".sbk-form-input").addClass("sbk-input-focus-red");
		console.log("sbk@error: "+report[0].status, report[0].massage);
	}else
		$(".sbk-err-msg").hide();
	
	$("#sbk-login-form input").on("keypress", function(){
		$(this).removeClass("sbk-input-focus-red").find(".sbk-err-msg").hide();
	});
}	