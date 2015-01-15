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
                    <c:choose>
                        <c:when test="${userForm.id == null}">
                            角色添加
                        </c:when>
                        <c:otherwise>
                            角色修改
                        </c:otherwise>
                    </c:choose>
                </h3>
            </div>
            <section class="panel panel-default">
                <div class="panel-body">
                    <form:form class="form-horizontal" action="${ctx}/userRole/allot" method="post">
                        <div class="form-group">
                            <input id="userId" type="hidden" name="id" value="${userId}" />
                            <label class="col-sm-2 control-label">角色</label>
                            <div class="col-sm-10">
                                <c:forEach items="${rolesList}" var="role" varStatus="s">
                                    <input type="checkbox"
                                    <c:forEach var="exist" items="${existRoles}">
                                            ${exist.id}
                                    <c:if test="${role.id == exist.roleId}"> checked="checked" </c:if>
                                    </c:forEach>
                                    name="roleIdList[${s.index}]" value="${role.id}" autofocus="" data-required="true">
                                    ${role.name}
                                </c:forEach>
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