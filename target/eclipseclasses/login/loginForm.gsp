<%--
  Created by IntelliJ IDEA.
  User: kevin.p
  Date: 1/17/18
  Time: 11:26 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Login</title>
    <g:javascript library="jquery"/>
    <meta name="layout" content="main"/>
</head>

<body>
<h1>Get to colorizing!</h1>
<g:hasErrors>
    <div class="errors">
        <g:renderErrors bean="${flash}" as="list"/>
    </div>
</g:hasErrors>
<g:form action="logIn">
    <label for="userName">User Name</label>
    <g:textField name="userName"/></br>

    <label for="password">Password</label>
    <g:passwordField name="password"/></br>

    <g:submitButton name="logIn" value="Log In"/>
</g:form>
<g:link controller="user" action="register">Register</g:link>
</body>
</html>