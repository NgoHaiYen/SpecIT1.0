<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Danh sách công việc được giao</title>
    <link rel="stylesheet" href="../../css/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/bootstrap/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="../../css/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="../css/slidebar.css">
    <link rel="stylesheet" href="../../css/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/jquery.dataTables.min.css"/>
    <style type="text/css">
        .dataTables_wrapper .dataTables_paginate .paginate_button{
            padding: 0px;
        }
        .paging_full_numbers{
            margin-right: 1em;
        }
        .dataTables_wrapper .dataTables_filter input {
            margin-right: 1em;
        }
        table.dataTable{
            width: 98%;
        }
    </style>
</head>

<body>
<div class="row">
    <!-- Menu -->
    <div class="side-menu">

        <nav class="navbar navbar-default" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <div class="brand-wrapper">
                    <!-- Hamburger -->
                    <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                    <!-- Brand -->
                    <div class="brand-name-wrapper">
                        <a class="navbar-brand" href="#">
                            <span class="glyphicon glyphicon-home"></span>   Call log IT
                        </a>
                    </div>
                </div>
            </div>

            <!-- Main Menu -->
            <div class="side-menu-container">
                <ul class="nav navbar-nav">
                    <li><a href="add.php"><span class='glyphicon glyphicon-plus'></span><b>THÊM YÊU CẦU</b></a></li>
                    <!-- Dropdown-->
                    <li class="panel panel-default" id="dropdown">
                        <a data-toggle="collapse" href="#dropdown-lvl1">
                            <span class="glyphicon glyphicon-asterisk"></span> Việc tôi yêu cầu <span class="caret"></span>
                        </a>
                        <!-- Dropdown level-->
                        <div id="dropdown-lvl1" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul class="nav navbar-nav">
                                    <li><a href="#"><span class="glyphicon glyphicon-inbox"></span>All <span id="badge_position" class="label label-success label-as-badge">5</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-envelope"></span>New <span id="badge_position" class="label label-primary label-as-badge">4</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-retweet"></span>Resolved <span id="badge_position" class="label label-info label-as-badge">3</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-check"></span>In progress <span id="badge_position" class="label label-warning label-as-badge">2</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-calendar"></span>Out of Date <span id="badge_position" class="label label-danger label-as-badge">1</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </li>

                    <li class="panel panel-default" id="dropdown">
                        <a data-toggle="collapse" href="#dropdown-lvl2">
                            <span class="glyphicon glyphicon-pencil"></span> Việc tôi được giao <span class="caret"></span>
                        </a>
                        <!-- Dropdown level-->
                        <div id="dropdown-lvl2" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul class="nav navbar-nav">
                                    <li><a href="#"><span class="glyphicon glyphicon-inbox"></span>All <span id="badge_position" class="label label-success label-as-badge">5</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-envelope"></span>New <span id="badge_position" class="label label-primary label-as-badge">4</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-retweet"></span>Resolved <span id="badge_position" class="label label-info label-as-badge">3</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-check"></span>In progress <span id="badge_position" class="label label-warning label-as-badge">2</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-calendar"></span>Out of Date <span id="badge_position" class="label label-danger label-as-badge">1</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </li>

                    <li class="panel panel-default" id="dropdown">
                        <a data-toggle="collapse" href="#dropdown-lv3">
                            <span class="glyphicon glyphicon-user"></span> Công việc liên quan <span class="caret"></span>
                        </a>

                        <!-- Dropdown level-->
                        <div id="dropdown-lv3" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul class="nav navbar-nav">
                                    <li><a href="#"><span class="glyphicon glyphicon-inbox"></span>All <span id="badge_position" class="label label-success label-as-badge">5</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-envelope"></span>New <span id="badge_position" class="label label-primary label-as-badge">4</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-retweet"></span>Resolved <span id="badge_position" class="label label-info label-as-badge">3</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-check"></span>In progress <span id="badge_position" class="label label-warning label-as-badge">2</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-calendar"></span>Out of Date <span id="badge_position" class="label label-danger label-as-badge">1</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
    </div>

    <!-- Main Content -->
    <div class="container-fluid">
        <div class="side-body">
            <h1> Danh sách công việc được giao </h1>
            <br/>

            <div>
                <table class="table table-hover" id="listmain">
                    <thead>
                    <tr>
                        <th>STT</th>
                        <th>Tên công việc</th>
                        <th>Mức độ ưu tiên</th>
                        <th>Người yêu cầu</th>
                        <th>Người thực hiện</th>
                        <th>Ngày hết hạn</th>
                        <th>Trạng thái</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>Sửa màn hình máy tính</td>
                        <td>Cao</td>
                        <td>Phạm Tuấn Anh</td>
                        <td>Phạm Tuấn Anh</td>
                        <td>2017-10-16 19:00:00</td>
                        <td>New</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Dooley</td>
                        <td>Bình thường</td>
                        <td>Phạm Tuấn Anh</td>
                        <td>Phạm Tuấn Anh</td>
                        <td>2017-10-16 19:00:00</td>
                        <td>Closed</td>
                    </tr>
                    </tbody>
                </table>

            </div>
        </div>

        <script src="../js/jquery.min.js"></script>
        <script src="../../css/bootstrap/js/bootstrap.min.js"></script>
        <script src="../../css/bootstrap/js/moment.js"></script>
        <script src="../../css/bootstrap/js/bootstrap-select.min.js"></script>
        <script src="../../css/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
        <script src="../../js/ckeditor/ckeditor.js"></script>
        <script src="../js/request.js"></script>
        <script src="../js/jquery.dataTables.min.js"></script>
        <script src="../js/dataTables.bootstrap.min.js"></script>
        <script>
            $(function () {
                /*Toggle bat tat slide bar*/
                $('.navbar-toggle').click(function () {
                    $('.navbar-nav').toggleClass('slide-in');
                    $('.side-body').toggleClass('body-slide-in');
                });
            });
            $(document).ready(function() {
                $('#listmain').DataTable({
                    pagingType: "full_numbers",
                    responsive: true,
                    language:
                        {
                            paginate:
                                {
                                    previous: "<",
                                    next: ">",
                                    first: "|<",
                                    last: ">|"
                                }
                        }
                });
            });
        </script>
</body>
</html>