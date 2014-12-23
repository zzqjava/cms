<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn" class="bg-dark">
    <head>
        <title>角色列表</title>
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
                        <li class="active"><a href="#">角色管理</a></li>
                    </ul>
                    <div class="m-b-md">
                        <h3 class="m-b-none">角色管理</h3>
                    </div>
                    <section class="panel panel-default">
                        <div class="row wrapper">
                            <div class="col-sm-5 m-b-xs">
                                <a href="${ctx}/role/input"> <button class="btn btn-sm btn-default">添加角色</button></a>
                            </div>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-striped b-t b-light">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>编号</th>
                                    <th>标识符</th>
                                    <th>角色名</th>
                                    <th>描述</th>
                                    <th>是否默认值</th>
                                    <th>是否有效</th>
                                    <th colspan="3">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${roleList}" var="role" varStatus="status">
                                    <tr>
                                        <td>${status.count}</td>
                                        <td>${role.id}</td>
                                        <td>${role.identifier}</td>
                                        <td><a href="${ctx}/role/view/${role.id}">${role.name}</a></td>
                                        <td>${role.description}</td>
                                        <td>${role.isDefault.name}</td>
                                        <td>${role.valid.name}</td>
                                        <td><a href="${ctx}/role/input/${role.id}">修改</a></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${role.valid.value == 1}">
                                                    <a href="${ctx}/role/disable/${role.id}">禁用</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="${ctx}/role/enable/${role.id}">启用</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </section>
                </section>
            </section>
        </section>
    </body>
</html>