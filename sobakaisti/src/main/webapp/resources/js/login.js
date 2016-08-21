$(function() {
	
	$("#sbk-reg-form").on('submit', function(evt) {
		disableButton($("#sbk-form-submit"), true);	// disables submit button		
		evt.preventDefault();
		submitRegistrationForm();
	});	
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
			console.log("sbk@error: "+report[i].status, report[i].massage);
		}else
			$(".sbk-err-msg").eq(i).hide();
	}	
}