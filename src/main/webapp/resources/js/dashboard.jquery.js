$(function() {
	var degree = 0;
	
	// TODO staviti vam ready(), proba
	var removeMeta = new Object();		
//	var shared = $('#hidden-settings').prepare();
	
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

	/* sakriva poruke o validaciji za svako polje forme */
	$('form input, form select').focusin(function(){
		$(this).removeValidationAlert();
	});
	
	/* na focusout iz title polja napravi slug od unetog stringa */
	$('.title-input').on('focusout', function(){
		$(this).makeSlugFromTitle();
	});
	
	
	$('#post-bttn, #save-draft-bttn').on('click', function(evt){
		evt.preventDefault();
		callAnchor('loading');
		/* u zavisnosti koji bttn je kliknut postavlja vrednost active */
		if($(this).is('#post-bttn')){
			$('#post-article-form input[name=active]').val(1);
			console.log('publikujem post, postavljam active=1');
		} else if ($(this).is('#save-draft-bttn')){
			$('#post-article-form input[name=active]').val(0);
			console.log('cuvam nacrt posta, postavljam active=0');
		}
		$('#post-article-form').postArticle();
	});
	
	/**
	 * Brisanje stavki
	 * */
	$(document).on('click','.delete-item', function(evt){
		evt.preventDefault();		
		removeMeta.itemId = $(this).attr('alt');
		removeMeta.uri = $(this).attr('href');
		removeMeta.item = $('#item-'+removeMeta.itemId);		
		/* pozivam dialog box i cekam potvrdu za brisanje*/
		$(this).confirmationDispatcher(removeMeta, function (confirmed) {
		    if (confirmed) {
		    	/* potvrdjeno brisanje pozivam delete ajax funkciju */
		    	$(this).deleteItem(removeMeta);
		    } else {
		    	callAnchor('');
		    }
		});
	});
	
	$(document).on('click', '.change-status-link', function(evt){
		evt.preventDefault();
		uri = $(this).attr('href');		
		$(this).switchArticleStatus(uri);
	});
	
	
	/* Otvaranje podmenija na sidebaru */
	$('.expandable').on('click', function(evt) {
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
	/*
	 * klik na select button
	 * */	
	$(document).on('click', '.bttn-select', function(evt){
		evt.preventDefault();			
		$(this).next('.select-menu-modal').toggle();
	});
	
	$('form input[name=categories]').change(function() {
		$(this).displaySelectedCategories();
	});
	
	
	/* tag search event */
	$(document).on('keyup', '.search-field', function(e) {
		e.preventDefault();
		var value = $(this).val();
//		var key = evt.which;
//		var $searchField = $(this);
//		if(key === 13) {
//			evt.preventDefault();
//			$searchField.addNewTag();
//		} else if(value.length > 1) {
//			$searchField.ajaxSearch();
//		}
		
		var keyCode = e.keyCode || e.which;
		if (keyCode === 13) { 
			console.log('Pritisnuo Enter!');
			$(this).addNewTag();
			return false;
		}else if(value.length > 1) {			
			console.log('Uneo tekst veci od 2 karaktera!');
			$(this).ajaxSearch();
			return false;
		}
	});
	
	/**
	 * zatvara selektovani tag na 'x'
	 * */
	$('.selected-result').on('click', 'span.close-tag-bttn', function(e){
		e.preventDefault();
		$(this).parent().remove();
	});
	
	/* pri odabiru fajla iz file systema na label se postavlja ime fajla */
	$('input[type=file]').on('change', function(){
		var filename = $(this).val().split('\\').pop();
		$(this).next('label').removeValidationAlert();
		$(this).next('label').html(filename);
		
		if($(this).hasClass('input-img')){
			$(this).showUploadedImgPreview();
		}
	});

	/**
	 * Submit forme
	 * */
	$("#submit-bttn").on('click', function(evt){
		evt.preventDefault();
		console.log('Submitovao sam formu');
		$('form[name="new-post-form"]').submitFormData();
	});
	
	$('.bttn-close').on('click', function(evt){
		evt.preventDefault();
		console.log('close!');
		$('.search-result').empty();
		$(this).parent().parent('.select-menu-modal').hide();
	});
	
	
	$(document).on('click', '.founded-tag', function(evt){
		evt.preventDefault();
		console.log('Kliknuo na tag.');
//		$tag = $(this).append(shared.removeButton.clone()).removeClass('founded-tag');		
		$tag = $(this).append('<span class="close-tag-bttn"></span>').removeClass('founded-tag');
		$('.selected-tags').append($tag);
	});
	
	$(document).on('click', '.bttn-remove', function(evt){
		evt.preventDefault();
		$(this).parent('.tag').remove();
	});
		
		
}); // End Of Ready


$.fn.prepare = function(){
	var shared = new Object();
	shared.closeButton = $(this).children('.bttn-close');
	shared.removeButton = $(this).children('.bttn-remove');
	shared.alert = "IZ objekta!";
	return shared;
};

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
            	var cat = {};
            	cat['id'] = o[this.name];
                o[this.name] = [cat];
            }
            var cat = {};
        	cat['id'] = this.value || '';
        	o[this.name].push(cat);
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
            	var categoy = {};
            	categoy['id'] = this.value || '';
            	o[this.name] = [categoy];
            }
            else if(this.name === 'tags'){ 
            	var tags = {};
            	tags['id'] = this.value || '';
            	o[this.name] = [tags];
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

/**
 * Pravi ajax poziv za kreiranje slug-a od naslova
 * */
$.fn.makeSlugFromTitle = function() {
	var title = $(this).val();
	var csrf = getCsrfParams();
	var uri = $(this).attr('src');
	/* ako polje title nije prazno naravi ajax poziv */
	if(title !== '') {
		$.ajax({
			url: uri,
			type : 'PUT',
			contentType: 'application/json; charset=utf-8',
			data: title,
			dataType : 'text',
			beforeSend: function(xhr) {
	            xhr.setRequestHeader(csrf[0], csrf[1]);
	        }
		}).done(function( data ) {
			console.dir('successful converted title to slug: '+data);
			$('#slug').val(data);
			$('#slug').attr('size', data.length);
		}).fail(function( xhr, status, errorThrown ) {
		    console.log("Error: " + errorThrown );
		    console.log( "Status: " + status );
		    $('#article-slug').val('');	    
		})
	} else {
		console.log('Input polje title je prazno!!');
	}	
}

/*
 * Poziv ajax funkciji za cuvanje clanka
 * */
$.fn.postArticle = function(){
	var $form = $(this);
	var json = $(this).serializeObject();
	var uri = $(this).attr('action');
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
	})
	.done(function( json ) {
		$('.response-message').showResponseMessage('Uspešno postovan članak.', true);
	})
	.fail(function( xhr, status, errorThrown ) {
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
/**
 * funkcija za prikaz delete dialog box-a, hvatanje odgovora.
 * */
$.fn.confirmationDispatcher = function(removeMeta, callback) {
    console.log('Prikazujem delete dialog box. ');
    callAnchor('delete');
    $('.dialog-yes').on('click', function(){
    	console.log('Potvrdni button');
    	callback(true);
    });
}
/**
 * 	AJAX Delete function
 *  @param removeMeta - podaci sa linka za brisanje itema
 * */
$.fn.deleteItem = function(removeMeta){
	var csrf = getCsrfParams();	
	console.log('Brisem objekat na URI: '+removeMeta.uri);	
	$.ajax({
	    url: removeMeta.uri,
	    type: 'DELETE',
	    beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        }
	}).done(function( json ) {
		console.log("success: "+json);
		$('.response-message').showResponseMessage(json, true);
		/* uklanjam DOM el */
		$(removeMeta.item).remove();
	}).fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	    console.dir( xhr );
	}).always(function( xhr, status ) {
		callAnchor('');
	});
}
/*
 * poziva metodu za promenu statusa na clanku
 * */
