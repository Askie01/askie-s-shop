<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account created!</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<%
    final Object successfulRegistration = request.getAttribute("successfulRegistration");

    if (successfulRegistration == null) {
        response.sendRedirect("register.jsp");
    }
%>
<div class="background"></div>
<div class="content">
    <h1>Twoje konto zostało utworzone!</h1>
    <p>Kliknij w poniższy link, żeby się zalogować</p>
    <a href="/Servlet_Hibernate_project_war_exploded/login/login.jsp" class="button">Zaloguj się</a>
</div>
</body>
</html>