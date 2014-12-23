<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN" class="bg-dark">
<head>
    <title>添加角色</title>
    <script src="${ctx}/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript">
        $(function() {
            var id = $("#userId").val();
            $.ajax( {
                type : "POST",
                url : "${ctx}/user/ajax/roles.do",
                data : {"id": id},
//                dataType: "json",
                dataType: "text",
                success : function(data) {
//                    if (data.success) {
//                        $("#roles").html(data.roles);
//                    }
                    $("#roles").html(data);
                }
            });
            $("#sub").click(function(){
                var roleId = $("#roleId").val();
                if (roleId != null && roleId != "") {
                    $("#theform").attr("action","${ctx}/role/update");
                    $("#theform").submit();
                } else {
                    $("#theform").submit();
                }
            });
        });
    </script>
</head>
<body>
<section id="content">
    <section class="vbox">
        <section class="scrollable padder">
            <ul class="breadcrumb no-border no-radius b-b b-light pull-in">
                <li><a href="${ctx}/dashboard"><i class="fa fa-home"></i> 主页</a></li>
                <li><a href="#">系统管理</a></li>
                <li class="${ctx}/user/list"><a href="#">角色管理</a></li>
                <li class="active">
                    <c:choose>
                    <c:when test="${roleForm.id == null}">
                        角色添加
                    </c:when>
                    <c:otherwise>
                        角色修改
                    </c:otherwise>
                </c:choose></li>
            </ul>
            <div class="m-b-md">
                <h3 class="m-b-none">
                </h3>
            </div>
            <section class="panel panel-default">
                <header class="panel-heading font-bold">
                    <c:choose>
                        <c:when test="${roleForm.id == null}">
                            角色添加
                        </c:when>
                        <c:otherwise>
                            角色修改
                        </c:otherwise>
                    </c:choose>
                </header>
                <div class="panel-body">
                    <form:form class="form-horizontal" id="theform" action="${ctx}/role/create" method="post">
                        <input type="hidden" class="form-control" id="roleId" name="roleId" value="${roleForm.id}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">角色名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="name" value="${roleForm.name}" autofocus="" data-required="true">
                            </div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">角色标识符</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="identifier" value="${roleForm.identifier}" required="">
                            </div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">描述</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="description" value="${roleForm.description}" required="">
                            </div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否默认值</label>
                            <div class="col-sm-10">
                                <label class="radio-inline">
                                    <input type="radio" name="isDefault" checked value="1"> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="isDefault" value="0"> 否
                                </label>
                            </div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否有效</label>
                            <div class="col-sm-10">
                                <%--<form:select path="enableDisableStatus" items="${enableDisableStatus}"/>--%>
                                <%--<form:checkbox path="enableDisableStatus" items="${enableDisableStatus}" itemValue="value" class="form-control" itemLabel="name" name="valid"/>--%>
                                <label class="radio-inline">
                                    <input type="radio" name="valid" checked value="1"> 是
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="valid" value="0"> 否
                                </label>
                            </div>
                        </div>
                        <div class="line line-dashed line-lg pull-in"></div>
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