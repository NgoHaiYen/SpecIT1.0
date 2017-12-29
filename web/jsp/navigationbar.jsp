<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
                <li><a href="#"><span class="glyphicon glyphicon-plus"></span><b>THÊM YÊU CẦU</b></a></li>
                <!-- Dropdown-->
                <li class="panel panel-default">
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

                <li class="panel panel-default">
                    <a data-toggle="collapse" href="#dropdown-lvl2">
                        <span class="glyphicon glyphicon-pencil"></span> Việc tôi được giao <span class="caret"></span>
                    </a>

                    <!-- Dropdown level-->
                    <div id="dropdown-lvl2" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav navbar-nav">
                                <li><a href="#"><span class="glyphicon glyphicon-inbox"></span>All <span id="badge_position" class="label label-success label-as-badge">5</span></a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-envelope"></span>New <span id="badge_position" class="label label-primary label-as-badge">4</span></a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-retweet"></span>In progress <span id="badge_position" class="label label-info label-as-badge">3</span></a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-check"></span>Feedback <span id="badge_position" class="label label-warning label-as-badge">2</span></a></li>
                                <li><a href="#"><span class="glyphicon glyphicon-calendar"></span>Out of Date <span id="badge_position" class="label label-danger label-as-badge">1</span></a></li>
                            </ul>
                        </div>
                    </div>
                </li>

                <?php if($_SESSION["role"] == 2): ?>
                    <li class="panel panel-default">
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
                <?php endif; ?>
                <!--PANEL cua manager-->
                <?php if($_SESSION['role'] == 3): ?>
                    <li class="panel panel-default">
                        <a data-toggle="collapse" href="#dropdown-lv4">
                            <span class="glyphicon glyphicon-star"></span> Công việc của bộ phận IT <span class="caret"></span>
                        </a>

                        <!-- Dropdown level-->
                        <div id="dropdown-lv4" class="panel-collapse collapse">
                            <div class="panel-body">
                                <ul class="nav navbar-nav">
                                    <li><a href="#"><span class="glyphicon glyphicon-inbox"></span>All <span id="badge_position" class="label label-success label-as-badge">5</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-envelope"></span>New <span id="badge_position" class="label label-primary label-as-badge">4</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-check"></span>In progress <span id="badge_position" class="label label-warning label-as-badge">2</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-export"></span>FeedBack <span id="badge_position" class="label label-warning label-as-badge">2</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-calendar"></span>Out of Date <span id="badge_position" class="label label-danger label-as-badge">1</span></a></li>
                                    <li><a href="#"><span class="glyphicon glyphicon-minus-sign"></span>Closed <span id="badge_position" class="label label-warning label-as-badge">2</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                <?php endif; ?>
            </ul>
        </div><!-- /.navbar-collapse -->
    </nav>
</div>