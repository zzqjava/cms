<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn" class="bg-dark">
<head>
    <title>用户注册</title>
</head>
<body>
<section id="content" class="m-t-lg wrapper-md animated fadeInUp">
  <div class="container aside-xxl">
    <a class="navbar-brand block" href="${ctx}/">CMS</a>
    <section class="panel panel-default bg-white m-t-lg">
      <header class="panel-heading text-center">
        <strong>注册成功</strong>
      </header>
      <div class="form-group" style="text-align:center;">
        <label class="control-label">注册成功！已向<span style="color: red">${userForm.username}</span>发送一份确认邮件，请登录邮箱，激活验证码完成注册！</label>
      </div>
      <div class="form-group" style="text-align:center;">
        <a href="${ctx}/signin" class="btn btn-primary">登录</a>
      </div>
    </section>
  </div>
</section>
<footer id="footer">
  <div class="text-center padder">
    <p>
      <small>CMS &copy; 2007-2014 qatang.com</small>
    </p>
  </div>
</footer>
</body>
</html>