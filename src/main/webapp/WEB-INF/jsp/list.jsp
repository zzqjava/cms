<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
	<title>列表</title>
	<meta name="description" content="Creating a table with Twitter Bootstrap. Learn how to use Twitter Bootstrap toolkit to create Tables with examples.">
	<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/css/main.css">
	<script src="${ctx}/js/jquery-1.11.1.min.js"></script>
	<script src="${ctx}/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">后台管理系统</a>
			</div>
		</div>
	</div>
	<div class="margin-top-50">
		<div class="menu-left">
			<!--Sidebar content-->
			<div class="sidebar-menu">
				<a href="#userMeun" class="nav-header menu-first collapsed" data-toggle="collapse"><i class="icon-user-md icon-large"></i> 用户管理</a>
				<ul id="userMeun" class="nav nav-list collapse menu-second">
					<li><a href="#"> 增加用户</a></li>
					<li><a href="#"> 修改用户</a></li>
					<li><a href="#"> 删除用户</a></li>
					<li><a href="#"> 用户列表</a></li>

				</ul>
				<a href="#articleMenu" class="nav-header menu-first collapsed" data-toggle="collapse"><i class="icon-book icon-large"></i> 文章管理</a>
				<ul id="articleMenu" class="nav nav-list collapse menu-second">
					<li><a href="#"><i class="icon-pencil"></i> 添加文章</a></li>
					<li><a href="#"><i class="icon-list-alt"></i> 文章列表</a></li>
				</ul>
			</div>
		</div>
		<div class="menu-right">
		<table class="table">
			<thead>
			<tr>
				<th>编号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>001</td>
				<td>张三</td>
				<td>男</td>
				<td>20</td>
			</tr>
			<tr>
				<td>002</td>
				<td>李四</td>
				<td>女</td>
				<td>30</td>
			</tr>
			<tr>
				<td>003</td>
				<td>王五</td>
				<td>男</td>
				<td>40</td>
			</tr>
			</tbody>
		</table>
		</div>
	</div>
</body>
</html>