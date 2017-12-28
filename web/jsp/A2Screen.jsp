<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>A2 Screen</title>

    <link href="../../css/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
    <link href="../../css/bootstrap/css/bootstrap-select.min.css" rel ="stylesheet" type="text/css">
    <link href="../css/popup.css" rel="stylesheet" type="text/css">
    <link href="../css/a2srceen.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12">
            <!-- Main content folded in a panel GROUP -->
            <div class="panel-group">
                <!--Request Detail panel -->
                <div class="panel panel-default">
                    <div class="panel-heading"> <h4><b>Request Detail</b> </h4>
                        <div class="row">
                            <div class="col-xs-6"></div>
                            <div class="col-xs-6">
                                <!-- Buttons -->
                                <button type="button" class="btn btn-default custom" id="priority-change"> <span class="glyphicon glyphicon-pencil"></span>Thay đổi mức độ ưu tiên </button>
                                <button type="button" class="btn btn-default custom" id="depart-change"> <span class="glyphicon glyphicon-envelope"></span>Thay đổi bộ phận IT</button>
                                <button type="button" class="btn btn-default custom" id="time-change"> <span class="glyphicon glyphicon-calendar"></span>Thay đổi deadline</button>
                                <button type="button" class="btn btn-default custom" id="relevant-change"> <span class="glyphicon glyphicon-user"></span>Thay đổi người liên quan</button>
                                <!-- Change State button-->
                                <select class="selectpicker custom" title="Thay đổi trạng thái">
                                    <option data-icon="glyphicon-pencil">New</option>
                                    <option data-icon="glyphicon-play">Inprogress</option>
                                    <option data-icon="glyphicon-ok">Resolved</option>
                                    <option data-icon="glyphicon-remove">Cancel</option>
                                    <option data-icon="glyphicon-refresh" disabled>Feedback</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                    <!--Request Details -->
                <div class="panel-body">

                    <div class="col-sm-4">
                        <label class="newrow">Ngày tạo:</label>20/05/2010 <br/>
                        <label class="newrow">Người yêu cầu:</label>Nguyễn Văn A<br/>
                        <label class="newrow">Mức độ ưu tiên:</label>Cao
                    </div>
                    <div class="col-sm-4">
                        <label class="newrow">Ngày hết hạn:</label>25/10/2015<br/>
                        <label class="newrow">Người thực hiện:</label>Nguyễn Văn B<br/>
                        <label class="newrow">Trạng thái:</label>Cao
                    </div>
                    <div class="col-sm-4">
                        <label class="newrow">Bộ phận IT:</label>DanangIT <br/>
                        <label class="newrow"> Người liên quan:</label>Phạm Tuấn Anh
                    </div>
                </div>
            </div>
                <!--Description Panel -->
                <div class="panel panel-default">
                    <div class="panel-heading"> <h4> Description </h4></div>
                    <div class="panel-body">
                        <!--Requested Detail -->
                        <div class="row">
                            <div class="col-xs-4">
                                <div class="media">
                                    <div class="media-left">
                                        <img src="../res/RequestByResultImage.jpg?v=123" class="img-rounded media-object" alt="profilePic" style="width:60px">
                                    </div>
                                    <div class="media-body">
                                        <h3 class="media-heading"> Trần Quốc Toản</br>
                                            <span class="glyphicon glyphicon-time"></span>
                                            <small> Created :Today 2am </small>
                                        </h3>
                                        <p>  This is this a simple DISCRIPTION</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xz-8">
                            </div>
                        </div>
                        <hr>
                        <!--Comment on this Request -->
                        <div class="row comment"> <!-- if there's a new comment ,will be added to this -->
                            <div class="col-xs-4">
                                <div class="media">
                                    <div class="media-left">
                                        <img src="../res/RequestByResultImage.jpg?v=123" class="img-rounded media-object" alt="profilePic" style="width:60px">
                                    </div>
                                    <div class="media-body">
                                        <h3 class="media-heading"> Admin trùm cuối</br> <span class="glyphicon glyphicon-time"></span> <small> Replied :Today 2 am </small></h3>
                                            <p>  Thay đổi mức độ ưu tiên</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xz-8">
                            </div>
                        </div>
                        <div class="col-sm-12">
                            <div class="form-group">
                                <textarea name="nd" id="nd"></textarea>
                                <span id="nd_error" class="errornote"></span>
                            </div>
                        </div>
                        <div class="col-sm-12">
                            <button type="submit" class="btn btn-primary" onclick=""> <span class="	glyphicon glyphicon-send"></span> Click to submit</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="../js/jquery.min.js"></script>

<script src="../../css/bootstrap/js/bootstrap.min.js"></script>
<script src="../../js/ckeditor/ckeditor.js"></script>

<script src="../../css/bootstrap/js/bootstrap-select.min.js"></script>
<script src ="../../js/popup.js"></script>
<script>
    CKEDITOR.replace('nd');
</script>
</body>
</html>