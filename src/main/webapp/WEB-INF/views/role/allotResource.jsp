<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN" class="bg-dark">
<head>
    <title>分配资源</title>
    <link rel="stylesheet" href="${ctx}/static/js/zTreeV3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx}/static/js/zTreeV3/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/zTreeV3/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript">
        var resourceIds;
        var treeObj;
        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        var zNodes = ${ztrees};
        $(function(){
            $.fn.zTree.init($("#resourceTree"), setting, zNodes);
            treeObj=$.fn.zTree.getZTreeObj("resourceTree");

            $("#theform").submit(function(){
                resourceIds = "";
                var nodes=treeObj.getCheckedNodes(true),
                        v="";
                for(var i=0;i<nodes.length;i++){
                    v+=nodes[i].name + ",";
                    resourceIds +=  nodes[i].id;
                    if(i != nodes.length - 1){
                        resourceIds +=  ",";
                    }
                }
                $('#resourceIds').val(resourceIds);
            });
        });
    </script>
</head>
<body>
<section id="content">
    <section class="vbox">
        <section class="scrollable padder">
            <ul class="breadcrumb no-border no-radius b-b b-light pull-in">
                <li><a href="${ctx}/dashboard"><i class="fa fa-home"></i> 主页</a></li>
                <li><a href="#">系统管理</a></li>
                <li class="${ctx}/user/list"><a href="#">角色管理</a></li>
                <li class="active">分配资源</li>
            </ul>
            <div class="m-b-md">
                <h3 class="m-b-none">
                </h3>
            </div>
            <section class="panel panel-default">
                <header class="panel-heading font-bold">
                    分配资源
                </header>
                <div class="panel-body">
                    <form:form class="form-horizontal" id="theform" action="${ctx}/allotResource/save" method="post">
                        <input type="hidden" class="form-control" id="roleId" name="roleId" value="${roleId}">
                        <input type="hidden" id="resourceIds" name="resourceIds" value="${resourceIds}"/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">资源：</label>
                            <div class="col-sm-10">
                                <ul id="resourceTree" class="ztree"></ul>
                            </div>
                        </div>
                        <div class="lines line-dashed line-lg pull-in"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button type="submit" class="btn btn-default" id="sub">提交</button>
                                <a class="btn btn-info" href="${ctx}${forwardUrl}">返回</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </section>
        </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>
</body>
</html>