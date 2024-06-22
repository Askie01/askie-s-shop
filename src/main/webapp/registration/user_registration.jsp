<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<%
    final Object user = session.getAttribute("user");

    if (user != null) {
        response.sendRedirect("../user/account.jsp");
    }
%>
<a href="../home.jsp">
    <img src="../logo.jpg" alt="Profile picture"> <br>
</a>
<form action="register_user" method="post">
    Login <input type="text" id="login" name="login" required> <br>
    Password <input type="text" id="password" name="password" required> <br>
    Username<input type="text" id="username" name="username" required> <br>
    First name <input type="text" id="first-name" name="first-name" required> <br>
    Last name <input type="text" id="last-name" name="last-name" required> <br>
    Birthdate <input type="date" id="birthdate" name="birthdate" max="<%= java.time.LocalDate.now() %>" required> <br>
    Email <input type="email" id="email" name="email" required> <br>
    Phone <input type="tel" id="phone" name="phone" required> <br>
    <input type="submit" value="Register" class="button">
</form>
</body>
</html>
