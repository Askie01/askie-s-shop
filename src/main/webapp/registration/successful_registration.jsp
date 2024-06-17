<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account created!</title>
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
<%
    final Object user = request.getAttribute("user");

    if (user == null) {
        response.sendRedirect("registration.jsp");
    }
%>
<div class="background"></div>
<div class="content">
    <h1>Your account has been created!</h1>
    <p>Click the link below to log in</p>
    <a href="../login/login.jsp" class="button">Log in</a>
</div>
</body>
</html>