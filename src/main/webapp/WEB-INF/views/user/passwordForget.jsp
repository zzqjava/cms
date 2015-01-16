<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
    <head>
        <title>欢迎</title>
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">后台管理系统</a>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="login-box text-center">
                <div class="login-single-panel-header">
                    <h3>忘记密码</h3>
                </div>
                <div class="login-single-panel-header">
                    <h5 style="color:red">${errorMessage}</h5>
                </div>
                <div class="">
                    <legend class=""></legend>
                </div>
                <form class="form-horizontal" action="${ctx}/user/password/reset" method="post">
                    <input type="hidden" name="id" value="${id}">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">邮箱</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" name="password" autofocus="" required="">
                        </div>
                    </div>
                    <div class="">
                        <legend class=""></legend>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-9">
                            <button type="submit" class="btn btn-primary btn-lg">提交</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="text-center">
                <a class="btn btn-primary btn-sm" href="${ctx}${forwardUrl}">返回列表</a>
            </div>
        </div>
    </body>
</html>