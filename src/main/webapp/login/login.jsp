<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="background"></div>
<div class="content">
    <h1>Login</h1>

    <div class="login-form">
        <form action="login" method="post">
            <div class="input-group">
                <label for="username">USERNAME</label>
                <input type="text" id="username" name="username">
            </div>
            <div class="input-group">
                <label for="password">PASSWORD</label>
                <input type="password" id="password" name="password">
            </div>
            <div class="input-group">
                <input type="submit" value="Login" class="button">
            </div>
        </form>
    </div>
</div>
</body>
</html>
