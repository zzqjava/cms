<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN" class="bg-dark">
<head>
    <title>欢迎</title>
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
                <li class="${ctx}/user/list"><a href="#">用户管理</a></li>
            </ul>
            <div class="m-b-md">
                <h3 class="m-b-none">
                    <c:choose>
                        <c:when test="${userForm.id == null}">
                            用户添加
                        </c:when>
                        <c:otherwise>
                            用户修改
                        </c:otherwise>
                    </c:choose>
                </h3>
            </div>
            <section class="panel panel-default">
                <div class="panel-body">
                    <form:form class="form-horizontal" action="${ctx}/user/create" method="post">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="username" value="${userForm.username}" autofocus="" data-required="true">
                            </div>
                        </div>
                        <div class="lines line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="password" value="${userForm.password}" required="">
                            </div>
                        </div>
                        <div class="lines line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">确认密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="conPassword" value="${userForm.conPassword}" required="">
                            </div>
                        </div>
                        <div class="lines line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="name" value="${userForm.name}" required="">
                            </div>
                        </div>
                        <div class="lines line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">邮箱</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="email" value="${userForm.email}" required="">
                            </div>
                        </div>
                        <div class="lines line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">手机号</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="mobile" value="${userForm.mobile}">
                            </div>
                        </div>
                        <div class="lines line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">QQ</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="QQ" value="${userForm.QQ}">
                            </div>
                        </div>
                        <div class="lines line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-10">
                                <c:forEach items="${genderList}" var="gender">
                                    <input type="radio" name="genderValue" <c:if test="${gender.value == userForm.genderValue}">checked="checked"</c:if>>&nbsp;&nbsp;${gender.name}&nbsp;&nbsp;
                                </c:forEach>
                                <%--<form:radiobuttons path="genderValue" items="${genderList}"/>--%>
                            </div>
                        </div>
                        <div class="lines line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否有效</label>
                            <div class="col-sm-10">
                                <c:forEach items="${enableDisableStatusList}" var="valid">
                                    <input type="radio" name="validValue" <c:if test="${valid.value == userForm.validValue}">checked="checked"</c:if>>&nbsp;&nbsp;${valid.name}&nbsp;&nbsp;
                                </c:forEach>
                                <%--<form:radiobuttons path="validValue" items="${enableDisableStatusList}"/>--%>
                            </div>
                        </div>
                        <div class="lines line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button type="submit" class="btn btn-default">提交</button>
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