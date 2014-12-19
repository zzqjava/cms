<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欢迎</title>
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="keywords" content="" />
    <meta http-equiv="description" content="" />
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/main.css">
    <script src="${ctx}/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/navi.jsp"/>
<div class="container-fluid">
<div class="col-lg-2">
    <jsp:include page="/WEB-INF/jsp/menu.jsp"/>
</div>
<div class="col-lg-10">
<ol class="breadcrumb">
    <li><a href="#">系统管理</a></li>
    <li><a href="${ctx}/user/list">用户管理</a></li>
    <li class="active">
    <c:choose>
        <c:when test="${userForm.id == null}">
            用户添加
        </c:when>
        <c:otherwise>
            用户修改
        </c:otherwise>
    </c:choose>
    </li>
</ol>
<div class="login-single-panel-header">
    <h5 style="color:red">${errorMessage}</h5>
</div>
<div class="">
    <legend class=""></legend>
</div>
<div class="container">
<c:choose>
    <c:when test="${userForm.id == null}">
        <form class="form-horizontal" action="${ctx}/user/create" method="post">
            <div class="form-group">
                <label class="col-lg-3 control-label">用户名</label>
                <div class="col-lg-6">
                    <input type="text" class="form-control" name="username" value="${userForm.username}" autofocus="" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">密码</label>
                <div class="col-lg-6">
                    <input type="password" class="form-control" name="password" value="${userForm.password}" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">确认密码</label>
                <div class="col-lg-6">
                    <input type="password" class="form-control" name="conPassword" value="${userForm.conPassword}" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">姓名</label>
                <div class="col-lg-6">
                    <input type="text" class="form-control" name="name" value="${userForm.name}" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">邮箱</label>
                <div class="col-lg-6">
                    <input type="text" class="form-control" name="email" value="${userForm.email}" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">手机号</label>
                <div class="col-lg-6">
                    <input type="text" class="form-control" name="mobile" value="${userForm.mobile}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">QQ</label>
                <div class="col-lg-6">
                    <input type="text" class="form-control" name="QQ" value="${userForm.QQ}">
                </div>
            </div>
            <div class="control-group">
                <label class="col-lg-3 control-label" style="width:23%;">性别</label>
                <div class="form-group">
                    <label class="radio-inline">
                        <input type="radio" name="genderValue" checked value="1"> 男
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="genderValue" value="2"> 女
                    </label>
                </div>
            </div>
            <div class="control-group">
                <label class="col-lg-3 control-label" style="width:23%;">是否有效</label>
                <div class="form-group">
                    <label class="radio-inline">
                        <input type="radio" name="validValue" checked value="1"> 是
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="validValue" value="2"> 否
                    </label>
                </div>
            </div>
            <div class="form-group">
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">提交</button>
                    <a class="btn btn-default" href="${ctx}${forwardUrl}">返回列表</a>
                </div>
            </div>
        </form>
    </c:when>
    <c:otherwise>
        <form:form class="form-horizontal" action="${ctx}/user/update" method="post" modelAttribute="userForm">
            <input type="hidden" name="id" value="${userForm.id}">
            <div class="form-group">
                <label class="col-lg-3 control-label">用户名</label>
                <div class="col-lg-6">
                    <input type="text" class="form-control" name="username" value="${userForm.username}" required="" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">姓名</label>
                <div class="col-lg-6">
                    <form:input cssClass="form-control" path="name" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">邮箱</label>
                <div class="col-lg-6">
                    <form:input cssClass="form-control" path="email" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">手机号</label>
                <div class="col-lg-6">
                    <form:input cssClass="form-control" path="mobile" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 control-label">QQ</label>
                <div class="col-lg-6">
                    <form:input cssClass="form-control" path="QQ" />
                </div>
            </div>
            <div class="control-group">
                <label class="col-lg-3 control-label" style="width:23%;">性别</label>
                <div class="form-group">
                    <form:radiobuttons path="genderValue" items="${genderList}" itemValue="value" itemLabel="name" delimiter="&nbsp;&nbsp;&nbsp;"/>
                </div>
            </div>
            <div class="control-group">
                <label class="col-lg-3 control-label" style="width:23%;">是否有效</label>
                <div class="form-group">
                    <form:radiobuttons path="validValue" items="${validList}" itemValue="value" itemLabel="name" delimiter="&nbsp;&nbsp;&nbsp;"/>
                </div>
            </div>
            <div class="form-group">
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">提交</button>
                    <a class="btn btn-default" href="${ctx}${forwardUrl}">返回列表</a>
                </div>
            </div>
        </form:form>
    </c:otherwise>
</c:choose>
</div>
</body>
</html>