<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欢迎</title>
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="keywords" content="" />
    <meta http-equiv="description" content="" />
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/main.css">
    <script src="${ctx}/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function() {
            var captchaUrl = "${ctx}/kaptcha?";
            $('#captchaImg').prop('src', captchaUrl + (new Date()).getTime());
            $('#captchaImg').click(function () {
                $(this).prop('src', captchaUrl + (new Date()).getTime());
            });
        });
    </script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/navi.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6 login-box">
            <h3 class="text-center">用户登录zhangzq</h3>
            <form id="theform" class="form-horizontal" action="${ctx}/signin" method="post" role="form">
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="username">用户名123</label>
                    <div class="col-lg-9">
                        <input type="text" id="username" name="username" autofocus="" placeholder="用户名" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="password">密码</label>
                    <div class="col-lg-9">
                        <input type="password" id="password" name="password" required="" placeholder="Password" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2 control-label" for="captcha">验证码</label>
                    <div class="col-lg-4">
                        <input type="text" id="captcha" name="captcha" autofocus="" required="" placeholder="验证码" class="form-control">
                    </div>
                    <div class="col-lg-4">
                        <img class="img-thumbnail" id="captchaImg" title="看不清？点击图片刷新"/>
                    </div>
                </div>
                <div class="form-group text-center">
                    <input class="btn btn-primary btn-lg" id="sub" name="sub" value="立即登录" type="submit" />
                    &nbsp;
                    <a href="${ctx}/signup" target="_blank">用户注册</a>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="text-center bottom">
    Copyright &copy; 2007-2014 qatang.com All rights reserved.
</div>
</body>
</html>