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
                    <c:when test="${menuForm.id == null}">
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
            <c:choose>
                <c:when test="${menuForm.id == null}">
                    <form id="theform" class="form-signup-heading" action="${ctx}/menu/create" method="post">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">菜单名称：</div>
                                <input class="form-control" type="text" name="name" placeholder="菜单名称不能为空" value="${menuForm.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">url：</div>
                                <input class="form-control" type="text" name="url" placeholder="${menuForm.url}" value="${menuForm.url}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">菜单排序值：</div>
                                <input class="form-control" type="text" name="orderLevelValue" placeholder="${menuForm.orderLevelValue}" value="${menuForm.orderLevelValue}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group col-sm-5">
                                <div class="input-group-addon">是否有效：</div>
                                <select class="form-control" id="validValue" name="validValue">
                                    <c:forEach items="${enableDisableStatus}" var="ed">
                                        <option value="${ed.value}">${ed.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">备注：</div>
                                <textarea class="form-control" rows="3" name="memo">${menuForm.memo}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-lg">提交</button>
                            <a class="btn btn-primary btn-lg" href="javascript:window.history.go(-1);">返回</a>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <form id="theform" class="form-signup-heading" action="${ctx}/menu/update" method="post">
                        <input type="hidden" id="id" name="id" value="${menuForm.id}">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">菜单名称：</div>
                                <input class="form-control" type="text" name="name" placeholder="菜单名称不能为空" value="${menuForm.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">url：</div>
                                <input class="form-control" type="text" name="url" placeholder="${menuForm.url}" value="${menuForm.url}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">菜单排序值：</div>
                                <input class="form-control" type="text" name="orderLevelValue" placeholder="${menuForm.orderLevelValue}" value="${menuForm.orderLevelValue}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group col-sm-5">
                                <div class="input-group-addon">是否有效：</div>
                                <select class="form-control" name="validValue">
                                    <c:forEach items="${enableDisableStatus}" var="ed">
                                        <option value="${ed.value}" <c:if test="${ed.value.toString() == menuForm.validValue}">selected="selected" </c:if> > ${ed.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">备注：</div>
                                <textarea class="form-control" rows="3" name="memo">${menuForm.memo}</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-lg">提交</button>
                            <a class="btn btn-primary btn-lg" href="javascript:window.history.go(-1);">返回</a>
                        </div>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>