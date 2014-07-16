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
        <script type="application/javascript">
            $(function(){
            });

            function collapse (subId) {
                if ($('#' + subId).hasClass('in')) {
                    $('#' + subId).removeClass('in')
                } else {
                    $('#' + subId).addClass('in')
                }
            }
        </script>
    </head>
    <body>
    <jsp:include page="/WEB-INF/jsp/navi.jsp"/>
    <div class="container-fluid">
        <div class="col-lg-2">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="javascript:collapse('collapseOne');">
                                系统管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <a href="${ctx}/user/list">用户管理</a>
                        </div>
                        <div class="panel-body">
                            <a href="${ctx}/menu/list">菜单管理</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="javascript:collapse('collapseTwo');">
                                内容管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="${ctx}/user/list">新闻管理</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-10">
            <ol class="breadcrumb">
                <li><a href="#">系统管理</a></li>
                <li><a href="#">用户管理</a></li>
                <li class="active">用户列表</li>
            </ol>
            <div class="container">
                <div class="col-xs-3">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">用户名称</span>
                        <input class="form-control" type="text" >
                    </div>
                </div>
                <div class="col-xs-2">
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon">性别：</span>
                        <select type="text" class="form-control col-xs-1" >
                            <option value="-1">全部</option>
                            <option value="0">男</option>
                            <option value="1">女</option>
                        </select>
                    </div>
                </div>
                <div class="col-xs-2">
                    <button type="button" class="btn btn-info btn-sm"> 查询</button>
                    <a href="${ctx}/user/input" class="btn btn-success btn-sm" role="button">新增用户</a>
                </div>
            </div>
            <h1></h1>
            <c:if test="${userList != 'null'}">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>用户名</th>
                        <th>性别</th>
                        <th>手机号</th>
                        <th>邮箱</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userList}" var="user" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${user.username}</td>
                            <td>${user.gender}</td>
                            <td>${user.mobile}</td>
                            <td>${user.email}</td>
                            <td><a href="${ctx}/userDelete?id=${user.id}">删除</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
    </div>
    </body>
</html>