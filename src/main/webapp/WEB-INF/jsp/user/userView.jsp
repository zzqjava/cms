<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>用户列表</title>
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="keywords" content="" />
        <meta http-equiv="description" content="" />
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${ctx}/css/main.css">
        <script src="${ctx}/js/jquery-1.11.1.min.js"></script>
        <script src="${ctx}/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">后台管理系统</a>
                </div>
            </div>
        </div>
        <div class="container" style="margin-top:70px;">
            <div class="text-center">
                <table class="table table-bordered">
                    <tbody>
                        <tr>
                            <td>用户名</td>
                            <td>${user.username}</td>
                        </tr>
                        <tr>
                            <td>姓名</td>
                            <td>${user.name}</td>
                        </tr>
                        <tr>
                            <td>角色</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>性别</td>
                            <td>${user.gender.name}</td>
                        </tr>
                        <tr>
                            <td>邮箱</td>
                            <td>${user.email}</td>
                        </tr>
                        <tr>
                            <td>手机号</td>
                            <td>${user.mobile}</td>
                        </tr>
                        <tr>
                            <td>QQ</td>
                            <td>${user.QQ}</td>
                        </tr>
                        <tr>
                            <td>是否有效</td>
                            <td>${user.valid.name}</td>
                        </tr>
                    </tbody>
                </table>
                <a class="btn btn-primary btn-sm" href="${ctx}${forwardUrl}">返回列表</a>
            </div>
        </div>
    </body>
</html>