$(function() {
	
	var uri;
	var $authorBox;
	var degree = 0;
	
	/*	Adds Author	*/
	$('#submit-author').on('click', function(){
		var csrf = getCsrfParams();
		var $form = $('#author-form');		
		addAuthor(csrf, $form);
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
	
	$('#author-form input').focusin(function(){
		$input = $(this);
		hideErrorMessageBox($input);
	});
	
	$('#article-title').on('focusout', function(){
		var $input = $(this);		
		makeSlugFromTitle($input);
	});
	
	
	$('#post-bttn').on('click', function(evt){
		evt.preventDefault();
		callAnchor('loading');
		var $form = $('#post-article-form');
		postArticle($form);		
	});
	
	/* Otvaranje podmenija na sidebaru */
	$('.expandable').on('click', function(evt){
		evt.preventDefault();
		degree = degree===0?90:0;
		$('.side-sub-nav').slideToggle();
		$('.rotateable').css({ WebkitTransform: 'rotate(' + degree + 'deg)'});
		$('.rotateable').css({ '-moz-transform': 'rotate(' + degree + 'deg)'});
	});
	
	$('#ser-bttn').on('click', function(){
		var json = $('#ser-test').serializeObject();
		console.log(JSON.stringify(json));
	});
	
	
	
}); // End Of Ready


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
            if(this.name === 'content'){
            	o[this.name] = tinyMCE.activeEditor.getContent() || '';
            }
            else if(this.name === 'author'){ 
            	var author = {};
            	author['id'] = this.value || '';
            	o[this.name] = author;
            }
            else if(this.name === 'categories'){ 
            	var category = [{'id':'1'},{'id':'2'}];
//            	category['id'] = this.value || '';
            	o[this.name] = category;
            }
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
/**
 * Metoda dodaje autora
 * */
function addAuthor(csrf, $form){
	var json = $form.serializeObject();
	var uri = $form.attr('action');
	
	$.ajax({
		url: uri,
		type : 'POST',
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify(json),
		dataType : 'json',
		beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        }
	}).done(function( json ) {
		console.log("Uspesno dodat autor "+json[0].firstName+' '+json[0].lastName);
		closePopup();
		populateNewAuthorBox(json);
		$form[0].reset();
	}).fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	    console.dir( xhr );
	    displayValidationErrors(xhr);
	}).always(function( xhr, status ) {
		console.log( "After adding: " + status );
	});
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
        }
	}).done(function( json ) {
		console.log("Uspesno obrisan!");
    	$authorBox.remove();
    	callAnchor('');
	}).fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	    console.dir( xhr );
	}).always(function( xhr, status ) {
		console.log( "After removing: " + status );
	});
}

function populateNewAuthorBox(author){
	var $author_box = '<div class="author-box success-border"><div class="author-settings"><span class="dropdown-icon">&#xe689;</span><ul class="dropdown-menu">'
						+'<li class="dropdown-item">Edit</li>'
						+'<li class="dropdown-item"><a href="sobakaisti/delete/'+author[0].id+'" class="delete-item item-link">Delete</a></li>'
						+'</ul></div><div class="author-cover"></div>'
						+'<div class="author-avatar"></div>'
						+'<div class="author-content">'
						+'<div class="author-basic-info author-name"><span>'+author[0].firstName+'</span> <span>'+author[0].lastName+'</span></div>'
						+'<div class="author-basic-info author-profession">'+author[0].profession+'</div></div>'
						+'<div class="author-meta-info">'
						+'<span class="icon birthplace-icon">'+author[0].birthplace+'</span>'
						+'<span class="icon dob-icon">'+author[0].dob+'</span>'
						+'<span class="icon website-icon"><a href="" target="_blank"></a></span>'
						+'<span class="icon mail-icon"></span></div></div>';
	
	$($author_box).insertBefore('.dash-main-container .new-author-box').delay(2000).queue(function(next){
	    $('.success-border').removeClass("success-border");
	    next();
	});
}

function hideAllDropdowns(){$('.dropdown-menu').hide();}

function displayValidationErrors(jqXhr){
	var errs = jqXhr.responseJSON;	
	console.log(errs);
	
	$.each(errs, function(i){
		  switch (errs[i].field) {
			case 'firstName':
				showErrorMessageBox('firstName', errs[i].defaultMessage);
				break;
			case 'lastName':
				showErrorMessageBox('lastName', errs[i].defaultMessage);
				break;
			case 'profession':
				showErrorMessageBox('profession', errs[i].defaultMessage);
				break;
			case 'birthplace':
				showErrorMessageBox('birthplace', errs[i].defaultMessage);
				break;
			case 'email':
				showErrorMessageBox('email', errs[i].defaultMessage);
				break;
			}
	});	
}
function showErrorMessageBox(field, message){
	var $input = $('#author-form input[name='+field+']');
	$input.addClass('error-border');
	$input.next('.validation-error').text(message).show();
}

function hideErrorMessageBox($input){
	$input.removeClass('error-border');
	$input.next('.validation-error').hide();
}

function makeSlugFromTitle($input){
	var title = $input.val()!==''?$input.val():'NO_DATA';
	
	var csrf = getCsrfParams();	
	var uri = $('#slug-href').attr('href');
	$.ajax({
		url: uri,
		type : 'POST',
		contentType: 'application/json; charset=utf-8',
		data: title,
		dataType : 'text',
		beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        }
	}).done(function( data ) {
		console.dir('successful converted slug to: '+data);
		$('#article-slug').val(data);
		$('#article-slug').attr('size', data.length);
	}).fail(function( xhr, status, errorThrown ) {
	    console.log("Error: " + errorThrown );
	    console.log( "Status: " + status );
//	    console.dir( xhr );
	    $('#article-slug').val('');
	    
	})
}

function postArticle($form){
	var json = $form.serializeObject();
	var uri = $form.attr('action');
	var csrf = getCsrfParams();
		
	console.log(JSON.stringify(json));
	$.ajax({
		url: uri,
		type : 'POST',
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify(json),
		dataType : 'json',
		beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        }
	}).done(function( json ) {
		$('.affirmative-notification').show(0).delay(4000).slideUp(500);
		
	}).fail(function( xhr, status, errorThrown ) {
	    console.log( "Status: " + status );
	    console.dir( xhr );
	    
	    var err = xhr.responseJSON;
	    console.log(err);
	    if(err.field === 'title'){
	    	console.log(err.defaultMessage);
	    	var $input = $('input[name=title]', $form);
	    	$input.addClass('error-border');
	    	$input.next('.validation-error').text(err.defaultMessage).show();
	    }
//	    $('#post-article-form input[name=title]').addClass('error-border');
	    
	}).always(function( xhr, status ) {
		console.log( "After adding: " + status );
		closePopup();
	});

}