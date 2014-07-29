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
        <jsp:include page="/WEB-INF/jsp/navi.jsp"/>
        <div class="container-fluid">
            <div class="col-lg-2">
                <jsp:include page="/WEB-INF/jsp/menu.jsp"/>
            </div>
            <div class="col-lg-10">
                <ol class="breadcrumb">
                    <li><a href="#">系统管理</a></li>
                    <li><a href="${ctx}/user/list">用户管理</a></li>
                    <li class="active">用户列表</li>
                </ol>
                <div class="container-fluid">
                    <form class="form-horizontal" action="${ctx}/user/list" method="post">
                        <input type="hidden" name="pageInfo.currentPage" value="1">
                        <div class="form-group">
                            <a href="${ctx}/user/input" class="btn btn-primary" role="button">创建用户</a>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-4">
                                <div class="input-group col-xs-10">
                                    <span class="input-group-addon">用户名：</span>
                                    <input type="text" name="username" class="form-control col-xs-4" value="${userForm.username}">
                                </div>
                            </div>
                            <div class="col-xs-4">
                                <div class="input-group col-xs-10">
                                    <span class="input-group-addon">用户姓名：</span>
                                    <input type="text" name="name" class="form-control" value="${userForm.name}">
                                </div>
                            </div>
                            <div class="col-xs-4">
                                <div class="input-group col-xs-10">
                                    <span class="input-group-addon">用户邮箱：</span>
                                    <input type="text" name="email" class="form-control" value="${userForm.email}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-4">
                                <div class="input-group col-xs-10">
                                    <span class="input-group-addon">手机号：</span>
                                    <input type="text" name="mobile" class="form-control" value="${userForm.mobile}">
                                </div>
                            </div>
                            <div class="col-xs-4">
                                <div class="input-group col-xs-6">
                                    <span class="input-group-addon">是否有效：</span>
                                    <select name="validValue" class="form-control">
                                        <c:forEach items="${validListAll}" var="valid">
                                            <option value="${valid.value}" <c:if test="${userForm.validValue == valid.value}">selected="selected"</c:if>>${valid.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-4">
                                <div class="input-group col-xs-6">
                                    <span class="input-group-addon">性别：</span>
                                    <select name="genderValue" class="form-control">
                                        <c:forEach items="${genderListAll}" var="gender">
                                            <option value="${gender.value}" <c:if test="${userForm.genderValue == gender.value}">selected="selected"</c:if>>${gender.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-8">
                                <div class="input-group col-xs-6">
                                    <span class="input-group-addon">排序方式：将</span>
                                    <select name="orderType" class="form-control col-xs-4">
                                        <option value="id">id</option>
                                        <option value="createdTime" <c:if test="${userForm.genderValue == 'createdTime'}">selected="selected"</c:if>>创建时间</option>
                                    </select>
                                    <span class="input-group-addon">字段，按</span>
                                    <select name="sortType" class="form-control" placeholder=".col-xs-2">
                                        <option value="asc">升序</option>
                                        <option value="desc" <c:if test="${userForm.sortType == 'desc'}">selected="selected"</c:if>>降序</option>
                                    </select>
                                    <span class="input-group-addon">排序</span>
                                </div>
                            </div>
                            <div class="col-xs-4">
                                <button class="btn btn-default">查询</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="container-fluid">
                    <div class="">
                        <legend class=""><h4>用户列表</h4></legend>
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
                                            <td>${user.valid.name}</td>
                                            <td><a href="${ctx}/user/input/${user.id}">修改</a></td>
                                            <td><a href="${ctx}/user/password/input/${user.id}">重置密码</a></td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${user.valid.value == 1}">
                                                        <a href="${ctx}/user/disable/${user.id}">禁用</a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="${ctx}/user/enable/${user.id}">启用</a>
                                                    </c:otherwise>
                                                </c:choose>
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
            </div>
        </div>
    </body>
</html>