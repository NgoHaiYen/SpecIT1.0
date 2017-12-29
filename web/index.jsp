<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Login</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div id="cssmenu">
  <ul>
    <li class="active"><a href="#">Login to SpecIT</a></li>
    <li><a href="#">Products</a></li>
    <li><a href="#">Contact</a></li>
    <li><a href="#">About</a></li>
  </ul>
</div>
<div class="container">
  <div id="loginbox" style="margin-top:150px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
    <div class="panel panel-info" >
      <div class="panel-heading">
        <div class="panel-title">Welcome to SpecIT</div>
      </div>

      <div style="padding-top:30px" class="panel-body" >

        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>

        <form action="login" class="form-horizontal" role="form" method="post">

          <div style="margin-bottom: 25px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input id="user" type="text" class="form-control" name="user" value="" placeholder="username" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" required>
          </div>
          <div class="help-block with-errors"></div>

          <div style="margin-bottom: 25px" class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            <input id="login-password" type="password" class="form-control" name="pass" placeholder="password" required>
          </div>
          <div class="help-block with-errors"></div>
          <div style="margin-top:10px" class="form-group">
            <div class="col-sm-12 controls">
              <button type="submit" name="submit" href="#" class="btn btn-success">Login </button>
            </div>
          </div>

        </form>
      </div>
    </div>
  </div>
</div>
<script type="javascript" src="js/jquery.min.js"></script>
<script type="javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="javascript" scr="js/validation.js"></script>
</body>
</html>