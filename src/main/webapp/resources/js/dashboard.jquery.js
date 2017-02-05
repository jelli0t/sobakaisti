$(function() {
	
	var uri;
	var $authorBox;
	
	$('#submit-author').on('click', function(){
		var csrf = getCsrfParams();
		var json = $('#author-form').serializeObject();
		
		$.ajax({
			url: 'http://localhost:8080/sobakaisti/sbk-admin/sobakaisti/add',
			type : 'POST',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(json),
			dataType : 'json',
			beforeSend: function(xhr) {
	            xhr.setRequestHeader(csrf[0], csrf[1]);
	        },
			success : function(data) {
				console.log("SUCCESS: ", data);
				closePopup();
				populateNewAuthorBox(data);
				$('#author-form')[0].reset();
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
	/* shows dropdown menu */
	$(document).on('click','.dropdown-icon', function( evt ){
		evt.preventDefault();
		$(this).next('.dropdown-menu').toggle();
	});
	 
	
	/* Delete item */
	$(document).on('click','.delete-item', function(evt){
		evt.preventDefault();
		uri = $(this).attr('href');
		$authorBox = $(this).closest('.author-box');	
		callAnchor('delete');		
		hideAllDropdowns();
		console.log('Izlazim.');
	});
	$('.dialog-yes').on('click',function(evt){
		console.log('Klknuo na YES brisem box.');
		deleteItem(uri, $authorBox);
	});
	
}); // End Of Ready

function submitForm(id){
	var csrf = getCsrfParams();
	var json = $(id).serializeObject();
//	alert(JSON.stringify(json));
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
/*
 * 	AJAX Delete function
 * */
function deleteItem(url, $authorBox){
	var csrf = getCsrfParams();	
	console.log('Brisem Autora na URI: '+url);
	$.ajax({
	    url: url,
	    type: 'DELETE',
	    beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        },
	    success: function(data){
	    	console.log("Uspesno obrisan!");
	    	$authorBox.remove();
	    	callAnchor('');
	    },
	    error: function(err){
	    	console.log(err);
	    }
	});
	
}

function populateNewAuthorBox(author){
	var $author_box = '<div class="author-box success-border"><div class="author-settings"><span class="dropdown-icon">&#xe689;</span><ul class="dropdown-menu">'
						+'<li class="dropdown-item">Edit</li>'
						+'<li class="dropdown-item"><a href="sobakaisti/delete/'+author.id+'" class="delete-item item-link">Delete</a></li>'
						+'</ul></div><div class="author-cover"></div>'
						+'<div class="author-avatar"></div>'
						+'<div class="author-content">'
						+'<div class="author-basic-info author-name"><span>'+author.firstName+'</span> <span>'+author.lastName+'</span></div>'
						+'<div class="author-basic-info author-profession">'+author.profession+'</div></div>'
						+'<div class="author-meta-info">'
						+'<span class="icon birthplace-icon">'+author.birthplace+'</span>'
						+'<span class="icon dob-icon">'+author.dob+'</span>'
						+'<span class="icon website-icon"><a href="" target="_blank"></a></span>'
						+'<span class="icon mail-icon"></span></div></div>';
	
	$($author_box).insertBefore('.dash-main-container .new-author-box').delay(2000).queue(function(next){
	    $('.success-border').removeClass("success-border");
	    next();
	});
}

function hideAllDropdowns(){$('.dropdown-menu').hide();}
