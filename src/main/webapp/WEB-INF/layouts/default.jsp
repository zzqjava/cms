<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<c:set var="SITE_NAME" scope="request" value="CMS"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="zh-cn" class="app">
<head>
    <meta charset="utf-8"/>
    <title>${SITE_NAME} | <sitemesh:title/></title>
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/static/css/animate.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/static/css/font.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/static/js/calendar/bootstrap_calendar.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/static/css/app.css" type="text/css"/>
    <!--[if lt IE 9]>
    <script src="${ctx}/static/js/ie/html5shiv.js"></script>
    <script src="${ctx}/static/js/ie/respond.min.js"></script>
    <script src="${ctx}/static/js/ie/excanvas.js"></script>
    <![endif]-->

    <script src="${ctx}/static/js/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${ctx}/static/js/bootstrap.js"></script>
    <script src="${ctx}/static/js/bootstrap-paginator.js"></script>
    <!-- App -->
    <script src="${ctx}/static/js/app.js"></script>
    <script src="${ctx}/static/js/app.plugin.js"></script>
    <script src="${ctx}/static/js/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${ctx}/static/js/charts/easypiechart/jquery.easy-pie-chart.js"></script>
    <script src="${ctx}/static/js/charts/sparkline/jquery.sparkline.min.js"></script>
    <script src="${ctx}/static/js/charts/flot/jquery.flot.min.js"></script>
    <script src="${ctx}/static/js/charts/flot/jquery.flot.tooltip.min.js"></script>
    <script src="${ctx}/static/js/charts/flot/jquery.flot.resize.js"></script>
    <script src="${ctx}/static/js/charts/flot/jquery.flot.grow.js"></script>
    <script src="${ctx}/static/js/charts/flot/demo.js"></script>

    <script src="${ctx}/static/js/calendar/bootstrap_calendar.js"></script>
    <script src="${ctx}/static/js/calendar/demo.js"></script>

    <script src="${ctx}/static/js/sortable/jquery.sortable.js"></script>
    <sitemesh:head/>
</head>
<body>
<section class="vbox">
    <header class="bg-dark dk header navbar navbar-fixed-top-xs">
        <div class="navbar-header aside-md">
            <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen,open" data-target="#nav,html">
                <i class="fa fa-bars"></i>
            </a>
            <a href="#" class="navbar-brand" data-toggle="fullscreen"><img src="${ctx}/static/images/logo.png"
                                                                           class="m-r-sm">${SITE_NAME}</a>
            <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".nav-user">
                <i class="fa fa-cog"></i>
            </a>
        </div>
        <ul class="nav navbar-nav navbar-right m-n hidden-xs nav-user">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <span class="thumb-sm avatar pull-left">
              <img src="${ctx}/static/images/avatar.jpg">
            </span>
                    <shiro:principal/> <b class="caret"></b>
                </a>
                <ul class="dropdown-menu animated fadeInRight">
                    <span class="arrow top"></span>
                    <%--<li>
                        <a href="#">Settings</a>
                    </li>
                    <li>
                        <a href="profile.html">Profile</a>
                    </li>
                    <li>
                        <a href="#">
                            <span class="badge bg-danger pull-right">3</span>
                            Notifications
                        </a>
                    </li>
                    <li>
                        <a href="docs.html">Help</a>
                    </li>
                    <li class="divider"></li>--%>
                    <li>
                        <a href="${ctx}/user/password/change/">修改密码</a>
                    </li>
                    <li>
                        <a href="${ctx}/signout">退出</a>
                    </li>
                </ul>
            </li>
        </ul>
    </header>
    <section>
        <section class="hbox stretch">
            <!-- .aside -->
            <aside class="bg-dark lter aside-md hidden-print" id="nav">
                <section class="vbox">
                    <section class="w-f scrollable">
                        <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0"
                             data-size="5px" data-color="#333333">

                            <!-- nav -->
                            <nav class="nav-primary hidden-xs">
                                <ul class="nav">
                                    <c:forEach items="${resourceListForMenu}" var="resourceParent">
                                        <li class="active">
                                            <a href="index.html"   class="active">
                                                <i class="fa fa-dashboard icon">
                                                    <b class="bg-danger"></b>
                                                </i>
                                                <span>${resourceParent.name}</span>
                                            </a>
                                            <c:if test="${resourceParent.children != null}">
                                                <ul class="nav lt">
                                                    <c:forEach items="${resourceParent.children}" var="resource">
                                                        <li >
                                                            <a href="${ctx}${resource.url}" >
                                                                <i class="fa fa-angle-right"></i>
                                                                <span>${resource.name}</span>
                                                            </a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </nav>
                            <!-- / nav -->
                        </div>
                    </section>

                    <footer class="footer lt hidden-xs b-t b-dark">
                        <a href="#nav" data-toggle="class:nav-xs" class="pull-right btn btn-sm btn-dark btn-icon">
                            <i class="fa fa-angle-left text"></i>
                            <i class="fa fa-angle-right text-active"></i>
                        </a>
                    </footer>
                </section>
            </aside>
            <!-- /.aside -->
            <sitemesh:body/>
        </section>
    </section>
</section>
</body>
</html>
