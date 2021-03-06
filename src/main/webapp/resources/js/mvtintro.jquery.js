$(function() {
	/* Poziva ucitavanje pozadinskog teksta */
	$( document ).loadBackground();
	
	$('.circle-menu-link').on('click', function(evt){
		evt.preventDefault();
		var url = $(this).attr('href');
		$(this).css({'position' : 'fixed'}).animate({
			top: '8%',
			left: '50%',
			marginLeft:'-50px'
        }, {
        	duration: 300,
	        easing: 'swing'
        }).promise().done(function(){
        	window.location.href = (url!=null ? url:'');
        });	
	});
	
	var P = 5;
	var radius = 150;
	var angleChange = 6 / P;	
	
	$('.radial-menu-item').each(function( index ) {
		index++;
		var angle = index * angleChange;
//		var x = radius * Math.cos(angle);
//		var y = radius * Math.sin(angle);
		var x = 1 - 140 * Math.cos(-0.5 * Math.PI - 2*(1/5) * index * Math.PI);
		var y = 1 + 140 * Math.sin(-0.5 * Math.PI - 2 * (1/5) * index * Math.PI);
		console.log('coordinates ('+x+', '+y+'); angle: '+angle+'; index: '+index);
		$('.radial-menu-item:nth-of-type('+index+')').css({transform: 'translate('+x+'px, '+y+'px)'});		  
	});
	
});

/**
 * funkcija koja loaduje tekst kao pozadinu
 * */
$.fn.loadBackground = function() {
	var dimension = {
			'width': $(this).width(),
			'height': $(this).height(),
			'charWidth': $('.test-char').width()
			};
	var $progress = $('.circular-progress-container > .circular-progress');
	$.ajax({
		url: window.location.href+'/load_background',
		type : 'GET',
		data: dimension,
		dataType: 'html',
		beforeSend: function(xhr) {
	        xhr.setRequestHeader('Accept', 'text/html; charset=utf-8');
	        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
	    }
	}).done(function( data ) {
		if(data) {
			/* Start circle progress animation */
			$('.circular-progress-value', $progress).addClass('circular-progress-animation');
			/* Append background rows on list */
			$('.bgd-list').append(data);
		} else {
			console.log('Data ne postoji!');
		}		
	}).fail(function( xhr, status, errorThrown ) {
		console.log( "Error: " + errorThrown );
		console.log( "Status: " + status );
		console.dir( xhr );
	}).always(function( xhr, status ) {
		console.log( "loaded: " + status );		
		/* new */
		$('nav.mvt-intro-menu').delay(2200).queue(function() {
			/* Ukloni circular progress */
			$progress.parent().remove();
			console.log( 'loading menu...' );
			/* Loading menu... */
			$(this).css({'max-width':'680px', 'opacity':'1', 'visibility':'visible'});
			$(this).dequeue();
		});
		
	});
};	
