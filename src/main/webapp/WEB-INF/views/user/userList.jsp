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
                    <%--<section class="panel panel-heading">--%>
                        <%--<form:form class="form-horizontal" action="${ctx}/user/list" method="post">--%>
                            <%--<div class="panel-body">--%>
                                <%--<table class="table table-bordered">--%>
                                    <%--<tr>--%>
                                        <%--<td>用户名</td>--%>
                                        <%--<td>--%>
                                            <%--<input type="text" class="form-control" name="username" value="${userForm.username}">--%>
                                        <%--</td>--%>
                                        <%--<td>姓名</td>--%>
                                        <%--<td>--%>
                                            <%--<input type="text" class="form-control" name="name" value="${userForm.name}">--%>
                                        <%--</td>--%>
                                        <%--<td>性别</td>--%>
                                        <%--<td>--%>
                                            <%--<select name="genderValue">${genderValue}--%>
                                                <%--<c:forEach items="${queryGenderList}" var="gender">--%>
                                                    <%--<option value="${gender.value}" <c:if test="${gender.value == userForm.genderValue}">selected="selected"</c:if>>${gender.name}</option>--%>
                                                <%--</c:forEach>--%>
                                            <%--</select>--%>
                                        <%--</td>--%>
                                        <%--<td>邮箱</td>--%>
                                        <%--<td>--%>
                                            <%--<input type="text" class="form-control" name="email" value="${userForm.email}">--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
                                    <%--<tr>--%>
                                        <%--<td colspan="4">创建时间:从--%>
                                            <%--到--%>
                                        <%--</td>--%>
                                        <%--<td>手机号</td>--%>
                                        <%--<td>--%>
                                            <%--<input type="text" class="form-control" name="mobile" value="${userForm.mobile}">--%>
                                        <%--</td>--%>
                                        <%--<td>QQ</td>--%>
                                        <%--<td>--%>
                                            <%--<input type="text" class="form-control" name="QQ" value="${userForm.QQ}">--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
                                <%--</table>--%>
                            <%--</div>--%>
                            <%--<div>--%>
                                <%--<button type="submit" class="btn btn-default">提交</button>--%>
                            <%--</div>--%>
                        <%--</form:form>--%>
                    <%--</section>--%>
                    <section class="panel panel-default">
                        <div class="row wrapper">
                            <div class="col-sm-5 m-b-xs">
                                <a href="${ctx}/user/input"><button class="btn btn-sm btn-default">用户添加</button></a>
                            </div>
                        </div>
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
                                    <th colspan="4">操作</th>
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
                                        <a href="${ctx}/user/role/${user.id}">角色管理</a>
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
                                    </td>
                                    <td><a href="${ctx}/userRole/input/${user.id}">角色更改</a></td>
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