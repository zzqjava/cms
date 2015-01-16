<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html lang="zh-cn" class="app">
<head>
    <title>用户主页</title>
</head>
<body>
<section id="content">
    <section class="vbox">
        <header class="header bg-white b-b b-light">
            <p>个人中心</p>
        </header>
        <section class="scrollable">
            <section class="hbox stretch">
                <aside class="aside-lg bg-light lter b-r">
                    <section class="vbox">
                        <section class="scrollable">
                            <div class="wrapper">
                                <div class="clearfix m-b">
                                    <a href="#" class="pull-left thumb m-r">
                                        <img src="static/images/avatar.jpg" class="img-circle">
                                    </a>
                                    <div class="clear">
                                        <div class="h3 m-t-xs m-b-xs">${user.name}</div>
                                        <small class="text-muted"><i class="fa fa-map-marker"></i> beijing, CN</small>
                                    </div>
                                </div>
                                <div class="panel wrapper panel-success">
                                    <div class="row">
                                        <div class="col-xs-4">
                                            <a href="#">
                                                <span class="m-b-xs h4 block">245</span>
                                                <small class="text-muted">Followers</small>
                                            </a>
                                        </div>
                                        <div class="col-xs-4">
                                            <a href="#">
                                                <span class="m-b-xs h4 block">55</span>
                                                <small class="text-muted">Following</small>
                                            </a>
                                        </div>
                                        <div class="col-xs-4">
                                            <a href="#">
                                                <span class="m-b-xs h4 block">2,035</span>
                                                <small class="text-muted">Tweets</small>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </section>
                </aside>
                <aside class="bg-white">
                    <section class="vbox">
                        <header class="header bg-light bg-gradient">
                            <ul class="nav nav-tabs nav-white">
                                <li class="active"><a href="#activity" data-toggle="tab">Activity</a></li>
                                <li class=""><a href="#events" data-toggle="tab">Events</a></li>
                                <li class=""><a href="#interaction" data-toggle="tab">Interaction</a></li>
                            </ul>
                        </header>
                        <section class="scrollable">
                            <div class="tab-content">
                                <div class="tab-pane active" id="activity">
                                    <ul class="list-group no-radius m-b-none m-t-n-xxs list-group-lg no-border">
                                        <li class="list-group-item">
                                            <a href="#" class="thumb-sm pull-left m-r-sm">
                                                <img src="static/images/avatar_default.jpg" class="img-circle">
                                            </a>
                                            <a href="#" class="clear">
                                                <small class="pull-right">3 minuts ago</small>
                                                <strong class="block">Drew Wllon</strong>
                                                <small>Wellcome and play this web application template ... </small>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <a href="#" class="thumb-sm pull-left m-r-sm">
                                                <img src="static/images/avatar.jpg" class="img-circle">
                                            </a>
                                            <a href="#" class="clear">
                                                <small class="pull-right">1 hour ago</small>
                                                <strong class="block">Jonathan George</strong>
                                                <small>Morbi nec nunc condimentum...</small>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <a href="#" class="thumb-sm pull-left m-r-sm">
                                                <img src="static/images/avatar.jpg" class="img-circle">
                                            </a>
                                            <a href="#" class="clear">
                                                <small class="pull-right">2 hours ago</small>
                                                <strong class="block">Josh Long</strong>
                                                <small>Vestibulum ullamcorper sodales nisi nec...</small>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <a href="#" class="thumb-sm pull-left m-r-sm">
                                                <img src="static/images/avatar_default.jpg" class="img-circle">
                                            </a>
                                            <a href="#" class="clear">
                                                <small class="pull-right">1 day ago</small>
                                                <strong class="block">Jack Dorsty</strong>
                                                <small>Morbi nec nunc condimentum...</small>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <a href="#" class="thumb-sm pull-left m-r-sm">
                                                <img src="static/images/avatar.jpg" class="img-circle">
                                            </a>
                                            <a href="#" class="clear">
                                                <small class="pull-right">3 days ago</small>
                                                <strong class="block">Morgen Kntooh</strong>
                                                <small>Mobile first web app for startup...</small>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <a href="#" class="thumb-sm pull-left m-r-sm">
                                                <img src="static/images/avatar_default.jpg" class="img-circle">
                                            </a>
                                            <a href="#" class="clear">
                                                <small class="pull-right">Jun 21</small>
                                                <strong class="block">Yoha Omish</strong>
                                                <small>Morbi nec nunc condimentum...</small>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <a href="#" class="thumb-sm pull-left m-r-sm">
                                                <img src="static/images/avatar.jpg" class="img-circle">
                                            </a>
                                            <a href="#" class="clear">
                                                <small class="pull-right">May 10</small>
                                                <strong class="block">Gole Lido</strong>
                                                <small>Vestibulum ullamcorper sodales nisi nec...</small>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <a href="#" class="thumb-sm pull-left m-r-sm">
                                                <img src="static/images/avatar_default.jpg" class="img-circle">
                                            </a>
                                            <a href="#" class="clear">
                                                <small class="pull-right">Jan 2</small>
                                                <strong class="block">Jonthan Snow</strong>
                                                <small>Morbi nec nunc condimentum...</small>
                                            </a>
                                        </li>
                                        <li class="list-group-item" href="#email-content" data-toggle="class:show">
                                            <a href="#" class="thumb-sm pull-left m-r-sm">
                                                <img src="static/images/avatar_default.jpg" class="img-circle">
                                            </a>
                                            <a href="#" class="clear">
                                                <small class="pull-right">3 minuts ago</small>
                                                <strong class="block">Drew Wllon</strong>
                                                <small>Vestibulum ullamcorper sodales nisi nec sodales nisi nec sodales nisi nec...</small>
                                            </a>
                                        </li>
                                        <li class="list-group-item">
                                            <a href="#" class="thumb-sm pull-left m-r-sm">
                                                <img src="static/images/avatar.jpg" class="img-circle">
                                            </a>
                                            <a href="#" class="clear">
                                                <small class="pull-right">1 hour ago</small>
                                                <strong class="block">Jonathan George</strong>
                                                <small>Morbi nec nunc condimentum...</small>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="tab-pane" id="events">
                                    <div class="text-center wrapper">
                                        <i class="fa fa-spinner fa fa-spin fa fa-large"></i>
                                    </div>
                                </div>
                                <div class="tab-pane" id="interaction">
                                    <div class="text-center wrapper">
                                        <i class="fa fa-spinner fa fa-spin fa fa-large"></i>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </section>
                </aside>
                <aside class="col-lg-3 b-l">
                    <section class="vbox">
                        <section class="scrollable">
                            <div class="wrapper">
                                <section class="panel panel-default">
                                    <form>
                                        <textarea class="form-control no-border" rows="3" placeholder="What are you doing..."></textarea>
                                    </form>
                                    <footer class="panel-footer bg-light lter">
                                        <button class="btn btn-info pull-right btn-sm">POST</button>
                                        <ul class="nav nav-pills nav-sm">
                                            <li><a href="#"><i class="fa fa-camera text-muted"></i></a></li>
                                            <li><a href="#"><i class="fa fa-video-camera text-muted"></i></a></li>
                                        </ul>
                                    </footer>
                                </section>
                                <section class="panel panel-default">
                                    <h4 class="font-thin padder">Latest Tweets</h4>
                                    <ul class="list-group">
                                        <li class="list-group-item">
                                            <p>Wellcome <a href="#" class="text-info">@Drew Wllon</a> and play this web application template, have fun1 </p>
                                            <small class="block text-muted"><i class="fa fa-clock-o"></i> 2 minuts ago</small>
                                        </li>
                                        <li class="list-group-item">
                                            <p>Morbi nec <a href="#" class="text-info">@Jonathan George</a> nunc condimentum ipsum dolor sit amet, consectetur</p>
                                            <small class="block text-muted"><i class="fa fa-clock-o"></i> 1 hour ago</small>
                                        </li>
                                        <li class="list-group-item">
                                            <p><a href="#" class="text-info">@Josh Long</a> Vestibulum ullamcorper sodales nisi nec adipiscing elit. </p>
                                            <small class="block text-muted"><i class="fa fa-clock-o"></i> 2 hours ago</small>
                                        </li>
                                    </ul>
                                </section>
                                <section class="panel clearfix bg-info lter">
                                    <div class="panel-body">
                                        <a href="#" class="thumb pull-left m-r">
                                            <img src="static/images/avatar.jpg" class="img-circle">
                                        </a>
                                        <div class="clear">
                                            <a href="#" class="text-info">@Mike Mcalidek <i class="fa fa-twitter"></i></a>
                                            <small class="block text-muted">2,415 followers / 225 tweets</small>
                                            <a href="#" class="btn btn-xs btn-success m-t-xs">Follow</a>
                                        </div>
                                    </div>
                                </section>
                            </div>
                        </section>
                    </section>
                </aside>
            </section>
        </section>
    </section>
    <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen" data-target="#nav"></a>
</section>
<aside class="bg-light lter b-l aside-md hide" id="notes">
    <div class="wrapper">Notification</div>
</aside>
</body>
</html>