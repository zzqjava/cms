<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>菜单管理</title>
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="keywords" content="" />
    <meta http-equiv="description" content="" />
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/main.css">
    <script src="${ctx}/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>
    <script src="${ctx}/js/bootstrap-alert.js"></script>
    <script src="${ctx}/js/bootstrap-transition.js"></script>
    <script src="${ctx}/js/bootstrap-button.js"></script>

    <script type="text/javascript">
        $(function(){
            //回显
            $("#type option").each(function() {
                if ($(this).val() == '${resourceForm.type}') {
                    $(this).attr("selected", "selected");
                }
            });
            $("#validValue option").each(function() {
                if ($(this).val() == '${resourceForm.validValue}') {
                    $(this).attr("selected", "selected");
                }
            });
        })

        //添加
        function save() {
            $("#theForm").attr("action", "${ctx}/resource/create");
            $("#theForm").submit();
        }
        //修改
        function update() {
            $("#theForm").attr("action", "${ctx}/resource/update");
            $("#theForm").submit();
        }

        //定时关闭提示信息
        var successMessage = '${successMessage}';
        if (successMessage != null && successMessage != '') {
            closeSuccess();
        }
        function closeSuccess() {
            setTimeout("closeSuccessTip()",3000);
        }
        function closeSuccessTip(){
            $('#tipSuccess').click();
        }
        var errorMessage = '${errorMessage}';
        if (errorMessage != null && errorMessage != '') {
            closeError();
        }
        function closeError() {
            setTimeout("closeErrorTip()",3000);
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
                <li class="${ctx}/user/list"><a href="#">资源管理</a></li>
                <li class="active">
                    <c:choose>
                        <c:when test="${resourceForm.id == null}">
                            添加资源
                        </c:when>
                        <c:otherwise>
                            修改资源
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
            <section class="panel panel-default">
                <header class="panel-heading font-bold">
                    <c:choose>
                        <c:when test="${resourceForm.id == null}">
                            添加资源
                        </c:when>
                        <c:otherwise>
                            修改资源
                        </c:otherwise>
                    </c:choose>
                </header>
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
                <div class="panel-body">
                    <form:form class="form-horizontal" id="theForm" action="#" method="post">
                        <input type="hidden" id="id" name="id" value="${resourceForm.id}">
                        <input class="form-control" type="hidden" name="parentID" value="${resourceForm.parentID}">
                        <input class="form-control" type="hidden" name="treeLevel"  value="${resourceForm.treeLevel}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">资源名称</label>
                            <div class="col-sm-10">
                                <input class="form-control" type="text" name="name" placeholder="资源名称不能为空" value="${resourceForm.name}" data-required="true" required="">
                            </div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">资源链接</label>
                            <div class="col-sm-10">
                                <input class="form-control" type="text" name="url" placeholder="${resourceForm.url}" value="${resourceForm.url}" required="">
                            </div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">资源权限</label>
                            <div class="col-sm-10">
                                <input class="form-control" type="text" name="identifier" placeholder="${resourceForm.identifier}" value="${resourceForm.identifier}" required="">
                            </div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">菜单排序</label>
                            <div class="col-sm-10">
                                <input class="form-control" type="text" name="priority" placeholder="${resourceForm.priority}" value="${resourceForm.priority}" required="">
                            </div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">菜单类型</label>
                            <div class="col-sm-1">
                                <select class="form-control" name="type" id="type">
                                    <c:forEach items="${resourcesTypeItems}" var="resourcesTypeItem">
                                        <option value="${resourcesTypeItem.value}">${resourcesTypeItem.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否有效</label>
                            <div class="col-sm-1">
                                <select class="form-control" id="validValue" name="validValue">
                                    <c:forEach items="${enableDisableStatus}" var="ed">
                                        <option value="${ed.value}">${ed.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">菜单备注</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" rows="3" name="memo">${resourceForm.memo}</textarea>
                            </div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <c:choose>
                                    <c:when test="${resourceForm.id == null}">
                                        <button class="btn btn-default" id="sub" name="sub" onclick="save()" type="button" data-complete-text="正在提交...">添加</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-default" id="sub" name="sub" onclick="update()" type="button" data-complete-text="正在提交...">修改</button>
                                    </c:otherwise>
                                </c:choose>
                                <a class="btn btn-default" href="${ctx}${forwardUrl}">返回</a>
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