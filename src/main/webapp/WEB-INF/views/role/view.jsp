<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
    <head>
        <title>角色查看</title>
    </head>
    <body>
        <section id="content">
            <section class="vbox">
                <section class="scrollable padder">
                    <ul class="breadcrumb no-border no-radius b-b b-light pull-in">
                        <li><a href="${ctx}/dashboard"><i class="fa fa-home"></i> 主页</a></li>
                        <li><a href="#">系统管理</a></li>
                        <li class="active"><a href="#">角色管理</a></li>
                    </ul>
                    <div class="m-b-md">
                        <h3 class="m-b-none">角色查看</h3>
                    </div>
                    <section class="panel panel-default">
                        <div class="panel-body">
                            <table class="table table-bordered">
                                <tbody>
                                <tr>
                                    <td>角色名</td>
                                    <td>${role.name}</td>
                                </tr>
                                <tr>
                                    <td>标识符</td>
                                    <td>${role.identifier}</td>
                                </tr>
                                <tr>
                                    <td>描述</td>
                                    <td>${role.description}</td>
                                </tr>
                                <tr>
                                    <td>是否默认角色</td>
                                    <td>${role.isDefault.name}</td>
                                </tr>
                                <tr>
                                    <td>是否有效</td>
                                    <td>${role.valid.name}</td>
                                </tr>
                                </tbody>
                            </table>
                            <a class="btn btn-primary btn-sm" href="${ctx}${forwardUrl}">返回列表</a>
                        </div>
                    </section>
                </section>
            </section>
        </section>
    </body>
</html>