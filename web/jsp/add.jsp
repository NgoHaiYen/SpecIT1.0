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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.css" rel="stylesheet"/>

    <style type="text/css">
        .day, th, .month, #date {
            cursor: pointer;
        }

        .month{
            margin: 5px;
        }

        img {
            visibility: hidden;
            margin-top: 10px;
            min-width: 100%;
            height: auto;
        }
    </style>

</head>
<body>
    <jsp:include page="navigationbar.jsp" />

    <!-- Main Content -->
    <div class="container-fluid">
        <div class="side-body">

            <form method="post" id="form" data-toggle="validator">
                <div class="row" id="row">
                    <h1> Thêm yêu cầu </h1>

                    <div class="col-sm-12">
                        <div class="form-group">
                            <label for="tencv">Tên công việc <span class="glyphicon glyphicon-asterisk" style="color:red"></span></label>
                            <input type="text" class="form-control" id="tencv" name="tencv" placeholder="Tên công việc" required>
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label >Mức độ ưu tiên:</label><br>
                            <select class="selectpicker form-control" id="priorities" name="priorities">
                                <c:forEach items="${priorities}" var="priority" >
                                    <option value="${priority.id}">${priority.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label>Ngày hết hạn <span class="glyphicon glyphicon-asterisk" style="color:red"></span></label>
                            <div class='input-group date' id='datetimepicker'>
                                <input type='text' class="form-control" name="date" data-error="Vui lòng chọn ngày" required/>
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar" id="date"></span>
                                </span>

                            </div>
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label>Team <span class="glyphicon glyphicon-asterisk" style="color:red"></span></label><br>
                            <select class="selectpicker form-control" data-live-search="true" name="subteams" title="Choose one of the following..." required>
                                <c:forEach items="${subteams}" var="subteam" >
                                    <option value="${subteam.id}">${subteam.name}</option>
                                </c:forEach>
                            </select>
                            <div class="help-block with-errors"></div>
                        </div>

                    </div>

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label>Người liên quan</label>
                            <select multiple class="selectpicker form-control" data-live-search="true" name="relater">
                                <c:forEach items="${employees}" var="employee" >
                                    <option value="${employee.id}">${employee.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="col-sm-12">
                        <div class="form-group" id="textform">
                            <label>Nội dung <span class="glyphicon glyphicon-asterisk" style="color:red"></span></label>
                            <textarea name="nd" id="nd" class="nd" required></textarea>
                            <div class="help-block with-errors"></div>
                            <div id="trackingDiv"></div>
                        </div>
                    </div>


                    <div class="col-sm-12">
                        <div class="form-group">
                            <span class="btn btn-default btn-file custom upload" style="margin-top: 30px; margin-left: 20px;">
                                <input class="form-control" type="file" name="upload" id="upload" accept=".jpeg,.png" onchange='changeImage(this)'>Chọn ảnh minh họa
                            </span>
                            <img id="image" src="image/image.jpeg"/>
                        </div>
                    </div>



                    <script>
                        var image = document.getElementById("image");
                        var file = document.getElementById("upload");
                        image.onclick = function(){
                            file.click();
                        };

                        changeImage = function(input){
                            if (input.value == "") return;
                            image.src = "image/" + input.value.split(/(\\|\/)/g).pop();
                            image.style.visibility='visible';
                            document.getElementsByClassName("upload")[0].style.visibility = 'hidden';
                            if (input.files && input.files[0]) {
                                var reader = new FileReader();
                                reader.onload = function (e) {
                                    $('#image')
                                        .attr('src', e.target.result)
                                        .width(150)
                                        .height(200);
                                };
                                reader.readAsDataURL(input.files[0]);
                            }
                        }
                    </script>
                    <div class="col-xs-12">
                        <div class="form-group">
                            <button type="submit" name ="addrequest" value="add" class="btn btn-info custom" id="send"><span class="glyphicon glyphicon-ok"></span> Gửi yêu cầu</button>
                            <button disabled type="submit" name ="addrequest" value="cancel" class="btn btn-danger custom"><span class="glyphicon glyphicon-remove"></span> Hủy bỏ</button>
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
    <script src="js/validation.js"></script>
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


        timer = setInterval(updateDiv,100);
        function updateDiv(){
            var editorText = CKEDITOR.instances.nd.getData();
            $('#trackingDiv').html(editorText);
        }



    </script>
</body>
</html>
