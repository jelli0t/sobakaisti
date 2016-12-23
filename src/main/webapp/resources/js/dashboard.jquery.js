$(function() {
//	$('.add-bttn').on('click', function(evt) {
//		evt.preventDefault();
////		
//		$('#overlay').ScrollTo();
//	})
//	$('#submit-form').on('click', function(){
//		var csrf = getCsrfParams();
//	//	var json = $('#author-form').serializeObject();
//		var $form = $('#author-form');
//		var fd = new FormData($form);
//	
//		var csrf = getCsrfParams();
//		$.ajax({
//			url: 'http://localhost:8080/sobakaisti/sbk-admin/sobakaisti/add',
//			type : 'POST',
//			contentType: 'application/json; charset=utf-8',
////			data: JSON.stringify(json2),
//			data: fd,
//			processData: false,  // tell jQuery not to process the data
//			contentType: false,
//			dataType : 'text',
////			timeout : 100000,
////			beforeSend: function(xhr) {
////	            xhr.setRequestHeader(csrf[0], csrf[1]);
////	        },
//			success : function(data) {
//				console.log("SUCCESS: ", data);
//			},
//			error:function(er, st, msg) { 
//				console.log("ERROR: ", msg);
//				console.log("ER: ", er);
//			},
//			done : function(e) {
//				console.log("DONE");
//			}
//		});
//	});
	
	
	$('#simple-btn').on('click', function(){
		var csrf = getCsrfParams();
		var dataForUpd = {};
		dataForUpd['name'] = 'Nemanja';
		dataForUpd['username'] = 'jelles';
		dataForUpd['password'] = 'jelles123';
		dataForUpd['comment'] = 'Ovo je testni json objekat.';
		dataForUpd['date'] = '12-10-1986';
		alert(JSON.stringify(dataForUpd));
		$.ajax({
			url: 'http://localhost:8080/sobakaisti/sbk-admin/sobakaisti/test',
			type : 'POST',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(dataForUpd),
			dataType : 'text',
			beforeSend: function(xhr) {
	            xhr.setRequestHeader(csrf[0], csrf[1]);
	        },
			success : function(data) {
				console.log("SUCCESS: ", data);
			},
			error:function(er, st, msg) { 
				console.log("ERROR: ", msg);
				console.log("ER: ", er);
			}
		});
	});
	
	
	$('#submit-form').on('click', function(){
		var csrf = getCsrfParams();
//		alert(csrf[0]+" / "+ csrf[1]);
//		var json = $('#author-form').serializeObject();
//		alert(JSON.stringify(json));
	//	var json = 'Nemanja';
		
		var dataForUpd = {};			
		dataForUpd['firstName'] = $('#firstName').val();
		dataForUpd['lastName'] = $('#lastName').val();
		dataForUpd['birthplace'] = $('#birthplace').val();
		dataForUpd['dob'] = $('#dob').val();
		dataForUpd['email'] = $('#email').val();
		dataForUpd['website'] = $('#website').val();
		dataForUpd['shortBio'] = $('#shortBio').val();
		dataForUpd['profession'] = $('#profession').val();
		dataForUpd['avatarPath'] = $('#avatarPath').val();
		
		alert(JSON.stringify(dataForUpd));
		$.ajax({
			url: 'http://localhost:8080/sobakaisti/sbk-admin/sobakaisti/add',
			type : 'POST',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(dataForUpd),
			dataType : 'json',
//			timeout : 100000,
			beforeSend: function(xhr) {
	            xhr.setRequestHeader(csrf[0], csrf[1]);
	        },
			success : function(data) {
				console.log("SUCCESS: ", data);
			},
			error:function(er, st, msg) { 
				console.log("ERROR: ", msg);
				console.log("ER: ", er);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});
	 
});
function submitForm(id){
	var csrf = getCsrfParams();
	var json = $(id).serializeObject();
	alert(JSON.stringify(json));
	$.ajax({
		url: 'http://localhost:8080/sobakaisti/sbk-admin/sobakaisti/add',
		type : 'POST',
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify(json),
		dataType : 'text',
//		timeout : 100000,
		beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        },
		success : function(data) {
			console.log("SUCCESS: ", data);
		},
		error:function(er, st, msg) { 
			console.log("ERROR: ", msg);
			console.log("ER: ", er);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
	
//	closePopup();
}


function callAnchor(target){
	location.hash = target;
}

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
/* zatvara otvoreni popup */
function closePopup(){
	location.hash = '#';
}
/*
 *  Uzima vrednosti iz meta tagova na stranici i vraća parametre za CSFR request, neophodan za Spring Security.
 * */
function getCsrfParams() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	return [ header, token ];
}


