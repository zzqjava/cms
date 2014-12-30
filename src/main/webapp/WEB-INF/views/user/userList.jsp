<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN" class="bg-dark">
    <head>
        <title>用户列表</title>
        <script type="text/javascript">
            var goPage = function(page) {
                $("#page").val(page);
                $("#pageForm").submit();
            }
        </script>
    </head>
    <body>
        <section id="content">
            <section class="vbox">
                <section class="scrollable padder">
                    <ul class="breadcrumb no-border no-radius b-b b-light pull-in">
                        <li><a href="${ctx}/dashboard"><i class="fa fa-home"></i> 主页</a></li>
                        <li><a href="#">系统管理</a></li>
                        <li class="active"><a href="#">用户管理</a></li>
                    </ul>
                    <div class="m-b-md">
                        <h3 class="m-b-none">用户管理</h3>
                    </div>
                    <header class="panel-heading">
                        <a href="${ctx}/user/input"><button class="btn btn-sm btn-default">用户添加</button></a>
                    </header>
                    <section class="panel panel-default">
                        <form class="form-inline" id="queryForm" action="${ctx}/user/list" method="post">
                            <div class="row wrapper">
                                <div class="col-sm-2 m-b-xs">
                                    <div class="input-group">
                                        <span class="input-group-addon">用户名：</span>
                                        <input type="text" name="username" id="username" value="${userForm.username}" class="form-control">
                                    </div>
                                </div>
                                <div class="col-sm-2 m-b-xs">
                                    <div class="input-group">
                                        <span class="input-group-addon">姓名：</span>
                                        <input type="text" name="name" id="name" value="${userForm.name}" class="form-control">
                                    </div>
                                </div>
                                <div class="col-sm-2 m-b-xs">
                                    <div class="input-group">
                                        <span class="input-group-addon">邮箱：</span>
                                        <input type="text" name="email" id="email" value="${userForm.email}" class="form-control">
                                    </div>
                                </div>
                                <div class="col-sm-2 m-b-xs">
                                    <div class="input-group">
                                        <span class="input-group-addon">手机号：</span>
                                        <input type="text" name="mobile" id="mobile" value="${userForm.mobile}" class="form-control">
                                    </div>
                                </div>
                                <div class="col-sm-2 m-b-xs">
                                    <div class="input-group">
                                        <span class="input-group-addon ">性别：</span>
                                        <form:select path="queryGenderList" items="${queryGenderList}" itemValue="value" class="form-control" itemLabel="name" name="genderValue" id="genderValue"/>
                                    </div>
                                </div>
                                <div class="col-sm-1 m-b-xs">
                                    <input class="btn btn-sm btn-default" id="query" name="query" type="submit" value="查询" />
                                </div>
                            </div>
                        </form>
                        <div class="table-responsive">
                            <table class="table table-striped b-t b-light">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>用户名</th>
                                    <th>姓名</th>
                                    <th>性别</th>
                                    <th>手机号</th>
                                    <th>邮箱</th>
                                    <th>是否有效</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${userList}" var="user" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td><a href="${ctx}/user/view/${user.id}">${user.username}</a></td>
                                    <td>${user.name}</td>
                                    <td>${user.gender.name}</td>
                                    <td>${user.mobile}</td>
                                    <td>${user.email}</td>
                                    <td>${user.valid.name}</td>
                                    <td>
                                        <a href="${ctx}/user/input/${user.id}">修改</a>
                                        <a href="${ctx}/user/password/input/${user.id}">密码管理</a>
                                        <a href="${ctx}/userRole/input/${user.id}">角色管理</a>
                                        <a href="${ctx}/user/validate/${user.id}">
                                            <c:choose>
                                                <c:when test="${user.valid.value == 1}">
                                                    禁用
                                                </c:when>
                                                <c:otherwise>
                                                    启用
                                                </c:otherwise>
                                            </c:choose>
                                        </a>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <footer class="panel-footer">
                            <div class="row">
                                <div class="col-sm-4 hidden-xs">
                                </div>
                                <div class="col-sm-4 text-center">
                                </div>
                                <div class="col-sm-4 text-right text-center-xs">
                                    <form id="pageForm" class="form-inline" action="${ctx}/user/list" method="post">
                                        <input id="page" type="hidden" name="pageInfo.currentPage">
                                        <ul class="pagination pagination-sm m-t-none m-b-none">
                                            <c:if test="${userForm.pageInfo.currentPage > 1}">
                                                <li><a style="cursor:pointer;" onclick="goPage(${userForm.pageInfo.currentPage - 1});"><i class="fa fa-chevron-left"></i></a></li>
                                            </c:if>
                                            <c:forEach begin="1" end="${userForm.pageInfo.totalPages}" var="i">
                                                <li><a onclick="goPage(${i});" style="cursor:pointer;<c:if test="${userForm.pageInfo.currentPage == i}"> background-color:#EEE;</c:if>">${i}</a></li>
                                            </c:forEach>
                                            <c:if test="${userForm.pageInfo.currentPage < userForm.pageInfo.totalPages}">
                                                <li><a style="cursor:pointer;" onclick="goPage(${userForm.pageInfo.currentPage + 1});"><i class="fa fa-chevron-right"></i></a></li>
                                            </c:if>
                                        </ul>
                                    </form>
                                </div>
                            </div>
                        </footer>
                    </section>
                </section>
            </section>
            <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
    </body>
</html>