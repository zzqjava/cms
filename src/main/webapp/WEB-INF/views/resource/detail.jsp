<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
    <title>查看资源</title>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">后台管理系统</a>
        </div>
    </div>
</div>
<div class="container" style="margin-top:70px;">
    <div class="text-center">
        <table class="table table-bordered">
            <tr>
                <td>资源名</td>
                <td>${resource.name}</td>
            </tr>
            <tr>
                <td>资源链接</td>
                <td>${resource.url}</td>
            </tr>
            <tr>
                <td>资源权限</td>
                <td>${resource.identifier}</td>
            </tr>
            <tr>
                <td>资源类别</td>
                <td>${resource.type.name}</td>
            </tr>
            <tr>
                <td>资源排序值</td>
                <td>${resource.priority}</td>
            </tr>
            <tr>
                <td>是否有效</td>
                <td>${resource.valid.name}</td>
            </tr>
            <tr>
                <td>创建时间</td>
                <td>${resource.createdTime}</td>
            </tr>
            <tr>
                <td>最后一次更新时间</td>
                <td>${resource.updatedTime}</td>
            </tr>
            <tr>
                <td>path</td>
                <td>${resource.path}</td>
            </tr>
            <tr>
                <td>备注</td>
                <td>${resource.memo}</td>
            </tr>
        </table>
        <a class="btn btn-sm btn-default" href="${ctx}${forwardUrl}">返回列表</a>
    </div>
</div>
</body>
</html>