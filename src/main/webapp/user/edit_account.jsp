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
<form action="update_account" method="post" enctype="multipart/form-data">
    <img id="profile-img" src="user_profile_picture" alt="Profile picture">
    <label for="edit-photo" class="edit-photo-btn">Edit</label>
    <input type="file" id="edit-photo" name="profile-picture" style="display: none;" accept="image/*">

    <h1 class="username">User Information</h1>
    <%--    This might generate some issues with passwords - but might be fixex with CSS.--%>
    Password <input type="password" id="password" name="password" value="${user.getPassword()}"> <br>
    First name <input type="text" id="first-name" name="first-name" value="${user.getFirstName()}"> <br>
    Last name <input type="text" id="last-name" name="last-name" value="${user.getLastName()}"> <br>
    Email <input type="email" id="email" name="email" value="${user.getEmail()}"> <br>
    phone <input type="tel" id="phone" name="phone" value="${user.getPhone()}"> <br>
    Birthdate <input type="date" id="birthdate" name="birthdate" value="${user.getBirthdate()}"> <br>
    <input type="submit" value="Save changes">
</form>
<input type="submit" value="List products" onclick="location.href='list_products.jsp'">
<input type="submit" value="Explore products" onclick="location.href='explore_products.jsp'">

<form action="delete_account" method="post">
    <input type="submit" value="Delete account">
</form>
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>
