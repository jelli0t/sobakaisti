$(function() {
	$('.v-line').delay(2000).animate({height: '360', marginTop: '-180px'},{
        duration: 500,
        easing: 'linear'
    	}).promise().done(function(){
    		iconAnimation();		
    	});
	
	/**
	 *  Klik na krug pokreta izvrsi animaciju
	 * */	
	$('.mvt-lang-circle').on('click', function(evt){
		evt.preventDefault();
		var url = $('a', this).attr('href');
		var $circle = $(this).detach().appendTo('body').css({'left':'50%','top':'40%', 'transform':'none'});
		$('.hemisphere, .v-line').hide({
			duration: 500,
	        easing: 'linear'
		}).promise().done(function(){ window.location.href = (url!=null ? url:'') });	
//		.promise().done(function(){
//			$('.v-line').animate({height: '0', marginTop: '-50px'},{
//		        duration: 500,
//		        easing: 'linear'
//		    })
				
//    	}); 
	});	
	
	
}); // Kraj ready funkcije

var iconAnimation = function() {
	$('#movement-icon').animate({right: '40%'}, 1000).promise().done(function(){
		singleCircleAnimation();
	});
	$('#game-icon').animate({left: '40%'}, 1000).promise().done(function(){
		gameCircleAnimation();
	});
}
/*
 * Animira krugove leve hemisvere. Otvaraju se kao lepeza suprotno kazaljkama na satu.
 * */
var singleCircleAnimation = function() {
	
	$('.left-hemisphere .single-circle').animate({left: '80%', opacity: '1.0'}, {	// left: 80%
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
/*
 * 	Animira krugove DESNE hemisvere. Otvaraju se u smru kazanjlke na satu.
 * */
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
