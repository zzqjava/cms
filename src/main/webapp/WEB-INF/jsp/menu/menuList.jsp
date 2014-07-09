<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>菜单列表</title>
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
        <div>
            <div class="container">
                <div style="margin-top:60px;">
                    <a href="${ctx}/menu/input" class="btn btn-primary btn-lg" role="button">添加菜单</a>
                </div>
                <div style="margin-top:10px;">
                    <div class="text-center">
                        <c:if test="${menuList != null && menuList.size() > 0}">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="text-center" width="5%">序号</th>
                                <th class="text-center" width="10%">菜单名</th>
                                <th class="text-center" width="20%">菜单链接</th>
                                <th class="text-center" width="20%">菜单排序值</th>
                                <th class="text-center" width="10%">是否有效</th>
                                <th class="text-center" width="20%">备注</th>
                                <th class="text-center" width="15%" colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="menu" items="${menuList}" varStatus="status">
                                <tr>
                                    <td class="text-center">${status.count}</td>
                                    <td class="text-center">${menu.name}</td>
                                    <td class="text-center">${menu.url}</td>
                                    <td class="text-center">${menu.orderView}</td>
                                    <td class="text-center">${menu.valid.name}</td>
                                    <td class="text-center">${menu.memo}</td>
                                    <td class="text-center"><a href="${ctx}/menu/input?id=${menu.id}">修改</a></td>
                                    <td class="text-center"><a href="#">权限管理</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>