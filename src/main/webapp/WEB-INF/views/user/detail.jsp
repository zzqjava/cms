<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN" class="bg-dark">
    <head>
        <title>用户查看</title>
    </head>
    <body>
        <section id="content">
            <section class="vbox">
                <section class="scrollable padder">
                    <ul class="breadcrumb no-border no-radius b-b b-light pull-in">
                        <li><a href="${ctx}/dashboard"><i class="fa fa-home"></i> 主页</a></li>
                        <li><a href="#">系统管理</a></li>
                        <li class="${ctx}/user/list"><a href="#">用户管理</a></li>
                    </ul>
                    <div class="m-b-md">
                        <h3 class="m-b-none">用户查看</h3>
                    </div>
                    <section class="panel panel-default">
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped b-t b-light">
                                    <tbody>
                                    <tr>
                                        <td>编码</td>
                                        <td>${user.id}</td>
                                    </tr>
                                    <tr>
                                        <td>用户名</td>
                                        <td>${user.username}</td>
                                    </tr>
                                    <tr>
                                        <td>密码</td>
                                        <td>${user.password}</td>
                                    </tr>
                                    <tr>
                                        <td>姓名</td>
                                        <td>${user.name}</td>
                                    </tr>
                                    <tr>
                                        <td>角色</td>
                                        <td>
                                            <c:forEach items="${roleList}" var="role">
                                                ${role.name}&nbsp;&nbsp;&nbsp;
                                            </c:forEach>
                                        </td>
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
                                    <tr>
                                        <td>创建时间</td>
                                        <td>${user.createdTime}</td>
                                    </tr>
                                    <tr>
                                        <td>更新时间</td>
                                        <td>${user.updatedTime}</td>
                                    </tr>
                                    <tr>
                                        <td>本次登录时间</td>
                                        <td>${user.loginTime}</td>
                                    </tr>
                                    <tr>
                                        <td>上次登录时间</td>
                                        <td>${user.lastLoginTime}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <a class="btn btn-info" href="${ctx}${forwardUrl}">返回</a>
                        </div>
                    </section>
                </section>
            </section>
            <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
    </body>
</html>