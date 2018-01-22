<%--
  Created by IntelliJ IDEA.
  User: kevin.p
  Date: 1/17/18
  Time: 11:26 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Registration</title>
    <g:javascript library="jquery"/>
    <meta name="layout" content="main"/>
</head>

<body>
<h1>Register to add your own colors and collect your favorites</h1>
<g:hasErrors>
    <div class="errors">
        <g:renderErrors bean="${user}" as="list"/>
    </div>
</g:hasErrors>
<g:if test="flash.message.length()">
    <div class="errors">
        ${flash.message}
    </div>
</g:if>
<g:form action="addUser">
    <label for="userName">User Name</label>
    <g:textField name="userName"/></br>

    <label for="password">Password</label>
    <g:passwordField name="password"/></br>

    <g:submitButton name="addUser" value="Register"/>
</g:form>
</body>
</html>