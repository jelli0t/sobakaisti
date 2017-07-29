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
		console.log('Ucitavam pozadinu prema seledecim dimenzijama: w:'+width+' h:'+height+' char_w:'+char_width);
		background(dimension,$circle);			
	});
	
//	background(dimension,$circle);	
	
	$('.circle-menu-link').on('click', function(evt){
		evt.preventDefault();
		var url = $(this).attr('href');
		alert('URL: '+url);
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
var background = function(dimension,$circle){
	$.ajax({
		url: window.location.href+'/load_background',
		type : 'GET',
		contentType: 'application/json; charset=utf-8',
		data: dimension
	}).done(function( data ) {
//		console.log("SUCCESS: ", data);
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
		
		var $ul = $('#circle-menu');
		
		
		
//		$('li').animate({marginLeft: '0'},{
//            duration: 3000,
//            easing: 'linear'
//        }).promise().done(function(){
//        	$circle.removeClass('loader');
//        	$circle.remove();
//        	$('.circle-menu-item').css({'position':'static', 'margin':'0 15px'}).animate({
//        		opacity: '1.0'
//        	}, 1000);        	
//    	});
		$('#circle-menu').delay(3200).queue(function() {
			$circle.remove();
            $(this).expandCircleMenu();
            $(this).dequeue();
        });
		
	});
};	
/**
 * metoda prikazuje i siri menu na uvodnoj animaciji
 * */
$.fn.expandCircleMenu = function() {
	$(this).show();
	var fromLeft = 0;
	$('li', this).each(function (i, item){	
		$(this).animate({left: fromLeft+'px'},{
			duration: 500,
			easing: 'linear'
		});
		fromLeft += 110;
	});	
};