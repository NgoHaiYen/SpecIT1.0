<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SpecIT</title>

    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
    <link href="bootstrap/css/bootstrap-select.min.css" rel ="stylesheet" type="text/css">
    <link href="css/a2srceen.css" rel="stylesheet" type="text/css">
    <link href="bootstrap/css/bootstrap-datepicker.min.css">
    <link href="bootstrap/css/bootstrap-bootstrapValidator.min.css">

    <style>
        span {
            display:table;
            margin:0 auto;
        }
    </style>

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
                                <!-- Hien thi voi quyen manage-->
                                <button type="button" class="btn btn-default custom" id="priority-change" name="priority-btn" data-toggle="modal" data-target="#priorityModal"> <span class="glyphicon glyphicon-pencil"></span>Thay đổi mức độ ưu tiên </button>
                                <!-- Department Modal -->

                                <div class="modal fade" id="priorityModal" role="dialog">
                                    <div class="modal-dialog modal-sm">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Thay đổi mức độ ưu tiên</h4>
                                            </div>
                                            <div class="modal-body">
                                                <form data-toggle="validator" role="form">
                                                    <div class="form-group">
                                                        <label class="control-label">Thay đổi mức độ ưu tiên:</label>
                                                        <select class="selectpicker form-control" multiple data-max-options="1" required>
                                                            <option>Bình thường</option>
                                                            <option>Thấp</option>
                                                            <option>Cao</option>
                                                        </select>
                                                        <div class="help-block with-errors"></div>
                                                        <label class="control-label">Lí do thay đổi:</label>
                                                        <textarea class="form-control" rows="5" id="priorityComment" required></textarea>
                                                        <div class="help-block with-errors"></div>
                                                        <div class="form-group">
                                                            <button type="submit" class="btn btn-primary">Submit</button>
                                                        </div>

                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>


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
                                            <div class="modal-footer">
                                                <button type="button" class="departPopup btn btn-default" data-dismiss="modal">Close</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <button type="button" class="btn btn-default custom" id="time-change" name="deadline-btn" data-target="#deadlineModal" data-toggle="modal"> <span class="glyphicon glyphicon-calendar"></span>Thay đổi deadline</button>

                                <!--Popup to change deadline-->
                                <!-- Department Modal -->
                                <div class="modal fade" id="deadlineModal" role="dialog">
                                    <div class="modal-dialog modal-sm">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Thay đổi Deadline</h4>
                                            </div>
                                            <div class="modal-body">
                                                <form id="dForm" data-toggle="validator" role="form">
                                                    <div class="form-group">
                                                        <div class="date-form">
                                                            <label class="control-label">Deadline</label>
                                                            <div class="input-group">
                                                                <label for="Deadline" class="input-group-addon btn">
                                                                    <span class="glyphicon glyphicon-calendar"></span></label>
                                                                <input type="text" id="Deadline" name="Deadline" class="form-control date-picker" data-error="Vui lòng chọn ngày" required/></br>
                                                            </div>
                                                            <div class="help-block with-errors"></div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label">Lí do thay đổi:</label>
                                                        <textarea class="form-control" rows="5" id="deadlineComment" required></textarea>
                                                        <div class="help-block with-errors"></div>
                                                        <div class="form-group">
                                                            <button type="submit" class="btn btn-primary">Submit</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>

                                        </div>
                                    </div>
                                </div>


                                <button type="button" class="btn btn-default custom" id="relevant-change" name ="relevant-btn" data-toggle="modal" data-target="#relevanModal"> <span class="glyphicon glyphicon-user"></span>Thay đổi người liên quan</button>

                                <div class="modal fade" id="relevanModal" role="dialog">
                                    <div class="modal-dialog modal-sm">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Thay đổi người liên quan</h4>
                                            </div>
                                            <div class="modal-body">
                                                <p>Chọn người liên quan:</p>
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
                                <select class="selectpicker custom" title="Thay đổi trạng thái" id="statuschange" name="status-btn" onchange="change(this);">
                                    <option value="1" data-icon="glyphicon-pencil">New</option>
                                    <option value="2" data-icon="glyphicon-play">Inprogress</option>
                                    <option value="3" data-icon="glyphicon-ok">Resolved</option>
                                    <option value="4" data-icon="glyphicon-remove">Cancel</option>
                                    <option value="5" data-icon="glyphicon-refresh" disabled>Feedback</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                    <!--Request Details -->
                <div class="panel-body">
                    <div class="col-sm-4">
                        <label class="newrow">Ngày tạo       :</label>${request.createdAt}<br/>
                        <label class="newrow">Người yêu cầu  :</label>${request.createdByName}<br/>
                        <label class="newrow">Mức độ ưu tiên :</label>${request.priorityName}
                    </div>
                    <div class="col-sm-4">
                        <label class="newrow">Ngày hết hạn    :</label>${request.deadline}<br/>
                        <label class="newrow">Người thực hiện :</label>${request.assignedToName}<br/>
                        <label class="newrow">Trạng thái      :</label>${request.statusName}
                    </div>
                    <div class="col-sm-4">
                        <label class="newrow">Bộ phận IT      :</label>${request.branchName}<br/>
                        <label class="newrow">Người liên quan :</label>
                        <c:forEach var = "i" begin = "1" end = "${relaters.size()}">
                            <c:out value = "${relaters.get(i).name}"/>
                        </c:forEach>
                        <br/>
                        <label class="newrow">Đánh giá :</label>
                        <c:if test="${request.rating == 0}">
                            <c:out value = "Không hài lòng"/>
                        </c:if>
                        <c:if test="${request.rating == 1}">
                            <c:out value = "Hài lòng"/>
                        </c:if>
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
                                        <img src="image/${request.image}" class="img-rounded media-object" alt="profilePic" style="width:60px">
                                    </div>
                                    <div class="media-body">
                                        <h3 class="media-heading">${request.createdByName}
                                            </br>
                                            <span class="glyphicon glyphicon-time"></span>
                                            <small> Created :${request.createdAt} </small>
                                        </h3>
                                        <p>${request.content}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xz-8">
                            </div>
                        </div>
                        <hr>
                        <!--Comment on this Request -->
                        <c:forEach var="comment" items="${comments}">
                            <div class="row comment"> <!-- if there's a new comment ,will be added to this -->
                                <div class="col-xs-4">
                                    <div class="media">
                                        <div class="media-left">
                                            <img src="../res/RequestByResultImage.jpg?v=123" class="img-rounded media-object" alt="profilePic" style="width:60px">
                                        </div>
                                        <div class="media-body">
                                            <h3 class="media-heading">${comment.name}
                                                </br> <span class="glyphicon glyphicon-time"></span> <small> Replied :${comment.createdAt} </small></h3>
                                            <p>${comment.content}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xz-8">
                                </div>
                            </div>
                        </c:forEach>

                        <form data-toggle="validator">
                            <div class="col-sm-12">
                                <label>Bình luận</label>
                                <div class="form-group">
                                    <div class="form-group">
                                        <textarea class="form-control" rows="5" id="nd" name="nd" required></textarea>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-12">
                                <button type="submit" class="btn btn-primary submit" onclick="" id="submit" name="submit-btn"> <span class="	glyphicon glyphicon-send"></span> Click to submit</button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="js/jquery.min.js"></script>

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-select.min.js"></script>
<script src="bootstrap/js/growl.min.js"></script>
<script src="js/validation.js"></script>
<script src="bootstrap/js/moment.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.min.js"></script>
<script src="bootstrap/js/bootstrapValidator.min.js"></script>
<script type="text/javascript">

    $(".departPopup").click(function(){
        $.bootstrapGrowl('Đã thay đổi bộ phận IT.',{
            type: 'success',
            delay: 3000,
        });
    });


    function change(selBox) {
        if($(selBox).val() === '3') {
            var txt;
            var evaluate = prompt("Đánh giá công việc:", "Tốt");
            if (evaluate == null || evaluate == "") {
                txt = "";
            } else {
                txt = evaluate;
            }
            document.getElementById("evaluate").innerHTML = txt;
            selBox.selectedIndex = 0;
        }
    }

    $(document).ready(function() {
        $(".date-picker").datepicker();

    });



</script>
</body>
</html>