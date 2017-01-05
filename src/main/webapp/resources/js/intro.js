$(function() {
	$('.hemisphere').delay(2000).animate({height: '360', marginTop: '-180px'},{
        duration: 1000,
        easing: 'linear'
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