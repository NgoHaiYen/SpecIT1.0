<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Login</title>
    <link href="../css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="login">
        <h1 align="center">Login</h1>
        <form action="" method="post" style="text-align: center">
            <input type="text" placeholder="Username" id="user" name="user"><br/><br/>
            <input type="password" placeholder="Password" id="pass" name="pass"><br/><br/>
            <input type="submit" value="Login" name="submit" id="submit">
            <span><?php echo $error ?></span>
        </form>
    </div>
</body>
</html>