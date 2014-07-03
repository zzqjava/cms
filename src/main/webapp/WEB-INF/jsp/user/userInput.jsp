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
                    <c:choose>
                        <c:when test="${user.id == null}">
                            <h3>用户添加</h3>
                        </c:when>
                        <c:otherwise>
                            <h3>用户修改</h3>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="">
                    <legend class=""></legend>
                </div>
                <c:choose>
                    <c:when test="${user.id == null}">
                        <form class="form-horizontal"action="${ctx}/user/create" method="post">
                    </c:when>
                    <c:otherwise>
                        <form class="form-horizontal"action="${ctx}/user/update" method="post">
                    </c:otherwise>
                </c:choose>
                    <input type="hidden" name="id" value="${user.id}">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="${user.username}" name="username" autofocus="" required="" value="${user.username}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">密码</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" name="password" required="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">确认密码</label>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" name="conPassword" required="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">姓名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="${user.name}" name="name" value="${user.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">邮箱</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="${user.email}" name="email" value="${user.email}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">手机号</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="${user.mobile}" name="mobile" value="${user.mobile}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="col-sm-3 control-label" style="width:23%;">性别</label>
                        <div class="form-group">
                            <c:choose>
                                <c:when test="${user.gender.name == '男'}">
                                    <label class="radio-inline">
                                        <input type="radio" name="genderValue" checked value="1"> 男
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="genderValue" value="2"> 女
                                    </label>
                                </c:when>
                                <c:otherwise>
                                    <label class="radio-inline">
                                        <input type="radio" name="genderValue" value="1"> 男
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="genderValue" checked value="2"> 女
                                    </label>
                                </c:otherwise>
                            </c:choose>
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
        </div>
    </body>
</html>