<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Danh sách công việc được giao</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="css/slidebar.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css"/>
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
    <jsp:include page="navigationbar.jsp" />

    <!-- Main Content -->
    <div class="container-fluid">
        <div class="side-body">
            <h1> Danh sách công việc được giao </h1><br/>
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
                        <c:forEach var="user" items="${listUser}">
                            <tr>
                                <td><a href="${pageContext.request.contextPath}/UserDetailsController?userId=${user.userId}">${user.userId}</a></td>
                                <td><img src="image/${user.linkImage}" width="40px" height="40px"></td>
                                <td>${user.fullName}</td>
                                <td>${user.fullNameKana}</td>
                                <td>${user.gender}</td>
                                <td>${user.email}</td>
                                <td>${user.tel}</td>
                                <td>${user.level}</td>
                                <td>${user.endDate}</td>
                                <td>${user.groupName}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/moment.js"></script>
        <script src="bootstrap/js/bootstrap-select.min.js"></script>
        <script src="bootstrap/js/bootstrap-datetimepicker.min.js"></script>
        <script src="js/ckeditor/ckeditor.js"></script>
        <script src="js/request.js"></script>
        <script src="js/jquery.dataTables.min.js"></script>
        <script src="js/dataTables.bootstrap.min.js"></script>
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