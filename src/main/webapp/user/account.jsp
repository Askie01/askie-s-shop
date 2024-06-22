<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Welcome ${user.getUsername()}</title>
</head>
<body>
<a href="../home.jsp">
    <img src="../logo.jpg" alt="Profile picture"> <br>
</a>
<br>
<br>
<img src="user_profile_picture" alt="Profile picture">
<h1 class="username">User information</h1>
<p><strong>First name:</strong> ${user.getFirstName()}</p>
<p><strong>Last name:</strong> ${user.getLastName()}</p>
<p><strong>Email:</strong> ${user.getEmail()}</p>
<p><strong>Phone:</strong> ${user.getPhone()}</p>
<p><strong>Birthdate:</strong> ${user.getBirthdate()}</p>
<input type="submit" value="List products" onclick="location.href='list_products.jsp'">
<input type="submit" value="Explore products" onclick="location.href='explore_products.jsp'">
<input type="submit" value="Edit account" onclick="location.href='edit_account.jsp'">
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>
