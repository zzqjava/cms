<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN" class="bg-dark">
<head>
    <title>资源列表</title>
    <script type="text/javascript">
        $(function(){
            //分页功能
            <c:if test="${resourceForm.pageInfo.totalPages != null}">
            var options = {
                size:"small",
                bootstrapMajorVersion:3,
                currentPage:${resourceForm.pageInfo.currentPage},
                totalPages:${resourceForm.pageInfo.totalPages},
                numberOfPages:10,
                onPageClicked: function (e, originalEvent, type, page) {
                    var url = "${ctx}/resource/list/" + page ;
                    $("#theForm").attr("action", url);
                    var queryResourceName =  $("#queryResourceName").val();
                    var queryValid =  $("#queryValid").val();
                    $("#queryResourceNameId").val(queryResourceName);
                    $("#queryValidId").val(queryValid);
                    $("#theForm").submit();
                },
                onPageChanged:null
            }
            $('#pageDiv').bootstrapPaginator(options);
            </c:if>
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
        function validate(id){
            $.ajax({
                url:"${ctx}/resource/validate/" + id,
                type:"POST",
                dataType:"json",
                success:function(data){
                    var status_span = $("#status_"+id);
                    var status_valid_span = $("#status_valid_"+id);
                    status_span.empty();
                    status_valid_span.empty();
                    if (data.code=="0") {
                        if (data.status==1) {
                            var str = '<a href="javascript://" onclick="validate(\'' + id + '\');">禁用</a>';
                            var str1 = '<span id="status_valid_' + id + '">有效</span>';
                        } else {
                            var str = '<a href="javascript://" onclick="validate(\'' + id + '\');">开启</a>';
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
                                        <span class="input-group-addon">资源名称：</span>
                                        <input type="text" name="queryResourceName" id="queryResourceName" value="${resourceForm.queryResourceName}" class="form-control" placeholder="资源名称不能为空">
                                    </div>
                        </div>
                        <div class="col-sm-2 m-b-xs">
                                    <div class="input-group">
                                        <span class="input-group-addon ">是否有效：</span>
                                        <form:select path="queryEnableDisableStatus" items="${queryEnableDisableStatus}" itemValue="value" class="form-control" itemLabel="name" name="queryValid" id="queryValid"/>
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
                                <c:if test="${resource.treeLevel == 1}">
                                    <tr>
                                        <td><a href="${ctx}/resource/detail/${resource.id}">${resource.name}</a></td>
                                        <td>${resource.url}</td>
                                        <td>${resource.treeLevel}</td>
                                        <td>${resource.identifier}</td>
                                        <td>${resource.type.name}</td>
                                        <td>${resource.priority}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${resource.valid.value == 1}">
                                                    <span id="status_valid_${resource.id}">有效</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span id="status_valid_${resource.id}">无效</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <a href="${ctx}/resource/update/${resource.id}">修改</a>
                                            <c:choose>
                                                <c:when test="${resource.valid.value == 1}">
                                                    <span id="status_${resource.id}"><a href="javascript://" onclick="validate('${resource.id}')">禁用</a></span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span id="status_${resource.id}"><a href="javascript://" onclick="validate('${resource.id}')">启用</a></span>
                                                </c:otherwise>
                                            </c:choose>
                                            <a href="${ctx}/resource/create?treeLevel=2&parentID=${resource.id}">添加子资源</a>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:forEach var="second" items="${resource.children}">
                                    <c:if test="${second.treeLevel == 2}">
                                        <tr>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/resource/detail/${second.id}">${second.name}</a></td>
                                            <td>${second.url}</td>
                                            <td>${second.treeLevel}</td>
                                            <td>${second.identifier}</td>
                                            <td>${second.type.name}</td>
                                            <td>${second.priority}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${second.valid.value == 1}">
                                                        <span id="status_valid_${second.id}">有效</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span id="status_valid_${second.id}">无效</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>

                                            <td><a href="${ctx}/resource/update/${second.id}">修改</a>
                                                <c:choose>
                                                    <c:when test="${resource.valid.value == 1}">
                                                        <span id="status_${second.id}"><a href="javascript://" onclick="validate('${second.id}')">禁用</a></span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span id="status_${second.id}"><a href="javascript://" onclick="validate('${second.id}')">启用</a></span>
                                                    </c:otherwise>
                                                </c:choose>
                                                <%--<a href="#" onclick="create('3','${second.id}')">添加子资源</a>--%>
                                                <a href="${ctx}/resource/create?treeLevel=3&parentID=${second.id}" >添加子资源</a>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:forEach var="third" items="${second.children}">
                                        <tr>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/resource/detail/${third.id}">${third.name}</a></td>
                                            <td>${third.url}</td>
                                            <td>${third.treeLevel}</td>
                                            <td>${third.identifier}</td>
                                            <td>${third.type.name}</td>
                                            <td>${third.priority}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${third.valid.value == 1}">
                                                        <span id="status_valid_${third.id}">有效</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span id="status_valid_${third.id}">无效</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><a href="${ctx}/resource/update/${third.id}">修改</a>
                                                    <c:choose>
                                                        <c:when test="${third.valid.value == 1}">
                                                            <span id="status_${third.id}"><a href="javascript://" onclick="validate('${third.id}')">禁用</a></span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span id="status_${third.id}"><a href="javascript://" onclick="validate('${third.id}')">启用</a></span>
                                                        </c:otherwise>
                                                    </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:forEach>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
                <footer class="panel-footer">
                    <div class="row">
                        <div class="col-sm-12 text-right text-left-xs">
                            <ul id='pageDiv' class="pagination pagination-sm m-t-none m-b-none"></ul>
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