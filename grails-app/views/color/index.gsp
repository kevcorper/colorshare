<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Color Feed</title>
    <g:javascript library="jquery"/>
    <meta name="layout" content="main"/>
</head>

<body>
<h1>Welcome to your color feed</h1>
<div id ="allColors">
    <g:render template="colorBox" collection="${colors}" var="color"/>
</div>
</body>
</html>