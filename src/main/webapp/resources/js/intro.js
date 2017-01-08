$(function() {
	$('.v-line').delay(2000).animate({height: '360', marginTop: '-180px'},{
        duration: 500,
        easing: 'linear'
    	}).promise().done(function(){
    		iconAnimation();		
    	});    
	
	$('.single-circle').on('click', function(){
		$('.hemisphere, .v-line').hide('slow');
		var $circle = $(this).detach().appendTo('body').css({'left':'50%','top':'40%', 'transform':'none'});
	});
	
	
	var width = $( window ).width();
	var height = $( window ).height();
	var char_w = $('#char-width').width();
		
	console.log("CHAR e WIDTH: ", char_w);
	var dimension = {
		'width':width,
		'height':height
		};
	
	$.ajax({
		url: 'http://localhost:8080/sobakaisti/load_background',
		type : 'GET',
		contentType: 'application/json; charset=utf-8',
		data: dimension,
		success : function(data) {
			console.log("SUCCESS: ", data);
			var li = '';
			$.each(data, function( i, val ){
				li+= (i % 2 === 0) ? '<li class="rtl">'+val+'</li>' : '<li class="ltr">'+val+'</li>'; 
			});
			$('.bgd-list').append(li);						
		},
		error:function(er, st, msg) { 
			console.log("ERROR: ", msg);
			console.log("ER: ", er);
		}		
	}).done(function() {
		console.log("done!");
		$('li').animate({marginLeft: '0'},{
            duration: 3000,
            easing: 'linear'
        }, {complete: circleFadeIn()});		
		});
	
});

var circleFadeIn = function() {
	$('.single-circle').animate({opacity: 1}, 1000);
}
var iconAnimation = function() {
	$('#movement-icon').animate({right: '40%'}, 1000).promise().done(function(){
		singleCircleAnimation();
	});
	$('#game-icon').animate({left: '40%'}, 1000).promise().done(function(){
		gameCircleAnimation();
	});
}

var singleCircleAnimation = function() {
	$('.left-hemisphere .single-circle').animate({left: '80%', opacity: '1.0'}, {
        duration: 500       
    }).promise().done(function(){
    	for(i=0, deg=0; i<8; i++, deg-=45){
    		var id = '.mvt-langwrapper-'+i;
    		$(id).css({
    			transition: '.4s linear',
                transform: 'rotate(' + deg + 'deg)'
            });
    		$(id+' .single-circle').css({
                transform: 'rotate(' + Math.abs(deg) + 'deg)'
            });    		
    	}		
	});	
}
	
var gameCircleAnimation = function() {
	$('.right-hemisphere .single-circle').animate({left: '20%', opacity: '1.0'}, {
        duration: 500       
    }).promise().done(function(){
    	for(i=0, deg=0; i<8; i++, deg+=45){
    		var id = '.game-langwrapper-'+i;
    		$(id).css({
    			transition: '.4s linear',
                transform: 'rotate(' + deg + 'deg)'
            });
    		$(id+' .single-circle').css({
                transform: 'rotate(' + deg*-1 + 'deg)'
            });    		
    	}		
	});
}	
