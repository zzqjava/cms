<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欢迎</title>
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="keywords" content="" />
    <meta http-equiv="description" content="" />
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <style type="text/css">
        body{background:#F3F9FD}
        li,ul,p{list-style:none; padding:0px; margin:0px;}
        img{ border:none;}
        .wapent{width:700px;margin:0 auto; -moz-box-shadow: 1px 1px 10px #505155;-webkit-box-shadow: 1px 1px 10px #505155;box-shadow: 1px 1px 10px #505155;-moz-border-radius:5px;-webkit-border-radius: 5px;border-radius:5px; background:#fff; }
        .wapent_reg{margin-top:2%;}
        .wapent_login{margin-top:5%;}
        .wapent_findPW{margin-top:10%;}
        .wapent1{border-bottom:1px solid #F4F4F4; padding:15px 20px 20px 20px; margin-bottom:15px; height:60px;}
        .wapent2{float:left; margin-right:20px;}
        .wapent3{border-right:1px dotted #e1e1e1; width:1px; overflow:hidden; float:left; height:55px;}
        .wapent4{color:#9e9f9f; font-family:"微软雅黑"; font-size:17px; float:left;padding:20px 0 0 20px;text-shadow: 0px 0px 1px #9F9F9F;}
        .wapent5{color:#9e9f9f; font-family: "微软雅黑"; font-size:12px; float:right;padding:0px 20px 0 20px;}
        .container{width:960px;margin:0 auto}
        .container .content{padding-right:335px;margin-top:20px}
        .tit{overflow:hidden;padding-bottom:20px;zoom:1;margin:0 0 0 10px}
        .tit h1{float:left;width:200px;height: auto;font-size: 20px;  font-family:"微软雅黑"; font-weight: bold;overflow:hidden;margin:0 0 0 30px; color:#9F9F9F;text-shadow: 0px 0px 1px #999;}
        .user_tips{float:right}
        #userName,#userNameField,#email,#perusername,#registerusername,#entregemail{ _position:relative;*+position:relative;}
        .reg_form{font-size:14px;padding-left:130px;_padding-left:100px;*+padding-left:130px;zoom:1;}
        .reg_form li{overflow:hidden;padding-bottom:16px;zoom:1; height:38px;}
        .reg_form label{float:left;width:85px;height:38px;line-height:38px;text-align:right;color:#555}
        .reg_form input{float:left;width:216px;height:35px;line-height:35px;border:1px solid #B7B7B7;font-size:18px;font-weight:100;color:#324F65;overflow:hidden;zoom:1;padding:2px 2px 2px 8px;outline:none;}
        .reg_form .radio_sex{float:left;margin:8px 20px 0 0}
        .reg_form .radio_sex label{width:auto;float:none;height:auto;line-height:normal;cursor:pointer}
        .reg_form .radio{float:none;width:auto;height:auto;cursor:pointer;border:0;vertical-align:middle;margin-right:5px;padding:0}
        .reg_form .feed_in,.reg_form .field{border:1px solid #d5dce0;-moz-border-radius:5px;-webkit-border-radius:5px}
        .reg_form .feed_in_curr{border:2px solid #c4c4c4;width:214px;height:33px;line-height:31px;-moz-border-radius:5px;-webkit-border-radius:5px}
        .reg_form .feed_in_pw{-moz-border-radius:5px;-webkit-border-radius:5px;width:116px;height:30px;line-height:30px;border:1px solid #d5dce0;font-size:16px;letter-spacing:4px; font-weight:bold;overflow:hidden;zoom:1;padding:2px 2px 2px 8px}
        .reg_form .feed_in_yzm{width:20px;min-width:20px;height:20px;line-height:20px;_float:left;_line-height:20px;*+line-height:20px;_float:left;}
        .reg_form .ValidationErrors{width:154px;float:left;line-height:40px;_line-height:31px;*+line-height:31px;color:#EC6514;font-family:"微软雅黑";font-size:12px;_padding:0 0 0 20px;padding:20px 0 0 20px}

        .reg_form .feed_in_span,.reg_form .feed_in_span_yzm{display:none;_display:block; _height:35px;_line-height:35px;_line-height:31px;_width:154px;_color:#9f9f9f;_font-family:"微软雅黑";_font-size:12px;_padding:4px 0 0 20px}
        .reg_form li:hover .feed_in_span{display: inline; float:left; height:35px; line-height:35px;_line-height:31px;*+line-height:31px; display:block;width:154px;color:#9f9f9f;font-family:"微软雅黑";font-size:12px;padding:4px 0 0 20px}
        .reg_form .feed_in_span a{color:#006EA3;font-size:12px;text-decoration:none}
        .reg_form li:hover .feed_in_span_yzm{display: inline;height:35px;_float:left;line-height:35px;_line-height:31px;*+line-height:31px; width:154px;color:#9f9f9f;font-family:"微软雅黑";font-size:12px;padding:4px 0 0 20px}
        .reg_form .sms_input_cur{width:98px}
        .reg_form .resend{margin-top:9px;margin-left:10px;display:inline;zoom:1;font-size:12px}
        .reg_form .resend em{color:red;display:inline-block;float:left;margin:5px 0 0 10px}
        .submit_area{margin-left:210px; padding-bottom:25px;*+padding-bottom:15px;_padding-bottom:15px;}
        .btn_login{width:143px;height:51px;border:0 none; font-size:18px;color:#fff;font-weight:bold;  cursor:pointer; float:left;}
        .btn_img_login{background:url(<c:url value="/images/login_Button.gif"/>) no-repeat;}
        .reg_footer{min-height:40px;_height:50px; _margin-top:20px;font-size:14px;}
        .dlg_footer{border-top:1px solid #8db1bf;border-radius:0 0 5px 5px;-moz-border-radius:0 0 5px 5px;-webkit-border-radius:5px;-webkit-border-top-left-radius:0;-webkit-border-top-right-radius:0;bottom:-1px;padding:14px 0}
        .dlg_footer li{color:#082f42;font-size:12px;float:left;list-style:none;color:#5c7176;text-align:center;margin:0 auto;width:100%}
        .reg_error{margin-top:5px;margin-bottom:15px;text-align:center;color:red;font-size:12px;}
        label.error{color:#f00;display: none;text-align:left;width:20px;}
    </style>

</head>
<body>
<div class="wapent wapent_login">
    <div class="wapent1">
        <div class="wapent3"></div>
        <div class="wapent4">后台管理系统</div>
        <div style="clear:both"></div>
    </div>

    <div class="content">
        <div class="tit"><h1>用户登录</h1>
            <div class="wapent5 agree1"></div>
            <div style="clear:both"></div>
        </div>
        <div id="perloginpanel">
            <form id="theform" action="${ctx}/login" method="post">
                <ul class="reg_form">
                    <li>
                        <label for="username">用户名：</label>
                        <input type="text" id="username" name="username" maxlength="16" value="${username}"/>
                    </li>
                    <li>
                        <label for="password">密码：</label>
                        <input type="password" id="password" name="password" maxlength="16" value="${password}"/>
                    </li>
                    <li>
                        <label for="captcha">验证码：</label>
                        <input type="text" class="feed_in_pw" id="captcha" name="captcha" maxlength="5"/>&nbsp;&nbsp;
                    </li>
                </ul>
                <div class="submit_area">
                    <input class="btn_login btn_img_login" id="sub" name="sub" value="" type="submit" />
                    <div style="clear:both;"></div>
                </div>
            </form>
        </div>
    </div>
    <div class="reg_error">
        ${errorMessage}
    </div>
    <div class="reg_footer">
        <ul class="dlg_footer">
            <li><span>Copyright &copy; 2007-2014 qatang.com All rights reserved.</span></li>
        </ul>
    </div>
</div>
</body>
</html>