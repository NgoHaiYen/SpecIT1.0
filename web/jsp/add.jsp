<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Request IT</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/mainmenu.css">

    <style type="text/css">
        .day, th, .month, #date {
            cursor: pointer;
        }

        .month{
            margin: 5px;
        }

        img {
            margin: 15px;
        }
    </style>

</head>
<body>
    <jsp:include page="navigationbar.jsp" />

    <!-- Main Content -->
    <div class="container-fluid">
        <div class="side-body">
            <h1> Thêm yêu cầu </h1>
            <form method="post" id="form">
                <div class="row" id="row">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <label for="tencv">Tên công việc <span class="glyphicon glyphicon-asterisk" style="color:red"></span></label>
                            <input type="text" class="form-control" id="tencv" name="tencv" placeholder="Tên công việc">
                            <span id="tencv_error" class="errornote"></span>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label >Mức độ ưu tiên:</label><br>
                            <select class="selectpicker custom1" id="priorities">
                                <c:forEach items="${priorities}" var="priority" >
                                    <option value="${priority.id}"${priority.id == thisPriority? 'selected' : ''}>${priority.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label>Ngày hết hạn <span class="glyphicon glyphicon-asterisk" style="color:red"></span></label>
                            <div class='input-group date' id='datetimepicker' name="date">
                                <input type='text' class="form-control" />
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar" id="date"></span>
                                </span> 
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label>Bộ phận IT <span class="glyphicon glyphicon-asterisk" style="color:red"></span></label><br>
                            <select class="selectpicker custom1" name="itteam">
                                <c:forEach items="${itteams}" var="itteam" >
                                    <option value="${itteam.id}"${itteam.id == thisItteam? 'selected' : ''}>${itteam.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label>Người liên quan</label>
                            <select multiple class="selectpicker custom1" data-live-search="true">
                                <c:forEach items="${employees}" var="employee" >
                                    <option value="${employee.id}"${employee.id == thisEmployee? 'selected' : ''}>${employee.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <div class="form-group">
                            <label>Nội dung <span class="glyphicon glyphicon-asterisk" style="color:red"></span></label>
                            <textarea name="nd" id="nd"></textarea>
                            <span id="nd_error" class="errornote"></span>
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <span class="btn btn-default btn-file custom">
                            <input type="file" id="upload" accept=".jpeg,.png" onchange='changeImage()'>Chọn ảnh minh họa
                        </span>
                    </div>

                    <img id="image" src="image/image.jpeg" width="200px" height="200px">

                    <script>
                        var image = document.getElementById("image");
                        var file = document.getElementById("upload");
                        image.onclick = function(){
                            file.click();
                        }

                        changeImage = function(){
                            image.src = "image/" + file.value.split(/(\\|\/)/g).pop();
                        }
                    </script>

                    <div class="col-xs-12">
                        <div class="form-group">
                            <button type="submit" class="btn btn-info custom" id="send" name ="send-btn"><span class="glyphicon glyphicon-ok"></span> Gửi yêu cầu</button>
                            <button disabled type="submit" class="btn btn-danger custom"><span class="glyphicon glyphicon-remove"></span> Hủy bỏ</button>
                        </div>
                    </div> 
                </div>
            </form>
        </div>
    </div>
    
    <script src="js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="bootstrap/js/moment.js"></script>
    <script src="bootstrap/js/bootstrap-select.min.js"></script>
    <script src="bootstrap/js/bootstrap-datepicker.min.js"></script>
    <script src="css/ckeditor/ckeditor.js"></script>
    <script src="js/request.js"></script>
    <script>
        $(function () {

        /*Toggle bat tat slide bar*/
            $('.navbar-toggle').click(function () {
                $('.navbar-nav').toggleClass('slide-in');
                $('.side-body').toggleClass('body-slide-in');
            });
        });

        /*Chon ngay gio*/
        $(function () {
            $('#datetimepicker').datepicker();
        });

        CKEDITOR.replace('nd');
    </script>
</body>
</html>
