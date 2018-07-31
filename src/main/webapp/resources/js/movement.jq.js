$(function() {
	var labelWidth = $('.triangle-label').width();
	resizeTriangle(labelWidth/2);
		
	var winHeight = $(window).height(), 
    docHeight = $(document).height(),
    $progressBar = $('progress'),
    max, value;

	/* Set the max scrollable area */
	max = docHeight - winHeight;
	$progressBar.attr('max', max);
	
	var fifthPercent = docHeight * 0.05;
	var pastWaypoint = false;
		
	$(document).on('scroll', function(){
	   value = $(window).scrollTop();
	   $progressBar.attr('value', value);
	   
	   /* uklanja/prikazuje logo i mreze iz top navigacije */
	   if(value > fifthPercent && !pastWaypoint){
		   $('.logo-navigation, .navigation-social').show();
		   pastWaypoint = true;
	   }else if(value <= fifthPercent && pastWaypoint){
		   $('.logo-navigation, .navigation-social').hide();
		   pastWaypoint = false;
	   }
	});
	
	$('.header-nav-container .header-nav-bttn').on('click', function(){
		$('.header-nav', this).toggle();
	});
	
	$(document).on({
	    mouseenter: function () {
	        $(this).animate({marginTop:'0'}, {duration: 400, easing: 'swing' });
	    },
	    mouseleave: function () {
	    	 $(this).animate({marginTop:'-34px'}, {duration: 800, easing: 'swing' });
	    }
	}, '.header-nav-container');
	
	/* hover efekat na filterima za autore */
	$(document).on({
	    mouseenter: function () {
	    	$(this).next('.author-fullname-bubble').slideDown('fast');
	    },
	    mouseleave: function () {
	    	 $(this).next('.author-fullname-bubble').hide('fast');
	    }
	}, '.circle-filter');
	
	/* kad dohvati dno strane ucitaj jos */
	$(window).on('scroll', scrollNearToBottom);
	
	$('li.author-circle > a').on('click', function(evt) {
		evt.preventDefault();
		var selected_author = $(this).clone();		
		$('#chosen-author-box').html($(selected_author).addClass('circle').removeClass('cloud-hoverable'));
		$(this).replace_authors_content();
	});
	/**
	 * Menja polje To na kontakt formi. Postavlja puno ime autora
	 * */
	$('.js-author-contact').on('click', function(evt) {
		evt.preventDefault();
		$('.js-author-contact > span.circle-filter').removeClass('chosen-author');		
		$('#input-framed-val').attr('data-value', $(this).attr('data-title'));
		$('span.circle-filter', this).addClass('chosen-author');
	});
	
	
	$('#post-comment-bttn').on('click', function(evt) {
		evt.preventDefault();
		/* Post comment via ajax */
		$('#post-comment-form').post_comment();
	});	
	
});


function getCsrfParams() {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	return [ header, token ];
}
var endReached = false;

function resizeTriangle(labelWidth){
	$('#triangle-container').css({'margin-left':-labelWidth});
	$('.triangle-icon').animate({
		borderLeftWidth : labelWidth,
		borderRightWidth : labelWidth
	}, 'fast');
}

/* proverava da li je scroll stigao blizu dna */
function scrollNearToBottom() {
	console.log('scrollNearToBottom()');
	 if (($(document).height() - 100) <= ($(window).height() + $(window).scrollTop())) {
     	console.log('Near Bottom!');
     	$(document).loadMoreArticlesPreviews();
     }
}

/**
 *  loads more article when scroll window to bottom
 * */
$.fn.loadMoreArticlesPreviews = function() {
	if(endReached){		
		console.log('Kraj vec dostignut, izlazim iz funkcije.');
		return;
	}
	$(window).off('scroll');
	var uri = $('#load-content-link').attr('href');
	var post_counter = $('#post-counter').val();
	if(typeof uri == 'undefined') return;
	
	$.ajax({
		url: uri + '?loaded=' + post_counter,
		type : 'GET',
		dataType: 'html',
	}).done(function( posts ) {			
		if (posts) {
			console.log('Uspesno dohvacen fragment, ');
//			$('.site-content-container').adjustContentHeight();
//			$('.site-content-container').append(article);
//			$('.post-prev-item > .fadein').fadeIn(600);
			$('.mvt-rest-posts > .mvt-postprev-container').append(posts);
			var loaded = $('.mvt-postprev-content', posts).length;
			
			$('#post-counter').val(parseInt(post_counter) + parseInt(loaded));
			console.log('Loaded posts: '+loaded);
			$(window).on('scroll', scrollNearToBottom);
		}else {	
			console.log('Nemam sta da dohvatim, document je prazan! ');
			endReached = true;
		}		
	}).fail(function( xhr, status, errorThrown ) {
	    console.log("Error: " + errorThrown );
	    console.log( "Status: " + status );
	});
	
}

