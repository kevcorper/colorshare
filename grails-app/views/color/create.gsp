<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Color Feed</title>
    <g:javascript library="jquery"/>
    <meta name="layout" content="main"/>
</head>

<body>
<h1>Add a color to the feed</h1>
<g:hasErrors>
    <div class="errors">
        <g:renderErrors bean="${color}" as="list"/>
    </div>
</g:hasErrors>
<g:form action="addColor">
    <label for="hexCode">Color Hex Code</label>
    <g:textField name="hexCode"/></br>
    <g:submitButton name="color" value="Submit"/>
</g:form>
</body>
</html>