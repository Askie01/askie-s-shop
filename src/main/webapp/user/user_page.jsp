<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Information</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="background"></div>
<div class="content">
    <div class="user-info">
        <div class="profile-picture">
            <img src="https://via.placeholder.com/150" alt="Profile picture">
        </div>
        <h1 class="username">User information</h1>
        <p><strong>First name:</strong> ${user.getFirstName()}</p>
        <p><strong>Last name:</strong> ${user.getLastName()}</p>
        <p><strong>Email:</strong> ${user.getEmail()}</p>
        <p><strong>Phone:</strong> ${user.getPhone()}</p>
        <p><strong>Birthdate:</strong> ${user.getBirthdate()}</p>
    </div>
    <div class="buttons">
        <form action="list_products">
            <input type="submit" value="List products" onclick="location.href='list_products'">
        </form>

        <form action="explore_products">
            <input type="submit" value="Explore products" onclick="location.href='explore_products'">
        </form>

        <form action="logout" method="post">
            <input type="submit" value="Logout" onclick="location.href='logout'">
        </form>
    </div>
</div>
</body>
</html>
