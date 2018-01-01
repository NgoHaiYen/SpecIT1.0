<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c"%>

<!-- Menu -->
<link rel="stylesheet" href="css/slidebar.css">
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
                <button onclick="window.location.href='add'" class="btn btn-danger custom" style="width: 270px !important; height: 40px !important; text-align: left !important; margin-top: 15px">
                    <a style="text-decoration: none" href="${pageContext.request.contextPath}/add">
                        <span  style="color: white; margin-right: 15px" class="glyphicon glyphicon-plus"></span>
                        <b style="color: white">THÊM YÊU CẦU</b>
                    </a>
                </button>
                <!-- Dropdown-->
                <li class="panel panel-default" style="margin-top: 30px">
                    <a data-toggle="collapse" href="#dropdown-lvl1">
                        <span class="glyphicon glyphicon-asterisk"></span> Việc tôi yêu cầu <span class="caret"></span>
                    </a>

                    <!-- Dropdown level-->
                    <div id="dropdown-lvl1" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav navbar-nav">
                                <li><a href="${pageContext.request.contextPath}/list?t=my"><span class="glyphicon glyphicon-inbox"></span>All <span class="label label-success label-as-badge">${myra}</span></a></li>
                                <li><a href="${pageContext.request.contextPath}/list?t=my&k=n"><span class="glyphicon glyphicon-envelope"></span>New <span class="label label-primary label-as-badge">${myrn}</span></a></li>
                                <li><a href="${pageContext.request.contextPath}/list?t=my&k=i"><span class="glyphicon glyphicon-check"></span>In progress <span class="label label-warning label-as-badge">${myri}</span></a></li>
                                <li><a href="${pageContext.request.contextPath}/list?t=my&k=r"><span class="glyphicon glyphicon-retweet"></span>Resolved <span class="label label-info label-as-badge">${myrr}</span></a></li>
                                <li><a href="${pageContext.request.contextPath}/list?t=my&k=f"><span class="glyphicon glyphicon-retweet"></span>Feedback <span class="label label-info label-as-badge">${myrf}</span></a></li>
                                <li><a href="${pageContext.request.contextPath}/list?t=my&k=c"><span class="glyphicon glyphicon-retweet"></span>Closed <span class="label label-info label-as-badge">${myrc}</span></a></li>
                                <li><a href="${pageContext.request.contextPath}/list?t=my&k=o"><span class="glyphicon glyphicon-calendar"></span>Out of Date <span class="label label-danger label-as-badge">${myro}</span></a></li>
                            </ul>
                        </div>
                    </div>
                </li>

                <li class="panel panel-default">
                    <a data-toggle="collapse" href="#dropdown-lvl2">
                        <span class="glyphicon glyphicon-pencil"></span> Việc tôi được giao <span class="caret"></span>
                    </a>

                    <!-- Dropdown level-->
                    <div id="dropdown-lvl2" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav navbar-nav">
                                <li><a href="${pageContext.request.contextPath}/list?t=mya"><span class="glyphicon glyphicon-inbox"></span>All <span class="label label-success label-as-badge">${myaa}</span></a></li>
                                <li><a href="${pageContext.request.contextPath}/list?t=mya&k=n"><span class="glyphicon glyphicon-envelope"></span>New <span class="label label-primary label-as-badge">${myan}</span></a></li>
                                <li><a href="${pageContext.request.contextPath}/list?t=mya&k=i"><span class="glyphicon glyphicon-retweet"></span>In progress <span class="label label-info label-as-badge">${myai}</span></a></li>
                                <li><a href="${pageContext.request.contextPath}/list?t=mya&k=r"><span class="glyphicon glyphicon-check"></span>Resolved <span class="label label-warning label-as-badge">${myar}</span></a></li>
                                <li><a href="${pageContext.request.contextPath}/list?t=mya&k=f"><span class="glyphicon glyphicon-check"></span>Feedback <span class="label label-warning label-as-badge">${myaf}</span></a></li>
                                <li><a href="${pageContext.request.contextPath}/list?t=mya&k=o"><span class="glyphicon glyphicon-calendar"></span>Out of Date <span class="label label-danger label-as-badge">${myao}</span></a></li>
                            </ul>
                        </div>
                    </div>
                </li>


                    <li class="panel panel-default">
                        <a data-toggle="collapse" href="#dropdown-lv3">
                            <span class="glyphicon glyphicon-user"></span> Công việc liên quan <span class="caret"></span>
                        </a>

                        <!-- Dropdown level-->
                        <div id="dropdown-lv3" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul class="nav navbar-nav">
                                    <li><a href="${pageContext.request.contextPath}/list?t=r"><span class="glyphicon glyphicon-inbox"></span>All <span class="label label-success label-as-badge">${rla}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=r&k=n"><span class="glyphicon glyphicon-envelope"></span>New <span class="label label-primary label-as-badge">${rln}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=r&k=i"><span class="glyphicon glyphicon-check"></span>In progress <span class="label label-warning label-as-badge">${rli}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=r&k=r"><span class="glyphicon glyphicon-retweet"></span>Resolved <span class="label label-info label-as-badge">${rlr}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=r&k=f"><span class="glyphicon glyphicon-retweet"></span>Feedback <span class="label label-info label-as-badge">${rlf}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=r&k=c"><span class="glyphicon glyphicon-retweet"></span>Closed <span class="label label-info label-as-badge">${rlc}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=r&k=o"><span class="glyphicon glyphicon-calendar"></span>Out of Date <span class="label label-danger label-as-badge">${rlo}</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </li>

                <!--PANEL cua sublead-->
                <c:if test="${role == 2}">
                    <li class="panel panel-default">
                        <a data-toggle="collapse" href="#dropdown-lv4">
                            <span class="glyphicon glyphicon-star"></span> Công việc của team <span class="caret"></span>
                        </a>

                        <!-- Dropdown level-->
                        <div id="dropdown-lv4" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul class="nav navbar-nav">
                                    <li><a href="${pageContext.request.contextPath}/list?t=t"><span class="glyphicon glyphicon-inbox"></span>All <span class="label label-success label-as-badge">${ta}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=t&k=n"><span class="glyphicon glyphicon-envelope"></span>New <span class="label label-primary label-as-badge">${tn}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=t&k=i"><span class="glyphicon glyphicon-check"></span>In progress <span class="label label-warning label-as-badge">${ti}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=t&k=r"><span class="glyphicon glyphicon-export"></span>Resolved <span class="label label-warning label-as-badge">${tr}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=t&k=f"><span class="glyphicon glyphicon-export"></span>Feedback <span class="label label-warning label-as-badge">${tf}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=t&k=c"><span class="glyphicon glyphicon-export"></span>Closed <span class="label label-warning label-as-badge">${tc}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=t&k=o"><span class="glyphicon glyphicon-calendar"></span>Out of Date <span class="label label-danger label-as-badge">${to}</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                </c:if>


                <!--PANEL cua manager-->
                <c:if test="${role == 3}">
                    <li class="panel panel-default">
                        <a data-toggle="collapse" href="#dropdown-lv5">
                            <span class="glyphicon glyphicon-edit"></span> Công việc của bộ phận IT <span class="caret"></span>
                        </a>

                        <!-- Dropdown level-->
                        <div id="dropdown-lv5" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul class="nav navbar-nav">
                                    <li><a href="${pageContext.request.contextPath}/list?t=i"><span class="glyphicon glyphicon-inbox"></span>All <span class="label label-success label-as-badge">${ita}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=i&k=n"><span class="glyphicon glyphicon-envelope"></span>New <span class="label label-primary label-as-badge">${itn}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=i&k=i"><span class="glyphicon glyphicon-check"></span>In progress <span class="label label-warning label-as-badge">${iti}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=i&k=r"><span class="glyphicon glyphicon-export"></span>Resolved  <span class="label label-warning label-as-badge">${itr}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=i&k=c"><span class="glyphicon glyphicon-minus-sign"></span>Closed <span class="label label-warning label-as-badge">${itc}</span></a></li>
                                    <li><a href="${pageContext.request.contextPath}/list?t=i&k=o"><span class="glyphicon glyphicon-calendar"></span>Out of Date <span class="label label-danger label-as-badge">${ito}</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                </c:if>
                <!--logout-->
                <button class="btn btn-primary" onclick="logout()" style="width: 270px !important; height: 40px !important; text-align: left !important; margin-top: 15px">
                    <a style="text-decoration: none"><span style="color: honeydew; margin-right: 15px" class="glyphicon glyphicon-off"></span>
                        <b style="color: honeydew">Logout</b>
                    </a>
                </button>
            </ul>
        </div><!-- /.navbar-collapse -->
    </nav>

    <script>
        logout = function(){
            $.ajax({
                    type:"post",
                    cache:false,
                    data: {
                        ajax: "logout"
                    },
                    url:"http://localhost:8080/SpecIT/login",
                    success : function(responseText) {
                        location.href = "http://localhost:8080/SpecIT/login";
                    }
                }).fail(function($xhr) {
                    alert("Failed");
                });
        }
    </script>
</div>