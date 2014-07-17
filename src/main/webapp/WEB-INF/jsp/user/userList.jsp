<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>用户列表</title>
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="keywords" content="" />
        <meta http-equiv="description" content="" />
        <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${ctx}/css/main.css">
        <script src="${ctx}/js/jquery-1.11.1.min.js"></script>
        <script src="${ctx}/js/bootstrap.min.js"></script>
        <script type="application/javascript">
            $(function(){
            });

            function collapse (subId) {
                if ($('#' + subId).hasClass('in')) {
                    $('#' + subId).removeClass('in')
                } else {
                    $('#' + subId).addClass('in')
                }
            }
        </script>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/navi.jsp"/>
        <div class="container" style="margin-top:60px;">
            <a href="${ctx}/user/input" class="btn btn-primary btn-lg" role="button">创建用户</a>
        </div>
        <div class="container">
            <form class="form-horizontal" action="${ctx}/user/list" method="post">
                <fieldset>
                    <div id="legend" class="">
                        <legend class="">用户查询</legend>
                    </div>
                    <div class="control-group">
                        <label class="control-label">用户名</label>
                        <div class="controls">
                            <input type="text" name="username" class="input-xlarge" value="${userForm.username}">
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">用户姓名</label>
                        <div class="controls">
                            <input type="text" name="name" class="input-xlarge" value="${userForm.name}">
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">用户邮箱</label>
                        <div class="controls">
                            <input type="text" name="email" class="input-xlarge" value="${userForm.email}">
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">手机号</label>
                        <div class="controls">
                            <input type="text" name="mobile" class="input-xlarge" value="${userForm.mobile}">
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">性别</label>
                        <div class="controls">
                            <select name="genderValue">
                                <c:choose>
                                    <c:when test="${userForm.genderValue == 1}">
                                        <option value="0">全部</option>
                                        <option value="1" selected>男</option>
                                        <option value="2">女</option>
                                    </c:when>
                                    <c:when test="${userForm.genderValue == 2}">
                                        <option value="0">全部</option>
                                        <option value="1">男</option>
                                        <option value="2" selected>女</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0" selected>全部</option>
                                        <option value="1">男</option>
                                        <option value="2">女</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">是否有效</label>
                        <div class="controls">
                            <select name="validValue">
                                <c:choose>
                                    <c:when test="${userForm.validValue == 1}">
                                        <option value="0" selected>全部</option>
                                        <option value="1" selected>是</option>
                                        <option value="2">否</option>
                                    </c:when>
                                    <c:when test="${userForm.validValue == 2}">
                                        <option value="0" selected>全部</option>
                                        <option value="1">是</option>
                                        <option value="2" selected>否</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0" selected>全部</option>
                                        <option value="1">是</option>
                                        <option value="2">否</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                            <p class="help-block"></p>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">将</label>
                        <div class="controls">
                            <select name="orderType">
                                <c:choose>
                                    <c:when test="${userForm.genderValue == 'createdTime'}">
                                        <option value="id">id</option>
                                        <option value="createdTime" selected>创建时间</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="id" selected>id</option>
                                        <option value="createdTime">创建时间</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                            <p class="help-block"></p>
                        </div>
                        <label class="control-label">字段，按</label>
                        <div class="controls">
                            <select name="sortType">
                                <c:choose>
                                    <c:when test="${userForm.sortType == 'desc'}">
                                        <option value="asc">正序</option>
                                        <option value="desc" selected>倒序</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="asc" selected>正序</option>
                                        <option value="desc">倒序</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                            <p class="help-block"></p>
                        </div>
                        <label class="control-label">排列</label>
                    </div>
                    <div class="control-group">
                        <label class="control-label"></label>
                        <div class="controls">
                            <button class="btn btn-default">查询</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
        <div class="container" style="margin-top:10px;">
            <c:if test="${userList != null}">
                <div class="text-center">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>序号</th>
                                <th>用户名</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>手机号</th>
                                <th>邮箱</th>
                                <th>是否有效</th>
                                <th colspan="4">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${userList}" var="user" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td><a href="${ctx}/user/view/${user.id}">${user.username}</a></td>
                                    <td>${user.name}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.gender.value == 1}">
                                                男
                                            </c:when>
                                            <c:otherwise>
                                                女
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${user.mobile}</td>
                                    <td>${user.email}</td>
                                    <td>${user.valid.name}</td>
                                    <td><a href="${ctx}/user/input/${user.id}">修改</a></td>
                                    <td><a href="${ctx}/user/passwordInput/${user.id}">重置密码</a></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.valid.value == 1}">
                                                <a href="${ctx}/user/forbidden/${user.id}">禁用</a>
                                            </c:when>
                                            <c:otherwise>
                                                禁用
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><a href="${ctx}/user/del/${user.id}" onclick="return confirm('确定要删除么？');">删除</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
>>>>>>> 262f52002c8088978b6918346c84d3ec47319ceb
    </body>
</html>