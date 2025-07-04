<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login & Sign Up</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div class="login-wrap">
    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
        <label for="tab-1" class="tab">Sign In</label>
        <input id="tab-2" type="radio" name="tab" class="sign-up">
        <label for="tab-2" class="tab">Sign Up</label>
        <div class="login-form">

            <!-- Sign In Form -->
            <form class="sign-in-htm" method="post" action="UsersController">
                <input type="hidden" name="action" value="login">

                <div class="group">
                    <label for="user1" class="label">Username</label>
                    <input id="user1" name="username" type="text" class="input" required>
                </div>
                <div class="group">
                    <label for="email1" class="label">Email</label>
                    <input id="email1" name="email" type="email" class="input" required>
                </div>
                <div class="group">
                    <label for="pass2" class="label">Password</label>
                    <input id="pass2" name="password" type="password" class="input" required>
                </div>
                <div class="group">
                    <input id="check" type="checkbox" class="check" checked>
                    <label for="check"><span class="icon"></span> Keep me Signed in</label>
                </div>
                <div class="group">
                    <input type="submit" class="button" value="Sign In">
                </div>
                <div class="hr"></div>

                <%-- Optional error message --%>
                <c:if test="${param.error == 'true'}">
                    <p style="color:red; text-align:center;">Invalid username or password.</p>
                </c:if>
            </form>

            <!-- Sign Up Form -->
            <form class="sign-up-htm" method="post" action="UsersController">
                <input type="hidden" name="action" value="add-user">

                <div class="group">
                    <label for="user" class="label">Username</label>
                    <input id="user" name="username" type="text" class="input" required>
                </div>
                <div class="group">
                    <label for="email" class="label">Email</label>
                    <input id="email" name="email" type="email" class="input" required>
                </div>
                <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input id="pass" name="password" type="password" class="input" required>
                </div>
                <div class="group">
                    <input type="submit" class="button" value="Sign Up">
                </div>
                <div class="hr"></div>
            </form>

        </div>
    </div>
</div>
</body>
</html>
