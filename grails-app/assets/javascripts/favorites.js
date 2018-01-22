$(document).ready(function() {

    $('body').on('click', '.color-favorite i', function (event) {
        event.preventDefault();

        var $target = $(event.target);

        var unLiked = $target.attr('class').includes("unliked")

        if (unLiked) {
            requestOptions = {url: '/colorshare/user/addToFavorites/' + $target.parent().attr('id').substring(9)}
        } else {
            requestOptions = {url: '/colorshare/user/removeFromFavorites/' + $target.parent().attr('id').substring(9)}
        }

        requestObject = $.ajax(requestOptions);

        requestObject.done(function (response) {
            if (unLiked) {
                $target.attr('class', 'fa fa-heart liked');
            } else {
                $target.attr('class', 'fa fa-heart-o unliked');
            }
        })
    });
});