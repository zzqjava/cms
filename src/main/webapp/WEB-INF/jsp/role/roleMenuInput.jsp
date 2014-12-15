<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>角色资源</title>
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="keywords" content="" />
    <meta http-equiv="description" content="" />
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/css/main.css">
    <script src="${ctx}/js/jquery-1.11.1.min.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>
    <script src="${ctx}/js/bootstrap-alert.js"></script>
    <script src="${ctx}/js/bootstrap-transition.js"></script>
    <script src="${ctx}/js/bootstrap-button.js"></script>
    <script type="text/javascript">
        $(function(){

        })

        //定时关闭提示信息
        var successMessage = '${successMessage}';
        if (successMessage != null && successMessage != '') {
            closeSuccess();
        }
        function closeSuccess() {
            setTimeout("closeSuccessTip()",2000);
        }
        function closeSuccessTip(){
            $('#tipSuccess').click();
        }
        var errorMessage = '${errorMessage}';
        if (errorMessage != null && errorMessage != '') {
            closeError();
        }
        function closeError() {
            setTimeout("closeErrorTip()",2000);
        }
        function closeErrorTip(){
            $('#tipError').click();
        }

        function submitForm () {
            var value = "";
            $("input[name='menu']:checked").each(function () {
                if (value == "") {
                    value = this.value;
                } else {
                    value =value + "," + this.value;
                }
            });
            $("#menuIds").val(value);
            $("#theForm").submit();
        }
    </script>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">后台管理系统</a>
        </div>
    </div>
</div>
<div class="container">
    <div class="login-box text-center" >
        <div class="login-single-panel-header">
            <h3>选择菜单</h3>
        </div>
        <form id="theForm" class="form-signup-heading" action="${ctx}/role/createRoleMenu" method="post">
            <input type="hidden" name="roleId" value="${roleId}" id="roleId"/>
            <input type="hidden" name="menuIds" value="${menuIds}" id="menuIds"/>
            <c:if test="${successMessage != null}" >
                <div class="alert alert-success fade in">
                    <a class="close" data-dismiss="alert" href="#" id="tipSuccess">×</a>
                        ${successMessage}
                </div>
            </c:if>
            <c:if test="${errorMessage != null}" >
                <div class="alert alert-danger fade in">
                    <a class="close" data-dismiss="alert" href="#" id="tipError">×</a>
                        ${errorMessage}
                </div>
            </c:if>
            <br/> <br/>
            <div class="input-group">
                <c:forEach items="${menuList}" var="menus">
                    <input type="checkbox" id="menu_${menus.id}" name="menu" value="${menus.id}"/>${menus.name}&nbsp;&nbsp;&nbsp;&nbsp;
                </c:forEach>
            </div>
            <br/>
            <a href="#"class="btn btn-primary btn-lg" id="sub" name="sub" onclick="submitForm();">确定</a>
        </form
    </div>
</div>
<div class="text-center bottom">
    Copyright &copy; 2007-2014 qatang.com All rights reserved.
</div>
</body>
</html>