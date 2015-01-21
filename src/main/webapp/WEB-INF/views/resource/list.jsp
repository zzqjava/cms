<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN" class="bg-dark">
<head>
    <title>资源列表</title>
    <script type="text/javascript">
        $(function(){
            //回显
            $("#queryValid option").each(function() {
                if ($(this).val() == '${resourceForm.queryValid}') {
                    $(this).attr("selected", "selected");
                }
            });
        })

        function create(treeLevel, parentID) {
            $("#theForm").attr("action", "${ctx}/resource/create");
            $("#treeLevel").val(treeLevel);
            $("#parentID").val(parentID);
            $("#theForm").submit();
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
        function switchStatus(id){
            $.ajax({
                url:"${ctx}/resource/switchStatus/" + id,
                type:"POST",
                dataType:"json",
                success:function(data){
                    var status_span = $("#status_"+id);
                    var status_valid_span = $("#status_valid_"+id);
                    status_span.empty();
                    status_valid_span.empty();
                    if (data.code=="0") {
                        if (data.status==1) {
                            var str = '<a href="javascript://" onclick="switchStatus(\'' + id + '\');">禁用</a>';
                            var str1 = '<span id="status_valid_' + id + '">有效</span>';
                        } else {
                            var str = '<a href="javascript://" onclick="switchStatus(\'' + id + '\');">开启</a>';
                            var str1 = '<span id="status_valid_' + id + '">无效</span>';
                        }
                        status_span.html(str);
                        status_valid_span.html(str1);
                    }
                }
            });
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
                <li class="active"><a href="#">资源管理</a></li>
            </ul>
            <div class="m-b-md">
                <h3 class="m-b-none">资源管理</h3>
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
                <header class="panel-heading">
                    <a href="${ctx}/resource/create?treeLevel=1&parentID=0" class="btn btn-sm btn-default">创建资源</a>
                </header>
                <form class="form-inline" id="queryForm" action="${ctx}/resource/list" method="post">
                    <div class="row wrapper">
                        <div class="col-sm-4 m-b-xs">
                                <div class="input-group">
                                    <span class="input-group-addon input-sm">资源名称</span>
                                    <input type="text" name="queryResourceName" style="width: 75%" id="queryResourceName" value="${resourceForm.queryResourceName}" class="form-control" />
                                </div>
                        </div>
                        <div class="col-sm-2 m-b-xs">
                                    <div class="input-group">
                                        <span class="input-group-addon input-sm">是否有效</span>
                                        <form:select path="queryEnableDisableStatus" cssStyle="width:100px;" items="${queryEnableDisableStatus}" itemValue="value" class="form-control" itemLabel="name" name="queryValid" id="queryValid"/>
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
                            <th>资源名称</th>
                            <th>资源链接</th>
                            <th>资源级别</th>
                            <th>资源权限</th>
                            <th>资源类别</th>
                            <th>资源排序值</th>
                            <th>是否有效</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${resourceList != null && resourceList.size() > 0}">
                            <c:forEach var="resource" items="${resourceList}">
                                <tr>
                                    <td>
                                        <c:if test="${resource.treeLevel == 2}">
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
                                        <c:if test="${resource.treeLevel == 3}">
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
                                        <a href="${ctx}/resource/detail/${resource.id}">${resource.name}</a>
                                    </td>
                                    <td>${resource.url}</td>
                                    <td>${resource.treeLevel}</td>
                                    <td>${resource.identifier}</td>
                                    <td>${resource.type.name}</td>
                                    <td>${resource.priority}</td>
                                    <td>
                                        <span id="status_valid_${resource.id}">
                                            ${resource.valid.name}
                                        </span>
                                    </td>
                                    <td>
                                        <a href="${ctx}/resource/update/${resource.id}">修改</a>
                                        <a href="javascript://" id="status_${resource.id}" onclick="switchStatus('${resource.id}')">
                                            <c:choose>
                                                <c:when test="${resource.valid.value == 1}">
                                                    禁用
                                                </c:when>
                                                <c:otherwise>
                                                    启用
                                                </c:otherwise>
                                            </c:choose>
                                        </a>
                                        <c:if test="${resource.treeLevel == 1}">
                                            <a href="${ctx}/resource/create?treeLevel=2&parentID=${resource.id}">添加子资源</a>
                                        </c:if>
                                        <c:if test="${resource.treeLevel == 2}">
                                            <a href="${ctx}/resource/create?treeLevel=3&parentID=${resource.id}">添加子资源</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
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
                            ${resourceForm.pageString}
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