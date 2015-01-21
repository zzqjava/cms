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
        <form action="${ctx}/signin" method="post" class="panel-body wrapper-lg">
          <div class="form-group">
            <label class="control-label">账户</label>
            <input type="text" placeholder="账户名" name="username" class="form-control input-lg">
          </div>
          <div class="form-group">
            <label class="control-label">密码</label>
            <input type="password" id="inputPassword" name="password" placeholder="密码" class="form-control input-lg">
          </div>
          <div class="form-group">
            <label class="control-label">验证码</label>
            <input type="text" id="captcha" name="captcha" autofocus="" required="" placeholder="验证码" class="form-control input-lg">
            <img class="img-thumbnail" id="captchaImg" title="看不清？点击图片刷新"/>
          </div>
          <div class="form-group">
            <input type="checkbox" value="true" name="rememberMe">记住我
          </div>
          <a href="${ctx}/signin" class="pull-right m-t-xs"><small>忘记密码？</small></a>
          <button type="submit" class="btn btn-primary">登录</button>
          <a href="${ctx}/signup" class="btn btn-primary">注册</a>
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