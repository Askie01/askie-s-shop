<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User page</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="user-page">
    <header class="header">
        <div class="profile-pic">
            <img src="https://via.placeholder.com/150" alt="Zdjęcie Profilowe">
        </div>
        <h1 class="username">Username</h1>
    </header>
    <section class="user-info">
        <h2>User information</h2>
        <p><strong>First name:</strong> ${user.getFirstName()}</p>
        <p><strong>Last name:</strong> ${user.getLastName()}</p>
        <p><strong>Email:</strong> ${user.getEmail()}</p>
        <p><strong>Phone:</strong> ${user.getPhone()}</p>
        <p><strong>Birthdate:</strong> ${user.getBirthdate()}</p>
    </section>
    <section class="actions">
        <button class="action-button">List product</button>
        <button class="action-button">Explore products</button>
        <button class="action-button">Log out</button>
    </section>
</div>
</body>
</html>
