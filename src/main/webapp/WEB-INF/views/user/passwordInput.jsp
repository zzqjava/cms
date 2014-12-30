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
                    <ul class="breadcrumb no-border no-radius b-b b-light pull-in">
                        <li><a href="${ctx}/dashboard"><i class="fa fa-home"></i> 主页</a></li>
                        <li><a href="#">系统管理</a></li>
                        <li><a href="#">用户管理</a></li>
                    </ul>
                    <div class="m-b-md">
                        <h3 class="m-b-none">密码修改</h3>
                    </div>
                    <section class="panel panel-default">
                        <div class="panel-body">
                            <form class="form-horizontal" action="${ctx}/user/password/update" method="post">
                                <input type="hidden" name="id" value="${userForm.id}">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">旧密码</label>
                                    <div class="col-sm-8">
                                        <input type="password" class="form-control" name="password" autofocus="" required="">
                                    </div>
                                </div>
                                <div class="lines line-dashed line-lg pull-in"></div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">新密码</label>
                                    <div class="col-sm-8">
                                        <input type="password" class="form-control" name="newPassword" required="">
                                    </div>
                                </div>
                                <div class="lines line-dashed line-lg pull-in"></div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">确认密码</label>
                                    <div class="col-sm-8">
                                        <input type="password" class="form-control" name="conPassword" required="">
                                    </div>
                                </div>
                                <div class="lines line-dashed line-lg pull-in"></div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-4">
                                        <button id="sub" type="button" class="btn btn-default">提交</button>
                                        <a class="btn btn-info" href="${ctx}${forwardUrl}">返回</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </section>
                </section>
            </section>
        </section>
    </body>
</html>