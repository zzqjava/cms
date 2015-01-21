<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn" class="bg-dark">
<head>
    <title>欢迎</title>
</head>
<body>
<section id="content">
    <div class="container">
        <div class="login-box text-center" >
            <div class="login-single-panel-header">
                <c:choose>
                    <c:when test="${successMessage == null}">
                        <h3>操作成功</h3>
                    </c:when>
                    <c:otherwise>
                        <h3>${successMessage}</h3>
                    </c:otherwise>
                </c:choose>
            <a class="btn btn-primary btn-sm" href="${ctx}${forwardUrl}">返回列表</a>
            </div>
        </div>
    </div>
    <div class="text-center bottom">
        Copyright &copy; 2007-2014 qatang.com All rights reserved.
    </div>
</section>
</body>
</html>