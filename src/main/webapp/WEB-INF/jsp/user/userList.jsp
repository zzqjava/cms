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
        <script type="text/javascript">
            var goPage = function(page) {
                $("#page").val(page);
                $("#pageForm").submit();
            }
        </script>
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">后台管理系统</a>
                </div>
            </div>
        </div>
        <div class="container" style="margin:60px auto 10px;">
            <a href="${ctx}/user/input" class="btn btn-primary btn-lg" role="button">创建用户</a>
        </div>
        <div class="container">
            <form class="form-inline" action="${ctx}/user/list" method="post">
                <fieldset>
                    <div class="text-center">
                        <table class="table table-bordered">
                            <tr>
                                <td class="text-right"><span>用户名：</span></td>
                                <td class="text-left"><input type="text" name="username" class="form-control" style="width:50%;" value="${userForm.username}"></td>
                                <td class="text-right"><span>用户姓名：</span></td>
                                <td class="text-left"><input type="text" name="name" class="form-control" style="width:50%;" value="${userForm.name}"></td>
                            </tr>
                            <tr>
                                <td class="text-right"><span>用户邮箱：</span></td>
                                <td class="text-left"><input type="text" name="email" class="form-control" style="width:50%;" value="${userForm.email}"></td>
                                <td class="text-right"><span>手机号：</span></td>
                                <td class="text-left"><input type="text" name="mobile" class="form-control" style="width:50%;" value="${userForm.mobile}"></td>
                            </tr>
                            <tr>
                                <td class="text-right"><span>性别：</span></td>
                                <td class="text-left">
                                    <select name="genderValue">
                                        <option value="0">全部</option>
                                        <option value="1" <c:if test="${userForm.genderValue == 1}">selected="selected"</c:if>>男</option>
                                        <option value="2" <c:if test="${userForm.genderValue == 2}">selected="selected"</c:if>>女</option>
                                    </select>
                                </td>
                                <td class="text-right"><span>是否有效</span></td>
                                <td class="text-left">
                                    <select name="validValue">
                                        <option value="0">全部</option>
                                        <option value="1" <c:if test="${userForm.validValue == 1}">selected="selected"</c:if>>是</option>
                                        <option value="2" <c:if test="${userForm.validValue == 2}">selected="selected"</c:if>>否</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4"><span>将</span>
                                    <select name="orderType">
                                        <option value="id">id</option>
                                        <option value="createdTime" <c:if test="${userForm.orderType == 'createdTime'}">selected="selected"</c:if>>创建时间</option>
                                    </select>
                                    <span>字段，按</span>
                                    <select name="sortType">
                                        <option value="asc">升序</option>
                                        <option value="desc" <c:if test="${userForm.sortType == 'desc'}">selected="selected"</c:if>>降序</option>
                                    </select>
                                    <span>排列</span>
                                </td>
                            </tr>
                        </table>
                        <button class="btn btn-default">查询</button>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="container">
            <div class="">
                <legend class="">用户列表</legend>
            </div>
            <c:if test="${userList != null}">
                <div class="text-center">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>序号</th>
                                <th>用户名</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>手机号</th>
                                <th>邮箱</th>
                                <th>是否有效</th>
                                <th colspan="3">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${userList}" var="user" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td><a href="${ctx}/user/view/${user.id}">${user.username}</a></td>
                                    <td>${user.name}</td>
                                    <td>
                                        <c:if test="${user.gender.value == 1}">男</c:if>
                                        <c:if test="${user.gender.value == 2}">女</c:if>
                                    </td>
                                    <td>${user.mobile}</td>
                                    <td>${user.email}</td>
                                    <td>${user.valid.name}</td>
                                    <td><a href="${ctx}/user/input/${user.id}">修改</a></td>
                                    <td><a href="${ctx}/user/password/input/${user.id}">重置密码</a></td>
                                    <td>
                                        <c:if test="${user.valid.value == 1}">
                                            <a href="${ctx}/user/disable/${user.id}">禁用</a>
                                        </c:if>
                                        <c:if test="${user.valid.value == 2}">
                                            <a href="${ctx}/user/enable/${user.id}">启用</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
        <div class="container">
            <div style="float:right;">
                <form id="pageForm" class="form-inline" action="${ctx}/user/list" method="post">
                    <input id="page" type="hidden" name="pageInfo.currentPage">
                    <ul class="pagination">
                        <c:if test="${userForm.pageInfo.currentPage > 1}">
                            <li><a style="cursor:pointer;" onclick="goPage(${userForm.pageInfo.currentPage - 1});">&laquo;</a></li>
                        </c:if>
                        <c:forEach begin="1" end="${userForm.pageInfo.totalPages}" var="i">
                            <li><a onclick="goPage(${i});" style="cursor:pointer;<c:if test="${userForm.pageInfo.currentPage == i}"> background-color:#EEE;</c:if>">${i}</a></li>
                        </c:forEach>
                        <c:if test="${userForm.pageInfo.currentPage < userForm.pageInfo.totalPages}">
                            <li><a style="cursor:pointer;" onclick="goPage(${userForm.pageInfo.currentPage + 1});">&raquo;</a></li>
                        </c:if>
                    </ul>
                </form>
            </div>
        </div>
    </body>
</html>