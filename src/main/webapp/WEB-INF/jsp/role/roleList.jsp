<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title> 角色列表</title>
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="keywords" content="" />
    <meta http-equiv="description" content="" />
    <%
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/main.css">
    <script src="${ctx}/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>
    <script src="${ctx}/js/bootstrap-paginator.js"></script>
    <script type="text/javascript">
        $(function(){
            //分页功能
            var options = {
                size:"large",
                bootstrapMajorVersion:3,
                currentPage:${roleForm.pageInfo.currentPage},
                totalPages:${roleForm.pageInfo.totalPages},
                numberOfPages:10,
                pageUrl:function(type,page){
                    return "${ctx}/role/list/"+page;
                },
                onPageClicked:null,
                onPageChanged:null
            }
            $('#pageDiv').bootstrapPaginator(options);
        })

        //定时关闭提示信息
        var message = '${message}';
        if (message != null && message != '') {
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
        //添加
        function create () {
            $("#theForm").attr("action", "${ctx}/role/input");
            $("#theForm").submit();
        }
        //修改
        function update (id) {
            $("#theForm").attr("action", "${ctx}/role/input");
            $("#roleId").val(id);
            $("#theForm").submit();
        }
        //删除
        function del (id) {
            if (confirm('确定删除该条记录?')) {
                $("#theForm").attr("action", "${ctx}/role/del/"+id);
                $("#theForm").submit();
            }
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
<br /><br /><br />
<div style="width: 60%;text-align: center;margin-left: auto; margin-right: auto;">
    <div class="panel panel-default">
        <c:if test="${message != null}" >
            <div class="alert alert-success fade in">
                <a class="close" data-dismiss="alert" href="#" id="tipSuccess">×</a>
                    ${message}
            </div>
        </c:if>
        <c:if test="${errorMessage != null}" >
            <div class="alert alert-danger fade in">
                <a class="close" data-dismiss="alert" href="#" id="tipError">×</a>
                    ${errorMessage}
            </div>
        </c:if>
        <div class="panel-heading" style="text-align: left">
            <span class="input-group-btn">
                <button class="btn btn-primary" id="list" name="list" type="button" onclick="window.location.href='${ctx}/role/list/${pageInfo.currentPage}';">角色列表</button>&nbsp;&nbsp;
                <button class="btn btn-primary" id="input" name="input" type="button" onclick="window.location.href='${ctx}/role/input';">添加角色</button>
            </span>
        </div>
        <table class="table table-hover table-striped">
            <thead>
            <tr class="success">
                <td>编码</td><td>角色名称</td><td>角色描述</td><td>是否有效</td><td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="role" varStatus="status">
                <tr <c:if test="${status.count%2==0}">class="warning"</c:if>>
                    <td>${role.id}</td>
                    <td>${role.roleName}</td>
                    <td>${role.roleDesc}</td>
                    <td>${role.valid.name}</td>
                    <td>
                        <button class="btn btn-primary btn-sm" id="update" name="update" type="button" onclick="update(${role.id})">修改</button>
                        <button class="btn btn-primary btn-sm" id="del" name="del" type="button" onclick="del(${role.id})">删除</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <form id="theForm" style="display:none;" method="post" action="">
        <input type="hidden" id="currentPage" name="currentPage" value="${pageInfo.currentPage}"/>
        <input type="hidden" id="roleId" name="id" />
    </form>
    <div class="pagination-lg">
        <ul id='pageDiv'></ul>
    </div>
</div>
<div class="text-center bottom">
    Copyright &copy; 2007-2014 qatang.com All rights reserved.
</div>
</body>
</html>