<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
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
<form action="login" method="post">
    Login <input type="text" id="login" name="login"> <br>
    Password <input type="text" id="password" name="password"> <br>
    <input type="submit" value="Submit" class="button"> <br>
</form>
</body>
</html>