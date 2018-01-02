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
    <link href="css/login.css" rel="stylesheet">

    <style>
        span {
            display:table;
            margin:0 auto;
        }
    </style>

</head>
<body>
    <!--nav header-->
    <div id="cssmenu">
        <ul>
            <li class="active"><a href="list"> SpecIT</a></li>
            <li><a href="#">You have logged in as ${username}</a></li>
            <li><a href="#">Contact</a></li>
            <li><a href="#">About</a></li>
            <li style="float: right"><a href="login" onclick="logout()">Logout</a> </li>
        </ul>
    </div>

    <!--body-->
    <div class="container">
            <!-- Main buttons in a panel GROUP -->
        <div class="panel panel-default">
            <div class="panel-heading custompanel">
                <h4><a href="list">SpecIT</a></h4>
                <!-- Buttons -->
                <!-- Hien thi voi quyen manage role >3 ,co kha nang edit-->
                <c:if test="${id == request.createdBy}">
                    <div class="prioritybtn btncustom">
                    <button type="button" class="btn btn-default custom" id="priority-change" name="priority-btn" data-toggle="modal" data-target="#priorityModal">
                        <span class="glyphicon glyphicon-pencil"></span>Thay đổi mức độ ưu tiên
                    </button>
                    <!-- Department Modal -->
                    <div class="modal fade" id="priorityModal" role="dialog">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Thay đổi mức độ ưu tiên</h4>
                                </div>
                                <div class="modal-body">
                                    <form onsubmit="return false;" data-toggle="validator" role="form">
                                        <label class="control-label">Thay đổi mức độ ưu tiên:</label>
                                        <div class="form-group">
                                            <select class="selectpicker form-control" id="priorities" required>
                                                <c:forEach items="${priorities}" var="priority" >
                                                    <option value="${priority.id}" ${priority.id == request.priority ? 'selected' : ''}>${priority.name}</option>
                                                </c:forEach>
                                            </select>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <label class="control-label">Lí do thay đổi:</label>
                                        <div class="form-group">
                                            <textarea class="form-control" rows="5" name="reasonPriority" id="priorityComment" required></textarea>
                                            <div class="help-block with-errors"></div>
                                        </div>

                                        <div class="modal-footer">
                                            <button onclick="postajax('priority', ${request.id}, $('#priorities').val(), $('#priorityComment').val())" class="prioriPopup btn btn-primary">Submit</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:if>

                <c:if test="${role >= 3}">
                <div class="departbtn btncustom">
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
                                    <select class="selectpicker" id="branches" required>
                                        <c:forEach items="${branches}" var="branch" >
                                            <option value="${branch.id}"${branch.id == request.branchId? 'selected' : ''}>${branch.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" onclick="postajax('branch', ${request.id}, $('#branches').val())" class="departPopup btn btn-primary" data-dismiss="modal">Submit</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="subteambtn btncustom">
                    <button type="button" class="btn btn-default custom" data-toggle="modal" id="subteam-btn" name="depart-btn" data-target="#subteamModal"> <span class="glyphicon glyphicon-list-alt"></span>Subteam</button>
                    <!-- Department Modal -->
                    <div class="modal fade" id="subteamModal" role="dialog">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Thay đổi team:</h4>
                                </div>
                                <div class="modal-body">
                                    <p>Chọn team thay đổi:</p>
                                    <!-- Lay du lieu tu csdl tu day-->
                                    <select class="selectpicker" id="subteam" required>
                                        <c:forEach items="${subteams}" var="subteam" >
                                            <option value="${subteam.id}"${subteam.id == request.teamId? 'selected' : ''}>${subteam.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" onclick="postajax('branch', ${request.id}, $('#subteam').val())" class="subteamPopup btn btn-primary" data-dismiss="modal">Submit</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <c:if test="${id == request.createdBy}">
                    <div class="deadlinebtn btncustom">
                    <button type="button" class="btn btn-default custom" id="time-change" name="deadline-btn" data-target="#deadlineModal" data-toggle="modal">
                        <span class="glyphicon glyphicon-calendar"></span>Thay đổi deadline
                    </button>
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
                                    <form id="dForm" onsubmit="return false;" data-toggle="validator" role="form">
                                        <div class="form-group">
                                            <div class="date-form">
                                                <label class="control-label">Deadline</label>
                                                <div class="input-group">
                                                    <label for="deadline" class="input-group-addon btn">
                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                    </label>
                                                    <input type="text" id="deadline" name="deadline" class="form-control date-picker" data-error="Vui lòng chọn ngày" required/></br>
                                                </div>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Lí do thay đổi:</label>
                                            <div class="form-group">
                                                <textarea class="form-control" rows="5" id="deadlineComment" required></textarea>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit" onclick="postajax('deadline', ${request.id}, $('#deadline').val(), $('#deadlineComment').val())" class="deadlinePopup btn btn-primary">Submit</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:if>

                <div class="relevantbtn btncustom">
                    <button type="button" class="btn btn-default custom" id="relevant-change" name ="relevant-btn" data-toggle="modal" data-target="#relevanModal">
                        <span class="glyphicon glyphicon-user"></span>Thay đổi người liên quan
                    </button>
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
                                    <select class="selectpicker" multiple data-live-search="true" id="relater">
                                        <c:forEach items="${employees}" var="employee" >
                                            <option value="${employee.id}"${relaters.contains(employee.id)? 'selected' : ''}>${employee.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- Khi click close, thong bao gui cho nguoi duoc nhan assign neu thanh cong popup => susscess assignPopup duoi script .assignPopUp-->
                                <div class="modal-footer">
                                    <button type="button" onclick="postajax('relater', ${request.id}, $('#relater').val(), 'nothing')" class="relevantPopup btn btn-primary" data-dismiss="modal">Submit</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="assignbtn btncustom">
                    <!-- Trigger the modal assign with a button-->
                    <button type="button" class="btn btn-default custom" data-toggle="modal" data-target="#assignModal">
                        <span class="glyphicon glyphicon-pencil"></span>Assign
                    </button>
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
                                    <select class="selectpicker" id="assignedto">
                                        <c:forEach items="${employees}" var="assign" >
                                            <option value="${assign.id}"${assign.id == request.assignedTo? 'selected' : ''}>${assign.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <!-- Khi click close, thong bao gui cho nguoi duoc nhan assign neu thanh cong popup => susscess assignPopup duoi script .assignPopUp-->
                                <div class="modal-footer">
                                    <button type="button" onclick="postajax('assign', ${request.id}, $('#assignedto').val())" class="assignPopup btn btn-primary" data-dismiss="modal">Submit</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                </c:if>

                <c:if test="${id == request.createdBy}">
                    <div class="statuschangebtn btncustom">
                        <select class="selectpicker" title="Thay đổi trạng thái" id="statuschange" name="status" onchange="change(this);">
                            <option value="1" data-icon="glyphicon-pencil">New</option>
                            <option value="2" data-icon="glyphicon-play">Inprogress</option>
                            <option value="3" data-icon="glyphicon-ok">Resolved</option>
                            <option value="4" data-icon="glyphicon-remove">Cancel</option>
                            <option value="5" data-icon="glyphicon-refresh">Feedback</option>
                        </select>
                    </div>
                </c:if>

            </div>
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
                        <c:if test="${i != 1}">
                            <c:out value=","/>
                        </c:if>
                        <c:out value = "${relaters.get(i-1).employeeName}"/>
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
            <div class="panel-heading"> <h4><b>Description</b> </h4></div>
            <div class="panel-body">
                <!--Requested Detail -->
                <div class="col-xs-12">
                    <div class="media">
                        <div class="media-left">

                        </div>
                        <div class="media-body">
                            <h3 class="media-heading">${request.createdByName}
                                </br>
                                <span class="glyphicon glyphicon-time"></span>
                                <small> Created: ${request.createdAt} </small>
                            </h3>
                            <p>${request.content}</p>

                            <!--Comment on this Request -->
                            <c:forEach var="comment" items="${comments}">
                                <hr/>
                                <div class="row comment"> <!-- if there's a new comment ,will be added to this -->
                                    <div class="col-xs-1">
                                    </div>
                                    <div class="col-xs-10">
                                        <div class="media">
                                            <div class="media-left">
                                                <img src="image/image.jpeg" class="img-rounded media-object" alt="profilePic" style="width:60px">
                                            </div>
                                            <div class="media-body">
                                                <h3 class="media-heading">${comment.name}
                                                    </br> <span class="glyphicon glyphicon-time"></span> <small> Replied :${comment.createdAt} </small></h3>
                                                <p>${comment.content}</p>
                                                <p>${comment.note}</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-1">
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading"><h4><b>Bình luận</b></h4></div>
            <div class="panel-body">
                <form data-toggle="validator">
                    <div class="form-group">
                        <textarea class="form-control col-xs-12"  rows="5" id="nd" name="nd" required></textarea>
                        <div class="help-block with-errors"></div>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-primary submit" onclick="comment()" id="submit" name="submit-btn">
                            <span class="glyphicon glyphicon-send"></span> Click to submit
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!--Request Details -->



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
            delay: 2000,
        });
        setTimeout(location.reload(true),6000);
    });
    $(".prioriPopup").click(function(){
        $.bootstrapGrowl('Đã thay đổi mức độ ưu tiên.',{
            type: 'success',
            delay: 2000,
        });
        setTimeout(location.reload(true),6000);
    });
    $(".subteamPopup").click(function(){
        $.bootstrapGrowl('Đã thay đổi subteam.',{
            type: 'success',
            delay: 2000,
        });
        setTimeout(location.reload(true),6000);
    });
    $(".deadlinePopup").click(function(){
        $.bootstrapGrowl('Đã thay đổi hạn chót.',{
            type: 'success',
            delay: 2000,
        });
        setTimeout(location.reload(true),6000);
    });
    $(".assignPopup").click(function(){
        $.bootstrapGrowl('Đã thay đổi người thực hiện.',{
            type: 'success',
            delay: 2000,
        });
        setTimeout(location.reload(true),6000);
    });
    $(".relevantPopup").click(function(){
        $.bootstrapGrowl('Đã thay đổi người liên quan.',{
            type: 'success',
            delay: 2000,
        });
        setTimeout(location.reload(true),6000);
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
        postajax("status", ${request.id}, $(selBox).val());
    }

    function comment(){
        $.ajax({
            type:"post",
            cache:false,
            data: {
                typename: "comment",
                requestid: ${request.id},
                nd: $('#nd').val()
            },
            url:"http://localhost:8080/SpecIT/details",
            success : function(responseText) {

            }
        }).fail(function($xhr) {
        });
    }

    $(document).ready(function() {
        $(".date-picker").datepicker();


    });

    function postajax(s, requestId, changeValue, comment){
        $('.modal').modal('hide');
        console.log(changeValue);

        $.ajax({
            type:"POST",
            cache:false,
            data: {
                typename: s,
                requestid: requestId,
                changeValue: changeValue,
                comment: comment
            },
            url:"http://localhost:8080/SpecIT/details",
            success : function(responseText) {

            }
        }).fail(function($xhr) {

        });
    }

    logout = function(){
        $.ajax({
            type:"post",
            cache:false,
            data: {
                ajax: "logout"
            },
            url:"http://localhost:8080/SpecIT/login",
            success : function(responseText) {

            }
        }).fail(function($xhr) {
        });
    }

</script>
</body>
</html>