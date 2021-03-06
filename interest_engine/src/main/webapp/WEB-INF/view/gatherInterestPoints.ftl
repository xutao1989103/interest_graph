<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Bootstrap Core CSS -->
    <link href="http://cdn.bootcss.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="http://cdn.bootcss.com/ink/3.1.6/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <title>Interest Graph - an online representation of the specific things in which an individual is interested.</title>
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">Interest Graph</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="/use/index">Home</a>
                </li>
                <li>
                    <a href="service.html">Services</a>
                </li>
                <li>
                    <a href="contact.html">Contact</a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Portfolio <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="portfolio-1-col.html">1 Column Portfolio</a>
                        </li>
                        <li>
                            <a href="portfolio-2-col.html">2 Column Portfolio</a>
                        </li>
                        <li>
                            <a href="portfolio-3-col.html">3 Column Portfolio</a>
                        </li>
                        <li>
                            <a href="portfolio-4-col.html">4 Column Portfolio</a>
                        </li>
                        <li>
                            <a href="portfolio-item.html">Single Portfolio Item</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">登录</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Header Carousel -->
<header id="myCarousel" class="carousel slide">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
        <div class="item active">
            <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Slide One');"></div>
            <div class="carousel-caption">
                <h2>Caption 1</h2>
            </div>
        </div>
        <div class="item">
            <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Slide Two');"></div>
            <div class="carousel-caption">
                <h2>Caption 2</h2>
            </div>
        </div>
        <div class="item">
            <div class="fill" style="background-image:url('http://placehold.it/1900x1080&text=Slide Three');"></div>
            <div class="carousel-caption">
                <h2>Caption 3</h2>
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
        <span class="icon-prev"></span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
        <span class="icon-next"></span>
    </a>
</header>

<!-- Page Content -->
<div class="container" style="margin-top: 30px">

    <!-- Marketing Icons Section -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                兴趣采集
            </h1>
        </div>

    </div>
    <hr>
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">歌单采集</div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="inFile">选择文件</label>
                    <input type="file" id="inputFile">
                </div>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">微博采集</div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="weibo">微博账号</label>
                    <input type="text" id="weiboAccount" placeholder="Enter weibo account">
                    <label for="weibo">微博密码</label>
                    <input type="password" id="weiboPassword" placeholder="Enter weibo password">
                </div>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">兴趣标签</div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="weibo">兴趣标签</label>
                    <input type="text" id="interetTags" class="form-control" aria-describedby="helpBlock" placeholder="Enter tags">
                </div>
            </div>
        </div>

    </div>

    <hr>

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright &copy; Your Website 2014</p>
            </div>
        </div>
    </footer>

</div>
<!-- /.container -->

<!-- jQuery -->
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function(){

    });
    function remote(type, url, data, dataType, _success,_error){
        $.ajax({
            type:type,
            url:url,
            data:data,
            dataType:dataType,
            success:_success,
            error:_error
        });
    }
</script>
<!-- Script to Activate the Carousel -->
<script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
</script>

</body>
</html>