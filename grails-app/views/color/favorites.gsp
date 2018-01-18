<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Favorite Colors</title>
    <g:javascript library="jquery"/>
    <meta name="layout" content="main"/>
</head>

<body>
<h1>Your favorite colors</h1>
<div id ="allColors">
    <g:render template="colorBox" collection="${colors}" var="color"/>
</div>
<script>
    $('.color-favorite i').on('click', function(event) {
        event.preventDefault();

        var $target = $(event.target);

        if ($target.attr('class').includes("unliked")) {
            requestOptions = {url: '/colorshare/user/addToFavorites/' + $target.parent().attr('id').substring(9)}
        } else {
            requestOptions = {url: '/colorshare/user/removeFromFavorites/' + $target.parent().attr('id').substring(9)}
        }

        requestObject = $.ajax(requestOptions);

        requestObject.done(function (response) {
            if ($target.attr('class').includes("unliked")) {
                $target.attr('class', 'fa fa-heart liked');
            } else {
                $target.attr('class', 'fa fa-heart-o unliked');
            }
        })
    });
</script>
</body>
</html>