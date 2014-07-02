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
        <div class="container" style="margin-top:60px;">
            <a href="${ctx}/user/input" class="btn btn-primary btn-lg" role="button">创建用户</a>
        </div>
        <div class="container" style="margin-top:10px;">
            <c:if test="${userList != 'null'}">
                <div class="text-center">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>序号</th>
                                <th>用户名</th>
                                <th>性别</th>
                                <th>手机号</th>
                                <th>邮箱</th>
                                <th colspan="2">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${userList}" var="user" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${user.username}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.gender.value == 1}">
                                                男
                                            </c:when>
                                            <c:otherwise>
                                                女
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${user.mobile}</td>
                                    <td>${user.email}</td>
                                    <td><a href="${ctx}/user/input?id=${user.id}">修改</a></td>
                                    <td><a href="${ctx}/user/del?id=${user.id}">删除</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </body>
</html>