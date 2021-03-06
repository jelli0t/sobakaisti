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
	
	/**
	 * DROP-DOWN MENU
	 * */
	$('.js-dropdown-bttn').on('click', function(evt) {
		evt.preventDefault()
		$(this).next('.js-dropdown-list').toggle();
	});
	$('.js-dropdown-close').on('click', function(evt) {
		evt.preventDefault()
		$(this).parent().parent().hide();
	});
	/** End of Drop-down */

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
		    console.log('potvrdjujem brisanje!');
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
	* Padajuci menu sa Radio opcijama
	**/
	$('.js-select-container').on('change', 'input[name=active]', function(evt){
		evt.stopPropagation();
		evt.preventDefault();
	      	var value = $(this).val();
	      	var $label = $(this).next('label').children('span:first-child').clone();
	      	if(value == 1) {
	      		$('.bttn-plain-select').empty().append($label);
	      	} else if (value == 0){
	      		$('.bttn-plain-select').empty().append($label);
	      	}
	 });
	
	$('form input[name=categories]').change(function() {
		$(this).displaySelectedCategories();
	});
	
	
	
	/**
	 * zatvara selektovani tag na 'x'
	 * */
	$('.selected-result').on('click', 'span.close-tag-bttn, span.close-cat-bttn', function(e){
		e.preventDefault();
		/* ako uklanjam kategoriju... */
		if($(this).is('span.close-cat-bttn')){			
			var checkbox_id = $(this).parent().attr('id');
			/* odcekiram checkbox sa nadjenim id */
			$('#'+checkbox_id).prop('checked', false);
		}
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
	 * Submit forme. Objavljivanje ili Draft
	 * */
	$('#submit-bttn, a#save-draft-bttn').on('click', function(evt){
		evt.preventDefault();
		var active = 0;
		if ($(this).is('#submit-bttn')) {
			console.log('[Submit] Radim objavljivanje.');
			active = 1;
		} else if ($(this).is('a#save-draft-bttn')) {
			console.log('[Submit] Radim cuvanje nacrta.');
			active = 0;
		} 		
		$('form[name="new-post-form"]').submitFormData(active);
	});
	
	$('.bttn-close').on('click', function(evt){
		evt.preventDefault();
		console.log('close!');
		$('.search-result').empty();
		$(this).parent().parent('.select-menu-modal').hide();
	});
	
	/* tag search event */
	$(document).on('keyup', '.js-search-filed', function(e) {
		e.preventDefault();
		var value = $(this).val();		
		var keyCode = e.keyCode || e.which;
		if (keyCode === 13) { 
			console.log('Pritisnuo Enter!');
			$(this).addNewTag(clicked);
			clicked++;
			return false;
		}else if(value.length > 1) {	
			console.log('Uneo tekst veci od 2 karaktera!');
			$(this).ajaxSearch();
			return false;
		}
	});
	
	/* Founded-Tag clicked! */
	var clicked = $( "#tag-count" ).length ? $('#tag-count').val() : 0;
	$('.select-menu-search-result').on('click', '.founded-tag', function(evt){
		evt.preventDefault();
		console.log('Kliknuo na tag: '+clicked+'. put');		
		$(this).appendSelectedTag(clicked);
		clicked++;
	});
	
	$(document).on('click', '.bttn-close-hover', function(evt) {
		evt.stopPropagation();
		evt.preventDefault();
		$(this).parent().remove();
	});
	
	/* Reset featured img polja */
	$('#img-prev').on('click', '.reset-img-input', function(){
		$('#img-file').val('');
		$('#img-file').next('label').html('Odaberi sliku...');
		$('#img-prev').empty();
	});
	
	/*
	 * Click on media upload bttn
	 * */
	$('.show-media-lib').on('click', function(event) {
		event.stopPropagation();
	    event.preventDefault();
	    /* kapsulira div gde treba da se ucita */
	    var $container = $('.media-select-modal');
	    $(this).switchMediaSelectionBody($container);
		callAnchor('media');		
	});
	
	$('.media-select-modal').on('click', '.media-tab', function(event) {
		event.stopPropagation();
	    event.preventDefault();
	    var $container = $('.media-repo-body');
		$(this).switchMediaSelectionBody($container);
		$('.media-tab').removeClass('media-tab-active');
		$(this).addClass('media-tab-active');
	});
	
	/* Close media upload */
	$('.media-select-modal').on('click', '#media-repo-close', function(event){
		event.stopPropagation(); 
	    event.preventDefault();
		callAnchor('');		
	});
	
	$('.media-select-modal').on('change', '#media-upload-form', function(event) {
		event.stopPropagation(); 
	    event.preventDefault();
		$(this).uploadMediaFile();		
	});
		
	/* remove media */
	$('.media-select-modal').on('click', '#bttn-media-remove', function(event) {
		event.stopPropagation(); 
	    event.preventDefault(); 
		$(this).removeMediaItem();
	}); 
	/* edit media */
	$('.media-select-modal').on('click', '#bttn-media-update', function(event) {
		event.stopPropagation(); 
	    	event.preventDefault(); 
		$('#media-edit-form').processFormData();
	});
	
	$('.media-select-modal').on('click', '#bttn-media-select', function(event) {
		event.stopPropagation(); 
    	event.preventDefault();
    	$(this).appendMediaPreview();    	
	});
	
	/* */
	$('.media-select-modal').on('change', '.media-item input', function(event) {
		event.stopPropagation(); 
	    event.preventDefault();
	    if($(this).is(':checked')) {
	    	var href = $(this).parent().find('a.media-item-link').attr('href');
			$('#bttn-media-select').removeClass('bttn-a-disabled').switchHrefValue(href);
	    } else {
	    	$('#bttn-media-select').addClass('bttn-a-disabled');
		}	    
	});
	
	$('.post-date-edit').on('click', function(event) {
		event.stopPropagation();
		event.preventDefault();
	    $(this).displayDateTimeEditor();
	});
	
	$('.post-date-editor').on('change', 'form[name=datetime-edit-form] > #post-month-field', function(event) {
		event.stopPropagation();
		event.preventDefault();
		$(this).updateDateSelect();
	});
	
	/* OOPEN Select Menu */	
	$('.js-select-container').on('click', '.js-modal-select', function(evt) {
		evt.preventDefault();
		$(this).next('.select-menu-modal-holder').toggle();
	});
	/* CLOSE Select Menu */
	$(document).on('click', '.js-menu-bttn-close', function(event) {
		event.preventDefault();
		$('.select-menu-modal-holder').hide();
	});
	
	/* Close select-menu-modal on outside clic or ESC */
	$(document).on('mouseup keydown', function (e) {
		e.stopPropagation();
  		var container = $('.select-menu-modal-holder');
  		var search_result = $('.select-menu-search-result');
  		if ((!container.is(e.target) && container.has(e.target).length === 0) || e.which == 27) {
  			container.hide();
  			search_result.empty();
  		}
	});	
	

	  if ($("#commit-result").length){ 
		  setTimeout(function(){
			  $('#commit-result').slideUp(300);
		  }, 4000);
	  }
	  
	  $('.overlay').on('click', function(e){
		  if(e.target != this) return;
		  closePopup();
	  });
	
	$('#js-edit-profile').on('click', function(evt){
		evt.preventDefault();
		$(this).loadFragmentInto('#js-editable-container');
	});
	
	/* Uklanja stavke sa liste */
	$(document).on('click', '.js-remove-item', function(evt){
		evt.preventDefault();
		removeMeta.itemId = $(this).parent().attr('id');
		removeMeta.uri = $(this).attr('data-uri');
		removeMeta.item = $('#'+removeMeta.itemId);
		/* pozivam dialog box i cekam potvrdu za brisanje*/
		$(this).confirmationDispatcher(removeMeta, function (confirmed) {
		    if (confirmed) {
		    	/* potvrdjeno brisanje pozivam delete ajax funkciju */
		    	console.log('potvrdjujem brisanje!');
		    	$(this).deleteItem(removeMeta);
		    } else
		    	callAnchor('');
		});
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

/**
* Generalna metoda za obradu podataka sa forme
*/
$.fn.processFormData = function(){
	var $form = $(this);
	var json = $form.serializeObject();
	var uri = $form.attr('action');
	var method = $form.attr('method');
	var csrf = getCsrfParams();
		
	console.log(JSON.stringify(json));
	$.ajax({
		url: uri,
		type : method,
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify(json),
		dataType : 'text',
		beforeSend: function(xhr) {
           		xhr.setRequestHeader(csrf[0], csrf[1]);
        	}
	})
	.done(function( response ) {
		$('.response-message').showResponseMessage(response, true);
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
	})
	.always(function( xhr, status ) {
		console.log( "After adding: " + status );
		
	});
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
	    dataType: 'html',
	    beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        }
	}).done(function( response ) {
		console.log("success: "+response);
		$('.commit-response-holder').attr('id', 'commit-result').append(response);
		/* uklanjam DOM el */
		$(removeMeta.item).remove();
	}).fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	    console.dir( xhr );
	}).always(function( xhr, status ) {
		callAnchor('');
		setTimeout(function(){
			  $('#commit-result').slideUp(300);
		  }, 4000);
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
	    dataType: 'html',
	    beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        }
	})
	.done(function( response ) {
		console.log("success: "+response);
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
		 $('.commit-response-holder').attr('id', 'commit-result').append(response);		
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	    console.dir( xhr );
	    $('.response-message').showResponseMessage(json, false);
	}).always(function( xhr, status ) {
		console.log( "After finish: " + status );
		setTimeout(function(){
			  $('#commit-result').slideUp(300);
			  $('.commit-response-holder').removeAttr('id').empty();
		  }, 4000);
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

/**
 * 
 * */
$.fn.toggleResponseMessage = function() {
	$(this).show().delay(4000).slideUp(350);
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


$.fn.ajaxSearch = function() {
	var uri_base = $(this).attr('src');
	var value = $(this).val();
	console.log('uri: '+uri_base+"; val: "+value);
	$.ajax({
	    url: uri_base+'search?hint='+value,
	    type: 'GET',
	    dataType: 'html'
	})
	.done(function( tags ) {
		$('.select-menu-search-result').empty().append(tags);
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	})
	.always(function( xhr, status ) {
		console.log( "After all: " + status );
	});
}

/*
 * kreira novi Tag na osnovu unete fraze
 * */
$.fn.addNewTag = function(clicked) {
	var uri = $(this).attr('src');
	var value = $(this).val();
	
	$.ajax({
	    url: uri+'add/'+value+'?index='+clicked,
	    type: 'GET',
	    dataType: 'html'
	})
	.done(function( tag ) {
		console.log("success: "+tag);
		$('.selected-tags').append($(tag).addClass('founded-tag'));
		$('.select-menu-modal-holder').hide();		
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	})
	.always(function( xhr, status ) {
		console.log( "After all: " + status );
	});
}

/**
 * Submit new post!
 * */
$.fn.submitFormData = function(active) {
	$('#overlay').toggle();
	var $form = $(this);
	var csrf = getCsrfParams();	
	var uri = $(this).attr('action');
	console.log("[URI]: "+uri);
	/* popunjavam FormData podacima sa forme */
	var data = new FormData($(this)[0]);
	var postDate = $form.makeDateObject(data);
	data.append('active', active);
	data.append('postDate', postDate);
	for (var key of data.keys()) {
		if(key === 'content'){
			data.set('content', tinyMCE.activeEditor.getContent() || '');
			break;
		}		
	}
	console.log('[Post] Radim '+(active===1 ? 'publikovanje':'čuvanje nacrta')+' članka "'+data.get('title')+'".');
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
	    else if(err.fieldName === 'featuredImg'){
	    	console.log(err.errorMessage);
	    	var $input = $('input[name=featuredImg]', $form);
	    	$('.upload-img-label').addClass('error-border');
	    	$('.upload-img-label').next('.validation-error').text(err.errorMessage).show();
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
		    image_holder.append('<span class="reset-img-input" title="Ukloni sliku"></span>');
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

$.fn.makeDateObject = function(formData) {
	var postDate = new Date();
	/* iterate trough form fields */
	for (var key of formData.keys()) {
		switch (key) {
		case 'month':
			postDate.setMonth(formData.get(key));
			break;
		case 'date':
			postDate.setDate(formData.get(key));
			break;
		case 'year':
			postDate.setFullYear(formData.get(key));
			break;
		case 'hour':
			postDate.setHours(formData.get(key));
			break;
		case 'minute':
			postDate.setMinutes(formData.get(key));
			break;
		}
	}
	console.log('Post date: '+postDate);
	return postDate;
}

/**
 * Updajtuje broj dana za selektovan mesec
 * */
$.fn.updateDateSelect = function() {
	var uri = $(this).attr('src');
	var month = $(this).val();
	$.ajax({
	    url: uri+''+month,
	    type: 'GET',	   
	    dataType: 'json'
	})
	.done(function( days ) {
		console.log("Max days per month: "+days);	
		/* update date select according to month */
		$dateSelect = $('form[name=datetime-edit-form] > #post-date-field').empty();
		for(var i=1; i <= days; i++){
			$dateSelect.append($('<option>', {value: i, text: i}, '</option>'));			
		}
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	    console.dir( xhr );
	})
}

/*
 * Ucitava telo selekcije medija 
 * na osnovu selektovanog taba
 * */
$.fn.switchMediaSelectionBody = function($container) {
	var $a = $(this);
	var url = $a.attr('href');
	console.log('uri : '+url);
	$.ajax({
	    url: url,
	    type: 'GET',	   
	    dataType: 'html'
	})
	.done(function( html ) {
		$container.empty();
		$container.append(html);
//		$('.media-repo-body').empty();
//		$('.media-repo-body').append(html);		
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	})	
}

$.fn.uploadMediaFile = function()
{
	var $form = $(this);
	var csrf = getCsrfParams();	
	var uri = $form.attr('action');
	console.log("[URI]: "+uri);
	/* popunjavam FormData podacima sa forme */
	var data = new FormData($(this)[0]);
    $.ajax({
	    url: uri,
	    type: 'POST',
	    enctype: 'multipart/form-data',
	    data: data,
	    dataType: 'html',
	    cache: false,
        processData: false, 
        contentType: false,
	    beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        }
	})
	.done(function( data ) {
		$('.media-edit-container').show(300);
		$('.media-upload-preview').empty();
		$('.media-upload-preview').append(data);	
		/* set media ID on button uri */
//		var id = $(data).find('.media-preview-editable input[type="hidden"][name="id"]').val();	
		var url = $('#media-selected-link').attr('src');
//		$('#bttn-media-select').appendValueOnHref(id);
		$('#bttn-media-select').switchHrefValue(url);
		$('#bttn-media-select').removeClass('bttn-a-disabled');
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );	    
	})
	.always(function( xhr, status ) {
		console.log("always: "+status);
		$('.response-message').toggleResponseMessage();
	});
}


$.fn.removeMediaItem = function(){
	var uri = $(this).attr('src');
	alert('Delete uri: '+uri);
	var csrf = getCsrfParams();	
	$.ajax({
	    url: uri,
	    type: 'DELETE',
	    beforeSend: function(xhr) {
            xhr.setRequestHeader(csrf[0], csrf[1]);
        }
	}).done(function( json ) {
		console.log("success: "+json);
		$('.response-message').showResponseMessage(json, true);
		$('.media-upload-preview').empty();
		$('.media-edit-container').hide(300);		
	}).fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	    console.dir( xhr );
	}).always(function( xhr, status ) {
		
	});
}

$.fn.appendMediaPreview = function() {
	var $a = $(this);
	var url = $a.attr('href');
	$.ajax({
	    url: url,
	    type: 'GET',	   
	    dataType: 'html'
	})
	.done(function( html ) {
		var className = $(html).attr('class');	
		$('#'+className).empty();
		$('#'+className).append(html);
		callAnchor('');
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	})	
}

$.fn.displayDateTimeEditor = function() {
	var $a = $(this);
	var url = $a.attr('href');
	var date = $('input[type=hidden]', $a).val();
	$.ajax({
	    url: url + '?date='+date,
	    type: 'GET',	   
	    dataType: 'html'
	})
	.done(function( html ) {
		$('.post-date-editor').empty();
		$('.post-date-editor').append(html);
		$('.post-date-editor').slideDown();
		/* append on click event */
		$('.post-date-editor').on('click', '#date-edit-accept', function(event) {
			event.stopPropagation();
			event.preventDefault();
			var dash = '-';
			var colon = ':';
			var formData = new FormData();
			$("form[name=datetime-edit-form] :input").each(function(){
				var key = $(this).attr('id');
				var value = $(this).val();
				formData.append(key, value);
			});
			var formatted_date = '';
				formatted_date += formData.get('post-year-field') + dash;
				formatted_date += (parseInt(formData.get('post-month-field')) + 1) + dash;
				formatted_date += formData.get('post-date-field') + ' ';
				formatted_date += formData.get('post-hour-field') + colon;
				formatted_date += (formData.get('post-minutes-field') < 60 ? formData.get('post-minutes-field') : 59) + colon;
				formatted_date += '00';
			//yyyy-MM-dd HH:mm:ss  
			console.log('formatted_date: '+formatted_date);
			$('#post-date-hidden').val(formatted_date);
			$('.post-date-editor').slideUp();
		});
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	})	
}

$.fn.switchHrefValue = function(value) {
	var $a = $(this);
	$a.attr('href', value);
}

$.fn.appendMediaTypeOnHref = function(type) {
	var $form = $(this);
	var raw = $form.attr('action');
	var action = raw.substr(0, raw.lastIndexOf('/')+1);	
	$form.attr('action', action + type);
	console.log('action: '+(action + type));
}

$.fn.appendValueOnHref = function(value) {
	var $a = $(this);
	var href = $a.attr('href');
	var r = /\d+/;
	var id = href.substr(href.lastIndexOf('/') + 1);
	if(id.match(/\d+/) !== null){
		href = href.replace( new RegExp(id), value );
	} else {
		href += value;
	}
	console.log('Konacni link za odabranu datoteku: ' + href );
	$a.attr('href', href);
}

$.fn.appendSelectedTag = function(index) {
	var $tag = $(this);
	var tag_id = $('input[type=hidden]', $tag).val();
	var tag_uri_prefix = $('input[type=hidden]', $tag).attr('src');
	console.log('postavljam selektovani tag sa ID: '+tag_id+'; kliknuo: '+index+'x');
	$.ajax({
	    url: tag_uri_prefix+''+tag_id+'?index='+index,
	    type: 'GET',	   
	    dataType: 'html'
	})
	.done(function( html ) {
		$('.selected-tags').append(html);
		$tag.remove();
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	})	
}

$.fn.loadFragmentInto = function(container) {
	var uri = $(this).attr('data-uri');
	$.ajax({
	    url: uri,
	    type: 'GET',
	    dataType: 'html'
	})
	.done(function( html ) {
		$(container).html(html);
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	})	
}


function loadFragment(uri, target) {
	$.ajax({
	    url: uri,
	    type: 'GET',
	    dataType: 'html'
	})
	.done(function( fragment ) {
		$(target).html(fragment);
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	});
}

function validateBeforeSubmit(form_name) {
	$form = $('form[name='+form_name+']');
	$('.input-holder > .field-err').each(function() {
		$( this ).removeClass('field-err');
	});
	var valid = $form.ajaxFormValidation();	
	console.log('Forma: '+form_name+' je validna: '+valid);
	if(valid) {
		$form.submit();
	}
}

$.fn.ajaxFormValidation = function() {
	var result = false;
	var uri = $(this).attr('data-validation-action');
	var form_name = $(this).attr('name');
	var formData = $(this).serialize();		
	$.ajax({
		url: uri,
	 	data: formData,
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        type: 'POST',
        async: false,
	    dataType: 'json'
	})
	.done(function( response ) {		
		result = true;
	})
	.fail(function( xhr, status, errorThrown ) {
		var err = xhr.responseJSON;
		$('label[for='+err.field+']').attr('data-error', err.defaultMessage).addClass('field-err');
		$('input[name='+err.field+']').addClass('field-err');
		result = false;
	});
	return result;
}


function clearContainer(target) {
	$(target).html("");
}
