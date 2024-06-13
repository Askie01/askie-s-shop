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
        response.sendRedirect("registration_page.jsp");
    }
%>
<div class="background"></div>
<div class="content">
    <h1>Your account has been created!</h1>
    <p>Click the link below to log in</p>
    <a href="login_page.jsp" class="button">Log in</a>
</div>
</body>
</html>