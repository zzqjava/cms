<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn" class="bg-dark">
<head>
    <title>欢迎</title>
</head>
<body>
<section id="content">
    <section class="vbox">
        <section class="scrollable padder">
            <section class="panel panel-default">
                <div class="panel-body">
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
                <div class="text-center bottom">
                    Copyright &copy; 2007-2014 qatang.com All rights reserved.
                </div>
            </section>
        </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>

</body>
</html>