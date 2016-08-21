$(window).scroll(function () {
     var sc = $(window).scrollTop()
    if (sc > 103) {    	
        $("#header-container").addClass("shrink");
        $("#header-sec, #sbk-logo-big").addClass("display-none");
        $("#sbk-logo-small").addClass("display-block");
        replaceNavBar();
    } else {
        $("#header-container").removeClass("shrink");
        $("#header-sec, #sbk-logo-big").removeClass("display-none");
        $("#sbk-logo-small").removeClass("display-block");
        restoreNavBar();
    }
});
function replaceNavBar(){
	$("#header-nav-menu").appendTo($("#header-nav-shrinked"));
}
function restoreNavBar(){
	$("#header-nav-menu").appendTo($("#header-sec"));
}