$(function() {
	var width = $( document ).width();
	var height = $( document ).height();
	var char_width = $('.test-char').width();
	
	var $circle = $('.mvt-init-circle');
	$circle.addClass('loader').promise().done(function(){
		var dimension = {
				'width':width,
				'height':height,
				'charWidth':char_width
				};
		background(dimension,$circle);			
	});
});
/**
 * funkcija koja loaduje tekst kao pozadinu
 * */
var background = function(dimension,$circle){	
	$.ajax({
		url: 'http://localhost:8080/sobakaisti/load_background',
		type : 'GET',
		contentType: 'application/json; charset=utf-8',
		data: dimension
	}).done(function( data ) {
		console.log("SUCCESS: ", data);
		var li = '';
		$.each(data, function( i, val ){
			li+= (i % 2 === 0) ? '<li class="rtl">'+val+'</li>' : '<li class="ltr">'+val+'</li>'; 
		});
		$('.bgd-list').append(li);
		
	}).fail(function( xhr, status, errorThrown ) {
	    console.log( "Error: " + errorThrown );
	    console.log( "Status: " + status );
	    console.dir( xhr );
	}).always(function( xhr, status ) {
		console.log( "loaded: " + status );
		$('li').animate({marginLeft: '0'},{
            duration: 3000,
            easing: 'linear'
        }).promise().done(function(){
        	$circle.removeClass('loader');
        	$circle.remove();
        	$('.circle-menu-item').css({'position':'static', 'margin':'0 15px'}).animate({
        		opacity: '1.0'
        	}, 1000);        	
    	});
	});
};	