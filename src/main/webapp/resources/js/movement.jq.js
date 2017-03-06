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
	
	$(document).on('scroll', function(){
	   value = $(window).scrollTop();
	   $progressBar.attr('value', value);
	});
	
	$('.header-nav-container .header-nav-bttn').on('click', function(){
		$('.header-nav', this).toggle();
	});
});


function resizeTriangle(labelWidth){
	$('#triangle-container').css({'margin-left':-labelWidth});
	$('.triangle-icon').animate({
		borderLeftWidth : labelWidth,
		borderRightWidth : labelWidth
	}, 'fast');
}

