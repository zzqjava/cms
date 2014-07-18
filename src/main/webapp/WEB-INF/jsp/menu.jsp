<%@page contentType="text/html; charset=utf-8"%>
<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a href="javascript:collapse('collapseOne');">
                    系统管理
                </a>
            </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in">
            <div class="panel-body panel-success">
                <a href="${ctx}/user/list">用户管理</a>
            </div>
            <div class="panel-body">
                <a href="${ctx}/menu/list">菜单管理</a>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a href="javascript:collapse('collapseTwo');">
                    内容管理
                </a>
            </h4>
        </div>
        <div id="collapseTwo" class="panel-collapse collapse">
            <div class="panel-body">
                <a href="${ctx}/user/list">新闻管理</a>
            </div>
        </div>
    </div>
</div>