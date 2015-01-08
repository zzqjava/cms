<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN" class="bg-dark">
<head>
    <title>菜单列表d</title>
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

        function input(treeLevel, parentID) {
            $("#theForm").attr("action", "${ctx}/resource/input");
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
            <section class="panel panel-default">
                <div class="row wrapper">
                    <div class="col-sm-5 m-b-xs">
                        <form action="${ctx}/resource/input" method="post" id="theForm" >
                            <input type="hidden" id="treeLevel" name="treeLevel" value=""/>
                            <input type="hidden" id="parentID" name="parentID" value=""/>
                            <input type="hidden" id="queryResourceNameId" name="queryResourceName" value=""/>
                            <input type="hidden" id="queryValidId" name="queryValid" value=""/>
                            <a href="#" class="btn btn-sm btn-default"  onclick="input('1','0')">创建资源</a>
                        </form>
                    </div>
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
                <form class="form-inline" id="queryForm" action="${ctx}/resource/list" method="post">
                    <div class="text-center">
                        <table class="table table-hover table-striped">
                            <tr>
                                <td style="width: 45%">
                                    <div class="input-group">
                                        <span class="input-group-addon">资源名称：</span>
                                        <input type="text" name="queryResourceName" id="queryResourceName" value="${resourceForm.queryResourceName}" class="form-control" placeholder="资源名称不能为空">
                                    </div>
                                </td>
                                <td style="width: 25%">
                                    <div class="input-group">
                                        <span class="input-group-addon ">是否有效：</span>
                                        <form:select path="queryEnableDisableStatus" items="${queryEnableDisableStatus}" itemValue="value" class="form-control" itemLabel="name" name="queryValid" id="queryValid"/>
                                    </div>
                                </td>
                                <td style="width: 5%">
                                    <input class="btn btn-sm btn-default" id="query" name="query" type="submit" value="查询" />
                                </td>
                                <td style="width: 25%"></td>
                            </tr>
                        </table>
                    </div>
                </form>
                <div class="table-responsive">
                    <table class="table table-striped b-t b-light">
                        <thead>
                        <tr>
                            <th>菜单名</th>
                            <th>菜单链接</th>
                            <th>菜单级别</th>
                            <th>菜单权限</th>
                            <th>菜单类别</th>
                            <th>菜单排序值</th>
                            <th>是否有效</th>
                            <th>备注</th>
                            <th colspan="3">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${resourceList != null && resourceList.size() > 0}">
                            <c:forEach var="resource" items="${resourceList}">
                                <c:if test="${resource.treeLevel == 1}">
                                    <tr>
                                        <td><a href="${ctx}/resource/view/${resource.id}">${resource.name}</a></td>
                                        <td>${resource.url}</td>
                                        <td>${resource.treeLevel}</td>
                                        <td>${resource.identifier}</td>
                                        <td>${resource.type.name}</td>
                                        <td>${resource.priority}</td>
                                        <td>${resource.valid.name}</td>
                                        <td>${resource.memo}</td>
                                        <td><a href="${ctx}/resource/input/${resource.id}">修改</a></td>
                                        <td>
                                            <a href="${ctx}/resource/toggleValidStatus/${resource.id}">
                                                <c:choose>
                                                    <c:when test="${resource.valid.value == 1}">
                                                        禁用
                                                    </c:when>
                                                    <c:otherwise>
                                                        启用
                                                    </c:otherwise>
                                                </c:choose>
                                            </a>
                                        </td>
                                        <td><a href="#" onclick="input('2','${resource.id}')">添加子资源</a></td>
                                    </tr>
                                </c:if>
                                <c:forEach var="second" items="${resource.children}">
                                    <c:if test="${second.treeLevel == 2}">
                                        <tr>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/resource/view/${second.id}">${second.name}</a></td>
                                            <td>${second.url}</td>
                                            <td>${second.treeLevel}</td>
                                            <td>${second.identifier}</td>
                                            <td>${second.type.name}</td>
                                            <td>${second.priority}</td>
                                            <td>${second.valid.name}</td>
                                            <td>${second.memo}</td>
                                            <td><a href="${ctx}/resource/input/${second.id}">修改</a></td>
                                            <td>
                                                <a href="${ctx}/resource/toggleValidStatus/${second.id}">
                                                    <c:choose>
                                                        <c:when test="${second.valid.value == 1}">
                                                            禁用
                                                        </c:when>
                                                        <c:otherwise>
                                                            启用
                                                        </c:otherwise>
                                                    </c:choose>
                                                </a>
                                            </td>
                                            <td>
                                                <a href="#" onclick="input('3','${second.id}')">添加子资源</a>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:forEach var="third" items="${second.children}">
                                        <tr>
                                            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/resource/view/${third.id}">${third.name}</a></td>
                                            <td>${third.url}</td>
                                            <td>${third.treeLevel}</td>
                                            <td>${third.identifier}</td>
                                            <td>${third.type.name}</td>
                                            <td>${third.priority}</td>
                                            <td>${third.valid.name}</td>
                                            <td>${third.memo}</td>
                                            <td><a href="${ctx}/resource/input/${third.id}">修改</a></td>
                                            <td>
                                                <a href="${ctx}/resource/toggleValidStatus/${third.id}">
                                                    <c:choose>
                                                        <c:when test="${third.valid.value == 1}">
                                                            禁用
                                                        </c:when>
                                                        <c:otherwise>
                                                            启用
                                                        </c:otherwise>
                                                    </c:choose>
                                                </a>
                                            </td>
                                            <td></td>
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