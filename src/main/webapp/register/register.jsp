<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="background"></div>
<div class="content">
    <h1>Registration</h1>

    <div class="register-form">
        <form action="register" method="post">
            <div class="input-group">
                <label for="username">USERNAME</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="input-group">
                <label for="password">PASSWORD</label>
                <input type="text" id="password" name="password" required>
            </div>
            <div class="input-group">
                <label for="firstName">IMIĘ</label>
                <input type="text" id="firstName" name="firstName" required>
            </div>
            <div class="input-group">
                <label for="lastName">NAZWISKO</label>
                <input type="text" id="lastName" name="lastName" required>
            </div>
            <div class="input-group">
                <label for="birthdate">DATA URODZENIA</label>
                <input type="date" id="birthdate" name="birthdate" max="<%= java.time.LocalDate.now() %>" required>
            </div>
            <div class="input-group">
                <label for="email">ADRES EMAIL</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="input-group">
                <label for="phone">NUMER TELEFONU</label>
                <input type="tel" id="phone" name="phoneNumber" required>
            </div>
            <div class="input-group">
                <input type="submit" value="Register" class="button">
            </div>
        </form>
    </div>
</div>
</body>
</html>
