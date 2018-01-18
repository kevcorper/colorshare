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
</body>
</html>