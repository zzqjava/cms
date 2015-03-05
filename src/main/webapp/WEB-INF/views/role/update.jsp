<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN" class="bg-dark">
    <head>
        <title>添加角色</title>
        <script type="text/javascript">
            $(function() {
                $("#sub").click(function(){
                    var roleId = $("#roleId").val();
                    if (roleId != null && roleId != "") {
                        var url = "${ctx}/role/update";
                        $("#theform").attr("action",url);
                        $("#theform").submit();
                    } else {
                        $("#theform").submit();
                    }
                });
            });
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
                        <li class="${ctx}/role/list"><a href="#">角色管理</a></li>
                    </ul>
                    <div class="m-b-md">
                        <h3 class="m-b-none">
                            角色修改
                        </h3>
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
                    <section class="panel panel-default">
                        <div class="panel-body">
                            <form:form class="form-horizontal" id="theform" action="${ctx}/role/create" method="post" commandName="roleForm">
                                <input type="hidden" class="form-control" id="roleId" name="id" value="${roleForm.id}">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">角色名</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="name" value="${roleForm.name}" required="">
                                    </div>
                                </div>
                                <div class="lines line-dashed line-lg pull-in"></div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">角色标识符</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="identifier" value="${roleForm.identifier}" required="">
                                    </div>
                                </div>
                                <div class="lines line-dashed line-lg pull-in"></div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">描述</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="description" value="${roleForm.description}">
                                    </div>
                                </div>
                                <div class="lines line-dashed line-lg pull-in"></div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">是否默认角色</label>
                                    <div class="col-sm-10">
                                        <form:select path="isDefault" items="${yesNoStatuses}" class="form-control" name="isDefault" itemValue="value" itemLabel="name"/>
                                    </div>
                                </div>
                                <div class="lines line-dashed line-lg pull-in"></div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">是否有效</label>
                                    <div class="col-sm-10">
                                        <form:select path="valid" items="${enableDisableStatus}" class="form-control" name="valid" itemValue="value" itemLabel="name"/>
                                    </div>
                                </div>
                                <div class="lines line-dashed line-lg pull-in"></div>
                                <div class="form-group">
                                    <div class="col-sm-4 col-sm-offset-2">
                                        <button type="submit" class="btn btn-default" id="sub">提交</button>
                                        <a class="btn btn-info" href="${ctx}${forwardUrl}">返回</a>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </section>
                </section>
            </section>
            <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
        </section>
    </body>
</html>