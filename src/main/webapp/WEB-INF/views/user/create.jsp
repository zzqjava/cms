<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN" class="bg-dark">
<head>
    <title>欢迎</title>
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
                    用户添加
                </h3>
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
            </div>
            <section class="panel panel-default">
                <div class="panel-body">
                    <form:form id="theForm" class="form-horizontal" action="${ctx}/user/create" method="post" commandName="userForm">
                        <input id="id" type="hidden" name="id" value="${userForm.id}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="username" value="${userForm.username}" autofocus="" data-required="true" />
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
                                <form:radiobuttons path="genderValue" items="${genderList}" itemValue="value" itemLabel="name" delimiter="&nbsp;"/>
                                <%--<c:forEach items="${genderList}" var="gender">--%>
                                    <%--<input type="radio" name="genderValue" <c:if test="${gender.value == userForm.genderValue}">checked="checked"</c:if> value="${gender.value}">&nbsp;&nbsp;${gender.name}&nbsp;&nbsp;--%>
                                <%--</c:forEach>--%>
                            </div>
                        </div>
                        <div class="lines line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否有效</label>
                            <div class="col-sm-10">
                                <form:radiobuttons path="validValue" items="${enableDisableStatusList}" itemValue="value" itemLabel="name" delimiter="&nbsp;"/>
                                <%--<c:forEach items="${enableDisableStatusList}" var="valid">--%>
                                    <%--<input type="radio" name="validValue" <c:if test="${valid.value == userForm.validValue}">checked="checked"</c:if> value="${valid.value}">&nbsp;&nbsp;${valid.name}&nbsp;&nbsp;--%>
                                <%--</c:forEach>--%>
                            </div>
                        </div>
                        <div class="lines line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button id="sub" type="submit" class="btn btn-default">提交</button>
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