$.fn.adjustContentHeight = function() {
	var height = $(document).height() - $('.mvt-main-footer').height();
	console.log('doc height: '+height);
	$(this).css({'height': height+'px'});
}

/**
 * Load footer
 * */
$.fn.loadFooterAtEnd = function(height) {
	$.ajax({
		url: $('#load-footer-link').attr('href'),
		type : 'GET',
		dataType: 'html',
	}).done(function( footer ) {			
		if (footer) {
			console.log('Uspesno dohvacen footer');
			$('body').append(footer).css({'height':(height-220)+'px'});
		}else {
			console.log('Nema sta da dohvatim, document je prazan! ');
			endReached = true;
		}		
	}).fail(function( xhr, status, errorThrown ) {
	    console.log("Error: " + errorThrown );
	    console.log( "Status: " + status );
	})
}

$.fn.replace_authors_content = function() {
	var $container = $('#authors-main-content');
    $.ajax({
		url: $(this).attr('href'),
		type : 'GET',
		dataType: 'html',
	}).done(function( bio ) {			
		if (bio) {
			$container.empty();
			console.log('Uspesno dohvacen autorov bio');
			$container.hide().html(bio).fadeIn(400);
		}else {
			console.log('Nema sta da dohvatim, document je prazan! ');
		}		
	}).fail(function( xhr, status, errorThrown ) {
	    console.log("Error: " + errorThrown );
	    console.log( "Status: " + status );
	})    
}

$.fn.replace_contact_author = function() {
	var $container = $('#authors-main-content');
    $.ajax({
		url: $(this).attr('href'),
		type: 'GET',	   
	    dataType: 'json'
	}).done(function( author ) {			
		if (author) {
			$container.empty();
			console.log('Uspesno dohvacen autorov bio');
			$container.hide().html(bio).fadeIn(400);
		}else {
			console.log('Nema sta da dohvatim, document je prazan! ');
		}		
	}).fail(function( xhr, status, errorThrown ) {
	    console.log("Error: " + errorThrown );
	    console.log( "Status: " + status );
	})    
}


$.fn.post_comment = function() {
	var $form = $(this);
	var json = $(this).serializeObject();
	var uri = $(this).attr('data-action');
	var csrf = getCsrfParams();		
	$.ajax({
		url: uri,
		type : 'POST',
		contentType: 'application/json; charset=utf-8',
		data: JSON.stringify(json),
		dataType: 'html',
		beforeSend: function(xhr) {
		    xhr.setRequestHeader(csrf[0], csrf[1]);
		}
	})
	.done(function( comment ) {
		$('#commit-result').show_commit_result(true, 'Uspesno ste objavili komentar.');
		$('#js-comment-container').append(comment);
	})
	.fail(function( xhr, status, errorThrown ) {
	    console.log( "Status: " + status );
	    console.dir( xhr );
	    console.dir( xhr );
	    
	    var err = xhr.responseJSON;
	    console.log(err);
	  
//	    $('#post-article-form input[name=title]').addClass('error-border');
	    
	}).always(function( xhr, status ) {
		console.log( "After adding: " + status );		
	});
}

$.fn.show_commit_result = function(commited, messageCode) {
    $.ajax({
    	url: '/sobakaisti/commit/result?commited=' + commited + '&messageCode='+messageCode,
		type : 'GET',
		dataType: 'html',
	}).done(function( commitResult ) {
		console.log(commitResult);
//		if (commitResult) {
			$('#commit-result').html(commitResult);
//		}	
	}).fail(function( xhr, status, errorThrown ) {
	    console.log("Error: " + errorThrown );
	    console.log( "Status: " + status );
	})    
}


$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
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