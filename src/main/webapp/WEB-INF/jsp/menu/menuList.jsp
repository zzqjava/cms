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
    <script type="text/javascript">
        function input(level, parentID) {
            $("#theForm").attr("action", "${ctx}/menu/input");
            $("#level").val(level);
            $("#parentID").val(parentID);
            $("#theForm").submit();
        }

    </script>
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
                <form action="${ctx}/menu/input" method="post" id="theForm" >
                    <input type="hidden" id="level" name="level" value=""/>
                    <input type="hidden" id="parentID" name="parentID" value=""/>
                    <a href="#" class="btn btn-primary"  onclick="input('1','0')">添加菜单</a>
                </form>
            </div>
            <div style="margin-top:10px;">
                <div class="text-center">
                    <c:if test="${menuList != null && menuList.size() > 0}">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th class="text-center" width="15%">菜单名</th>
                                <th class="text-center" width="20%">菜单链接</th>
                                <th class="text-center" width="10%">菜单级别</th>
                                <th class="text-center" width="10%">菜单权限</th>
                                <th class="text-center" width="10%">菜单排序值</th>
                                <th class="text-center" width="10%">是否有效</th>
                                <th class="text-center" width="10%">备注</th>
                                <th class="text-center" width="15%" colspan="3">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="menu" items="${menuList}">
                                <c:if test="${menu.level == 1}">
                                    <tr>
                                        <td class="text-center"><a href="view/${menu.id}">${menu.name}</a></td>
                                        <td class="text-center">${menu.url}</td>
                                        <td class="text-center">${menu.level}</td>
                                        <td class="text-center">${menu.permission}</td>
                                        <td class="text-center">${menu.orderLevel}</td>
                                        <td class="text-center">${menu.valid.name}</td>
                                        <td class="text-center">${menu.memo}</td>
                                        <td class="text-center"><a href="input/${menu.id}">修改</a></td>
                                        <td class="text-center">
                                            <c:choose>
                                                <c:when test="${menu.valid.value == 1}">
                                                    <a href="disable/${menu.id}">无效</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="enable/${menu.id}">有效</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="text-center"><a href="#" onclick="input('2','${menu.id}')">添加子菜单</a></td>
                                    </tr>
                                </c:if>
                                    <c:forEach var="second" items="${menu.children}">
                                        <c:if test="${second.level == 2}">
                                            <tr>
                                                <td class="text-center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="view/${second.id}">${second.name}</a></td>
                                                <td class="text-center">${second.url}</td>
                                                <td class="text-center">${second.level}</td>
                                                <td class="text-center">${second.permission}</td>
                                                <td class="text-center">${second.orderLevel}</td>
                                                <td class="text-center">${second.valid.name}</td>
                                                <td class="text-center">${second.memo}</td>
                                                <td class="text-center"><a href="input/${second.id}">修改</a></td>
                                                <td class="text-center">
                                                    <c:choose>
                                                        <c:when test="${second.valid.value == 1}">
                                                            <a href="disable/${second.id}">无效</a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a href="enable/${second.id}">有效</a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td class="text-center"><a href="#" onclick="input('3','${second.id}')">添加子菜单</a></td>
                                            </tr>
                                        </c:if>
                                        <c:forEach var="third" items="${second.children}">
                                            <tr>
                                                <td class="text-center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="view/${third.id}">${third.name}</a></td>
                                                <td class="text-center">${third.url}</td>
                                                <td class="text-center">${third.level}</td>
                                                <td class="text-center">${third.permission}</td>
                                                <td class="text-center">${third.orderLevel}</td>
                                                <td class="text-center">${third.valid.name}</td>
                                                <td class="text-center">${third.memo}</td>
                                                <td class="text-center"><a href="input/${third.id}">修改</a></td>
                                                <td class="text-center">
                                                    <c:choose>
                                                        <c:when test="${third.valid.value == 1}">
                                                            <a href="disable/${third.id}">无效</a>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a href="enable/${third.id}">有效</a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td></td>
                                            </tr>
                                        </c:forEach>
                                    </c:forEach>
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