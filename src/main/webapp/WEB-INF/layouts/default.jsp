<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<%--                                    <c:forEach items="${menuBeans}" var="menuBean">
                                        <li class="active">
                                            <a href="index.html"   class="active">
                                                <i class="fa fa-dashboard icon">
                                                    <b class="bg-danger"></b>
                                                </i>
                                                <span>${menuBean.menu.name}</span>
                                            </a>
                                            <c:if test="${menuBean.subMenus != null}">
                                                <ul class="nav lt">
                                                    <c:forEach items="${menuBean.subMenus}" var="menu">
                                                        <li >
                                                            <a href="${ctx}${menu.url}" >
                                                                <i class="fa fa-angle-right"></i>
                                                                <span>${menu.name}</span>
                                                            </a>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </c:if>
                                        </li>
                                    </c:forEach>--%>
                                    <li class="active">
                                        <a href="index.html"   class="active">
                                            <i class="fa fa-dashboard icon">
                                                <b class="bg-danger"></b>
                                            </i>
                                            <span>系统管理</span>
                                        </a>
                                        <ul class="nav lt">
                                            <li >
                                                <a href="${ctx}/user/list" >
                                                    <i class="fa fa-angle-right"></i>
                                                    <span>用户管理</span>
                                                </a>
                                            </li>
                                            <li >
                                                <a href="${ctx}/resource/list" >
                                                    <i class="fa fa-angle-right"></i>
                                                    <span>资源管理</span>
                                                </a>
                                            </li>
                                            <li >
                                                <a href="${ctx}/role/list" >
                                                    <i class="fa fa-angle-right"></i>
                                                    <span>角色管理</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
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
<script src="${ctx}/static/js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="${ctx}/static/js/bootstrap.js"></script>
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

</body>
</html>
