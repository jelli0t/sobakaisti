$(function() {
	var labelWidth = $('.triangle-label').width();
	resizeTriangle(labelWidth/2);
});


function resizeTriangle(labelWidth){
	$('#triangle-container').css({'margin-left':-labelWidth});
	$('.triangle-icon').animate({
		borderLeftWidth : labelWidth,
		borderRightWidth : labelWidth
	}, 'fast');
}