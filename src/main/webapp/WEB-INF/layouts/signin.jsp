<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="SITE_NAME" scope="request" value="CMS"/>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="zh-cn" class="bg-dark">
<head>
    <meta charset="utf-8"/>
    <title>${SITE_NAME} | <sitemesh:title/></title>
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/static/css/animate.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/static/css/font.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/static/js/calendar/bootstrap_calendar.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/static/css/app.css" type="text/css"/>
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${ctx}/static/js/bootstrap.js"></script>
    <!-- App -->
    <script src="${ctx}/static/js/app.js"></script>
    <script src="${ctx}/static/js/app.plugin.js"></script>
    <script src="${ctx}/static/js/slimscroll/jquery.slimscroll.min.js"></script>
    <!--[if lt IE 9]>
    <script src="${ctx}/static/js/ie/html5shiv.js"></script>
    <script src="${ctx}/static/js/ie/respond.min.js"></script>
    <script src="${ctx}/static/js/ie/excanvas.js"></script>
    <![endif]-->
    <sitemesh:head/>
</head>
<body>
<sitemesh:body/>
</body>
</html>
