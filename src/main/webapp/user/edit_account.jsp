<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Information</title>
    <link rel="stylesheet" href="../styles2.css">
</head>
<body>
<div class="background"></div>
<div class="user-content">
    <form class="user-info" action="update_account" method="post" enctype="multipart/form-data">
        <div class="profile-picture">
            <img id="profile-img" src="${user.getImage()}" alt="Profile picture">
            <label for="edit-photo" class="edit-photo-btn">Edit</label>
            <input type="file" id="edit-photo" name="profile-picture" style="display: none;" accept="image/*">
        </div>
        <h1 class="username">User Information</h1>
        <div class="input-group">
            <label for="first-name">First name:</label>
            <input type="text" id="first-name" name="first-name" value="${user.getFirstName()}">
        </div>
        <div class="input-group">
            <label for="last-name">Last name:</label>
            <input type="text" id="last-name" name="last-name" value="${user.getLastName()}">
        </div>
        <div class="input-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${user.getEmail()}">
        </div>
        <div class="input-group">
            <label for="phone">Phone:</label>
            <input type="tel" id="phone" name="phone" value="${user.getPhone()}">
        </div>
        <div class="input-group">
            <label for="birthdate">Birthdate:</label>
            <input type="date" id="birthdate" name="birthdate" value="${user.getBirthdate()}">
        </div>
        <div class="buttons">
            <input type="submit" value="Save changes">
        </div>
    </form>
    <div class="buttons">
        <form action="list_products">
            <input type="submit" value="List products" onclick="location.href='list_products.jsp'">
        </form>

        <form action="explore_products">
            <input type="submit" value="Explore products" onclick="location.href='explore_products.jsp'">
        </form>

        <form action="edit_account" method="post">
            <input type="submit" value="Edit account" onclick="location.href='edit_account.jsp'">
        </form>

        <form action="logout" method="post">
            <input type="submit" value="Logout" onclick="location.href='logout.jsp'">
        </form>
    </div>
</div>
<script>
    document.getElementById('edit-photo').addEventListener('change', function (event) {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById('profile-img').src = e.target.result;
            }
            reader.readAsDataURL(file);
        }
    });
</script>
</body>
</html>
