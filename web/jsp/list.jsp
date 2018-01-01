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
    <form action="details" id="list" method="post">
        <div class="container-fluid">
            <div class="side-body">
                <h1> ${listname} </h1><br/>
                <div>
                    <table class="table table-hover" id="listmain">
                        <thead>
                        <tr>
                            <th>Hình ảnh</th>
                            <th>Tên công việc</th>
                            <th>Mức độ ưu tiên</th>
                            <th>Người yêu cầu</th>
                            <th>Người thực hiện</th>
                            <th>Ngày hết hạn</th>
                            <th>Trạng thái</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="request" items="${requests}">
                                <tr>
                                    <td>
                                        <span <c:if test="${request.read}">style="visibility: hidden"</c:if> onclick="seen(${request.id}, this)" class="glyphicon glyphicon-asterisk" style="color:red"></span>
                                        <img class="seen" src="image/${request.image}" width="40px" height="40px">
                                    </td>
                                    <td><a style="cursor: pointer;" onclick="postValue(${request.id})">${request.subject}</a></td>
                                    <td>${request.priorityName}</td>
                                    <td>${request.createdByName}</td>
                                    <td>${request.assignedToName}</td>
                                    <td>${request.deadline}</td>
                                    <td>${request.statusName}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <input type="hidden" name="requestshow" id="show">
    </form>

        <script>
            function postValue(a){
                document.getElementById('show').value = a;
                document.getElementById('list').submit();
            }
        </script>

        <script src="js/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/moment.js"></script>
        <script src="bootstrap/js/bootstrap-select.min.js"></script>
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
            function seen(requestId, e){
                $.ajax({
                    type:"POST",
                    cache:false,
                    data: {
                        requestid: requestId,
                    },
                    url:"http://localhost:8080/SpecIT/list",
                    success : function(responseText) {
                        if (responseText == "ok"){

                        }
                    }
                }).fail(function($xhr) {

                });
            }

            $('.seen').click(function(event){
                console.log(event.target.hide());
            });
        </script>
</body>
</html>