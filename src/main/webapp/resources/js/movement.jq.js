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
	$(window).scroll(function() {
        if ($(document).height() <= ($(window).height() + $(window).scrollTop())) {
        	console.log('Bottom!');
        	$(document).loadMoreArticlesPreviews();
        }
    });
	
});

var endReached = false;

function resizeTriangle(labelWidth){
	$('#triangle-container').css({'margin-left':-labelWidth});
	$('.triangle-icon').animate({
		borderLeftWidth : labelWidth,
		borderRightWidth : labelWidth
	}, 'fast');
}

/**
 *  loads more article when scroll window to bottom
 * */
$.fn.loadMoreArticlesPreviews = function() {
	if(endReached){		
		console.log('Kraj vec dostignut, izlazim iz funkcije.');
		return;
	}
	var uri = $('#load-content-link').attr('href');
	if(typeof uri == 'undefined') return;
	
	$.ajax({
		url: uri,
		type : 'GET',
		dataType: 'html',
	}).done(function( article ) {			
		if (article) {
			console.log('Uspesno dohvacen fragment, ');
			$('.site-content-container').adjustContentHeight();
			$('.site-content-container').append(article);
		}else {
//			alert('Visina dokumenta '+$(document).height());			
			console.log('Nema sta da dohvatim, document je prazan! ');
			endReached = true;
		}		
	}).fail(function( xhr, status, errorThrown ) {
	    console.log("Error: " + errorThrown );
	    console.log( "Status: " + status );
	})
	
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

