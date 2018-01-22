$(document).ready(function() {

    $('.color-display')
        .mouseover(function(event) {
            event.preventDefault();
            $target = event.$target;
            var backgroundColor = $(this).css('background');

            $('html').css('background', backgroundColor);
        })
        .mouseout(function(event) {
            event.preventDefault();
            $target = event.$target;
            $('html').css('background', "#eee");
        })
});