$.fn.switchArticleStatus = function (uri) {
	console.log('Pozivam funkciju switchArticleStatus()');
	var articleId = $(this).attr('alt');
	var $icon = $('.article-status-icon', this);
	var csrf = getCsrfParams();	
	$.ajax({
	    url: uri,
	    type: 'PUT',
	    beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        }
	})
	.done(function( json ) {
		console.log("success: "+json);
		var src = $icon.attr('src');
		console.log("src: "+src);
		var status = parseInt(src.replace(/[^0-9\.]/g, ''), 10);
		console.log("status: "+status);
		switch (status) {
			case 0:
				console.log("case 0: "+status);
				src = src.replace(/[0-9]/g, '1'); 
				break;
			case 1:
				console.log("case 1: "+status);
				src = src.replace(/[0-9]/g, '0');
				break;
		}
		 console.log("menjam src u: "+src);
		 $icon.attr('src',src);
		 $('.response-message').showResponseMessage(json, true);		
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	    console.dir( xhr );
	    $('.response-message').showResponseMessage(json, false);
	}).always(function( xhr, status ) {
		console.log( "After finish: " + status );
	});
};
/**
 *  prikazuje poruku sa response-a
 * */
$.fn.showResponseMessage = function(message, isSuccess) {
	var className = '';
	if(isSuccess){
		console.log('postavljam pozitivan odovor');
		className = 'affirmative-message';		
	}else {
		className = 'negative-message';
	}
	$(this).addClass(className).html(message).show()
	.delay(4000).slideUp(300);
}

/*
 * popunjava box sa selektovanim kategorijama
 * */
