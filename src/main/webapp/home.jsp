<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<%
    final Object user = session.getAttribute("user");

    if (user != null) {
        response.sendRedirect("user/account.jsp");
    }
%>
<h1>Home</h1>
<a href="home.jsp">
    <img src="logo.jpg" alt="Profile picture"> <br>
</a>
<a href="login/login.jsp" class="welcome-button">Login</a> <br>
<a href="registration/user_registration.jsp" class="welcome-button">Registration</a> <br>
</body>
</html>