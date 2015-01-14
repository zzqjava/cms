<%@page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn" class="">
<head>
    <meta charset="utf-8" />
    <title>${SITE_NAME} | 500</title>
    <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="${ctx}/static/css/animate.css" type="text/css" />
    <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css" type="text/css" />
    <link rel="stylesheet" href="${ctx}/static/css/font.css" type="text/css" />
    <link rel="stylesheet" href="${ctx}/static/css/app.css" type="text/css" />
    <!--[if lt IE 9]>
    <script src="${ctx}/static/js/ie/html5shiv.js"></script>
    <script src="${ctx}/static/js/ie/respond.min.js"></script>
    <script src="${ctx}/static/js/ie/excanvas.js"></script>
    <![endif]-->
</head>
<body>
<section id="content">
    <div class="row m-n">
        <div class="col-sm-4 col-sm-offset-4">
            <div class="text-center m-b-lg">
                <h1 class="h text-white animated fadeInDownBig">500</h1>
            </div>
            <div class="list-group m-b-sm bg-white m-b-lg">
                <a href="${ctx}/" class="list-group-item">
                    <i class="fa fa-chevron-right icon-muted"></i>
                    <i class="fa fa-fw fa-home icon-muted"></i> 返回主页
                </a>
            </div>
        </div>
    </div>
</section>
<!-- footer -->
<footer id="footer">
    <div class="text-center padder clearfix">
        <p>
            <small>CMS &copy; 2007-2014 qatang.com</small>
        </p>
    </div>
</footer>
<!-- / footer -->
<script src="${ctx}/static/js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="${ctx}/static/js/bootstrap.js"></script>
<!-- App -->
<script src="${ctx}/static/js/app.js"></script>
<script src="${ctx}/static/js/app.plugin.js"></script>
<script src="${ctx}/static/js/slimscroll/jquery.slimscroll.min.js"></script>

</body>
</html>