$.fn.displaySelectedCategories = function() {
	var value = $(this).attr('title');
	if($(this).is(':checked')) {
		console.log('Odabrana kategorija: '+value); 
		var $category = '<span class="label category" id="cat_'+value+'">'+value
						+'<span class="close-tag-bttn"></span>'
						+'</span>';
		$('.selected-categories').append($category);
	}else {
		$('#cat_'+value).remove();
	}
}

//TODO sa metodom ispod napravi univerzalnu metodu za tagove
//TODO resi prikaz rezultata
$.fn.ajaxSearch = function() {
	var csrf = getCsrfParams();	
	var uri = $(this).attr('src');
	var value = $(this).val();
	console.log('uri: '+uri+"; val: "+value);
	$.ajax({
	    url: uri+'search',
	    type: 'PUT',
	    data: value,
	    contentType: 'application/json; charset=utf-8',
	    dataType: 'json',
	    beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        }
	})
	.done(function( tags ) {
		console.log("success: "+tags);
		var span = '';
		$.each(tags, function(i){
			span += '<span class="label tag founded-tag" id="tag-'+tags[i].id+'">'+tags[i].tag+'<input type="hidden" name="tags" value="'+tags[i].id+'"></span>';
		});
		$('.search-result').empty().append(span);
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	    console.dir( xhr );
	})
	.always(function( xhr, status ) {
		console.log( "After all: " + status );
	});
}

/*
 * kreira novi Tag na osnovu unete fraze
 * */
$.fn.addNewTag = function() {
	var csrf = getCsrfParams();	
	var uri = $(this).attr('src');
	var value = $(this).val();
	
	$.ajax({
	    url: uri+'add',
	    type: 'PUT',
	    data: value,
	    contentType: 'application/json; charset=utf-8',
	    dataType: 'json',
	    beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        }
	})
	.done(function( tag ) {
		$('#tags-modal').toggle();
		console.log("success: "+tag.tag);
		var span = '<span class="label tag" id="tag-'+tag.id+'">'+tag.tag+'<input type="hidden" name="tags" value="'+tag.id+'">'
					+'<span class="close-tag-bttn"></span>'
					+'</span>';
//		$('.search-result').empty().append(span);
		$('.selected-tags').append(span);
		
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	    console.dir( xhr );
	})
	.always(function( xhr, status ) {
		console.log( "After all: " + status );
	});
}

/***/
$.fn.submitFormData = function() {
	$('#overlay').toggle();
	var $form = $(this);
	var csrf = getCsrfParams();	
	var uri = $(this).attr('action');
	console.log("URI: "+uri);
	var data = new FormData($(this)[0]);
	
	for (var value of data.values()) {
		   console.log(value); 
		}
	$.ajax({
	    url: uri,
	    type: 'POST',
	    enctype: 'multipart/form-data',
	    data: data,
	    cache: false,
        processData: false, 
        contentType: false,
	    beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        }
	})
	.done(function( data ) {
		console.log("success: "+data);	
		$('.response-message').showResponseMessage(data, true);
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	    console.dir( xhr );
	    /* extract error message for fields */
	    var err = xhr.responseJSON;
	    console.log(err);
	    if(err.fieldName === 'title'){
	    	console.log(err.errorMessage);
	    	var $input = $('input[name=title]', $form);
	    	$input.addClass('error-border');
	    	$input.next('.validation-error').text(err.errorMessage).show();
	    }else if(err.fieldName === 'author'){
	    	console.log(err.errorMessage);
	    	var $input = $('select[name=author]', $form);
	    	$input.addClass('error-border');
	    	$input.next('.validation-error').text(err.errorMessage).show();
	    }
	    else if(err.fieldName === 'file'){
	    	console.log(err.errorMessage);
	    	var $input = $('input[name=file]', $form);
	    	$('#upload-label').addClass('error-border');
	    	$('#upload-label').next('.validation-error').text(err.errorMessage).show();
	    }
	})
	.always(function( xhr, status ) {
		$('#overlay').toggle();
		console.log( "After all: " + status );
	});
}

$.fn.showUploadedImgPreview = function() {
	
	var imgPath = $(this)[0].value;
	var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
	if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
		if (typeof (FileReader) != "undefined") {
			var image_holder = $("#img-prev");
		   
		    image_holder.empty();
		
		    var reader = new FileReader();
		    reader.onload = function (e) {
		        $("<img />", {
		        "src": e.target.result,
		        "class": "thumb-image"
		        }).appendTo(image_holder);
		
		    }
		    image_holder.show();
		    reader.readAsDataURL($(this)[0].files[0]);
		} else {
		    alert("This browser does not support FileReader.");
		}
	 }else {
		 var $input = $(this).next('label');
		 $input.empty();
		 $input.addClass('error-border');
		 $input.next('.validation-error').text('Nije podrzan format fotografije!').show();
	 }
}


/*
 * Uklanja poruke o validaciji polja.
 * */
$.fn.removeValidationAlert = function() {
	$(this).removeClass('error-border');
	$(this).next('.validation-error').empty().hide();
}

