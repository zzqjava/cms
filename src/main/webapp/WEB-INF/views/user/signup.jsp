<%@page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN" class="bg-dark">
<head>
  <title>登录</title>
  <script type="text/javascript">
    $(function() {
      var captchaUrl = "${ctx}/kaptcha?";
      $('#captchaImg').prop('src', captchaUrl + (new Date()).getTime());
      $('#captchaImg').click(function () {
        $(this).prop('src', captchaUrl + (new Date()).getTime());
      });
    });
  </script>
</head>
<body>
  <section id="content" class="m-t-lg wrapper-md animated fadeInUp">    
    <div class="container aside-xxl">
      <a class="navbar-brand block" href="${ctx}/">CMS</a>
      <section class="panel panel-default bg-white m-t-lg">
        <header class="panel-heading text-center">
          <strong>用户登录</strong>
        </header>
        <form action="${ctx}/signup" method="post" class="panel-body wrapper-lg">
          <div class="form-group">
            <label class="control-label">账户</label>
            <input type="text" placeholder="账户名" name="username" class="form-control input-lg">
          </div>
          <div class="form-group">
            <label class="control-label">邮箱</label>
            <input type="text" placeholder="邮箱" name="email" class="form-control input-lg">
          </div>
          <div class="form-group">
            <label class="control-label">密码</label>
            <input type="password" id="password" name="password" placeholder="密码" class="form-control input-lg">
          </div>
          <div class="form-group">
            <label class="control-label">确认密码</label>
            <input type="password" id="conPassword" name="conPassword" placeholder="确认密码" class="form-control input-lg">
          </div>
          <div class="form-group">
            <label class="control-label">验证码</label>
            <input type="text" id="captcha" name="captcha" autofocus="" required="" placeholder="验证码" class="form-control input-lg">
            <img class="img-thumbnail" id="captchaImg" title="看不清？点击图片刷新"/>
          </div>
          <button type="submit" class="btn btn-primary">注册</button>
          <a href="${ctx}/signin" class="btn btn-primary">登录</a>
          <div class="lines line-dashed"></div>
          <label class="highlight_red">${errorMessage}</label>
        </form>
      </section>
    </div>
  </section>
  <!-- footer -->
  <footer id="footer">
    <div class="text-center padder">
      <p>
        <small>CMS &copy; 2007-2014 qatang.com</small>
      </p>
    </div>
  </footer>
</body>
</html>