/*ma navbar transparente fixé et blanc au défilement*/
$(document).ready(function () {
    $(window).scroll(function () {
        if ($(this).scrollTop() > 50) {
            $('.navbar').removeClass('transparent').addClass('bg-warning');
        } else {
            $('.navbar').removeClass('bg-warning').addClass('transparent');
        }
    });
});