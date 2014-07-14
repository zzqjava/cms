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
</head>
<body>
    <div>
        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">后台管理系统</a>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="login-box text-center" >
                <div class="login-single-panel-header">
                    <c:choose>
                        <c:when test="${menu.id == null}">
                            <div class="titlediv"><h3>添加菜单</h3></div>
                        </c:when>
                        <c:otherwise>
                            <div class="titlediv"><h3>修改菜单</h3></div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="">
                    <legend class=""></legend>
                </div>
                <div>
                    <h5 style="color:red">${errorMessage}</h5>
                </div>
                <form id="theform" class="form-signup-heading" action="${ctx}/menu/save" method="post">
                    <input type="hidden" id="id" name="id" value="${menu.id}">
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">菜单名称：</div>
                            <input class="form-control" type="text" name="name" placeholder="菜单名称不能为空" value="${menu.name}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">url：</div>
                            <input class="form-control" type="text" name="url" placeholder="${menu.url}" value="${menu.url}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">菜单排序值：</div>
                            <input class="form-control" type="text" name="orderValue" placeholder="${menu.orderView}" value="${menu.orderView}">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group col-sm-5">
                            <div class="input-group-addon">是否有效：</div>
                            <select class="form-control" name="validValue" id="validValue">
                                <c:forEach items="${yesNoStatuses}" var="yesNoStatuse">
                                    <option value="${yesNoStatuse.value}">${yesNoStatuse.name}</option>
                                </c:forEach>
                            </select>
<%--                            <input class="form-control" type="text" name="validValue" placeholder="${menu.valid}" value="${menu.valid}">--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <div class="input-group-addon">备注：</div>
                            <input class="form-control" type="text" name="memo" placeholder="${menu.memo}" value="${menu.memo}">
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-lg">提交</button>
                        <a class="btn btn-primary btn-lg" href="javascript:window.history.go(-1);">返回</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>