<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn" class="bg-dark">
    <head>
        <title>角色列表</title>
        <script type="text/javascript">
            var goPage = function(page) {
                $("#page").val(page);
                $("#pageForm").submit();
            }
            //定时关闭提示信息
            var successMessage = '${successMessage}';
            if (successMessage != null && successMessage != '') {
                closeSuccess();
            }
            function closeSuccess() {
                setTimeout("closeSuccessTip()",2000);
            }
            function closeSuccessTip(){
                $('#tipSuccess').click();
            }
            var errorMessage = '${errorMessage}';
            if (errorMessage != null && errorMessage != '') {
                closeError();
            }
            function closeError() {
                setTimeout("closeErrorTip()",2000);
            }
            function closeErrorTip(){
                $('#tipError').click();
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
                    <c:if test="${successMessage != null}" >
                        <div class="alert alert-success fade in">
                            <a class="close" data-dismiss="alert" href="#" id="tipSuccess">×</a>
                                ${successMessage}
                        </div>
                    </c:if>
                    <c:if test="${errorMessage != null}" >
                        <div class="alert alert-danger fade in">
                            <a class="close" data-dismiss="alert" href="#" id="tipError">×</a>
                                ${errorMessage}
                        </div>
                    </c:if>
                    <div class="row wrapper">
                        <div class="col-sm-5 m-b-xs">
                            <a href="${ctx}/role/create"> <button class="btn btn-sm btn-default">添加角色</button></a>
                        </div>
                    </div>
                    <section class="panel panel-default">
                        <form class="form-inline" id="queryForm" action="${ctx}/role/list" method="post" commandName="roleForm">
                            <div class="row wrapper">
                                <div class="col-sm-4 m-b-xs">
                                    <div class="input-group">
                                        <span class="input-group-addon">角色名：</span>
                                        <input type="text" id="name" name="name" value="${roleForm.name}" class="form-control">
                                    </div>
                                </div>
                                <div class="col-sm-2 m-b-xs">
                                    <div class="input-group">
                                        <span class="input-group-addon ">是否有效：</span>
                                        <form:select path="queryEnableDisableStatus" items="${queryEnableDisableStatus}" itemValue="value" class="form-control" itemLabel="name" id="valid" name="valid"/>
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
                                    <th>编号</th>
                                    <th>角色名称</th>
                                    <th>标识符</th>
                                    <th>描述</th>
                                    <th>是否默认角色</th>
                                    <th>是否有效</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${roleList}" var="role" varStatus="status">
                                    <tr>
                                        <td>${status.count}</td>
                                        <td>${role.id}</td>
                                        <td><a href="${ctx}/role/detail/${role.id}">${role.name}</a></td>
                                        <td>${role.identifier}</td>
                                        <td>${role.description}</td>
                                        <td>${role.isDefault.name}</td>
                                        <td>${role.valid.name}</td>
                                        <td><a href="${ctx}/role/update/${role.id}">修改</a>
                                            <c:choose>
                                                <c:when test="${role.valid.value == 1}">
                                                    <a href="${ctx}/role/validate/${role.id}">禁用</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="${ctx}/role/validate/${role.id}">启用</a>
                                                </c:otherwise>
                                            </c:choose>
                                            <a href="${ctx}/roleResource/allot/${role.id}">分配资源</a>
                                        </td>
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
                                    <form id="pageForm" class="form-inline" action="${ctx}/role/list" method="post">
                                        <input id="page" type="hidden" name="pageInfo.currentPage">
                                        <ul class="pagination pagination-sm m-t-none m-b-none">
                                            <c:if test="${roleForm.pageInfo.currentPage > 1}">
                                                <li><a style="cursor:pointer;" onclick="goPage(${roleForm.pageInfo.currentPage - 1});"><i class="fa fa-chevron-left"></i></a></li>
                                            </c:if>
                                            <c:forEach begin="1" end="${roleForm.pageInfo.totalPages}" var="i">
                                                <li><a onclick="goPage(${i});" style="cursor:pointer;<c:if test="${roleForm.pageInfo.currentPage == i}"> background-color:#EEE;</c:if>">${i}</a></li>
                                            </c:forEach>
                                            <c:if test="${roleForm.pageInfo.currentPage < roleForm.pageInfo.totalPages}">
                                                <li><a style="cursor:pointer;" onclick="goPage(${roleForm.pageInfo.currentPage + 1});"><i class="fa fa-chevron-right"></i></a></li>
                                            </c:if>
                                        </ul>
                                    </form>
                                </div>
                            </div>
                        </footer>
                    </section>
                </section>
            </section>
        </section>
    </body>
</html>