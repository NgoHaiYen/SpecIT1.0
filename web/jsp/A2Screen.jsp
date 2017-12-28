<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>A2 Screen</title>

    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
    <link href="bootstrap/css/bootstrap-select.min.css" rel ="stylesheet" type="text/css">
    <link href="css/popup.css" rel="stylesheet" type="text/css">
    <link href="css/a2srceen.css" rel="stylesheet" type="text/css">

    <!--popup -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-growl/1.0.0/jquery.bootstrap-growl.min.js"></script>
</head>
<body>
<div class="container-fluid">

    <include file="navigationbar.jsp"/>

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
                                <button type="button" class="btn btn-default custom" id="priority-change" name="priority-btn"> <span class="glyphicon glyphicon-pencil"></span>Thay đổi mức độ ưu tiên </button>
                                <button type="button" class="btn btn-default custom" data-toggle="modal" id="depart-btn" name="depart-btn" data-target="#departModal"> <span class="glyphicon glyphicon-envelope"></span>Thay đổi bộ phận IT</button>

                                <!-- Department Modal -->
                                <div class="modal fade" id="departModal" role="dialog">
                                    <div class="modal-dialog modal-sm">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Thay đổi bộ phận IT</h4>
                                            </div>
                                            <div class="modal-body">
                                                <p>Chọn bộ phận IT:</p>
                                                <!-- Lay du lieu tu csdl tu day-->
                                                <select class="selectpicker">
                                                    <option>IT Hà Nội</option>
                                                    <option>IT Đà Nẵng</option>
                                                </select>
                                            </div>

                                            <!-- Khi click close, thong bao gui cho nguoi duoc nhan assign neu thanh cong popup => susscess assignPopup duoi script .assignPopUp-->
                                            <div class="modal-footer">
                                                <button type="button" class="assignPopup btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <button type="button" class="btn btn-default custom" id="time-change" name ="deadline-btn"> <span class="glyphicon glyphicon-calendar"></span>Thay đổi deadline</button>
                                <button type="button" class="btn btn-default custom" id="relevant-change" name ="relevant-btn"> <span class="glyphicon glyphicon-user"></span>Thay đổi người liên quan</button>
                                <!-- Trigger the modal assign with a button-->
                                <button type="button" class="btn btn-default custom" data-toggle="modal" data-target="#assignModal"><span class="glyphicon glyphicon-pencil"></span>Assign</button>

                                <!--  Assign Modal -->
                                <div class="modal fade" id="assignModal" role="dialog">
                                    <div class="modal-dialog modal-sm">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Assign</h4>
                                            </div>
                                            <div class="modal-body">
                                                <p>Chọn người thực hiện:</p>
                                                <!-- Lay du lieu tu csdl tu day-->
                                                <select class="selectpicker">
                                                    <option>Mustard</option>
                                                    <option>Ketchup</option>
                                                    <option>Relish</option>
                                                </select>
                                            </div>

                                            <!-- Khi click close, thong bao gui cho nguoi duoc nhan assign neu thanh cong popup => susscess assignPopup duoi script .assignPopUp-->
                                            <div class="modal-footer">
                                                <button type="button" class="assignPopup btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- Change State button-->
                                <select class="selectpicker custom" title="Thay đổi trạng thái" id="statuschange" name="status-btn">
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
                            <button type="submit" class="btn btn-primary" onclick="" id="submit" name="submit-btn"> <span class="	glyphicon glyphicon-send"></span> Click to submit</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="../js/jquery.min.js"></script>

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/ckeditor/ckeditor.js"></script>

<script src="bootstrap/js/bootstrap-select.min.js"></script>
<script src ="js/popup.js"></script>
<script>
    CKEDITOR.replace('nd');
    $(".assignPopup").click(function(){
        $.bootstrapGrowl('Thông báo đã được gửi tới A.',{
            type: 'success',
            delay: 3000,
        });
    });
</script>
</body>
</html>