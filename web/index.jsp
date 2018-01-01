<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <link rel="stylesheet" href="css/login.css"><link rel="shortcut icon" href="img/bug-fixing.png" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
          <p style="color:red"><c:out value="${errMe}"/></p>
          <div class="help-block with-errors"></div>
          <div style="margin-top:10px" class="form-group">
            <div class="col-sm-12 controls">
              <button type="submit" name="submit" href="#" class="btn btn-success">Login </button>
              <a href="#" data-target="#myModal" data-toggle="modal">Quên mật khẩu?</a>
            </div>
          </div>
        </form>

          <div id="myModal" class="modal fade" role="dialog">
              <div class="modal-dialog">
                  <!-- Modal content-->
                  <div class="modal-content">
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal">&times;</button>
                          <h4 class="modal-title">Quên mật khẩu</h4>
                      </div>
                      <div class="modal-body">
                          <p>Hãy điền email của bạn vào đây: </p>
                          <input class="form-control" id="forgotemail" name="email">
                          <p id="error" style="color:red"></p>
                      </div>
                      <div class="modal-footer">
                          <button id="forgot" name="forgotemail" value="forgot" onclick="requestEmail()" class="btn btn-default">Gửi</button>
                      </div>
                  </div>
              </div>
          </div>
      </div>
    </div>
  </div>
</div>



<script type="javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script type="javascript" src="js/validation.js"></script>
<script type="javascript" src="js/jquery.min.js"></script>

<script>
    function requestEmail(){
        $.ajax({
            type:"POST",
            cache:false,
            data: {
                forgotemail: $('#forgotemail')
            },
            url:"http://localhost:8080/SpecIT/login",
            success : function(responseText) {
                alert("Hãy kiểm tra lại email của bạn");
            }
        }).fail(function($xhr) {
            alert("Failed");
        });
    }
</script>
</body>
</html>