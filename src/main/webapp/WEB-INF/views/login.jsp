<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <!-- <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script> -->
    <!-- <script src="//code.jquery.com/jquery-1.11.1.min.js"></script> -->

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h3 class="omb_authTitle">Login or Sign up</h3>
    <div class="row omb_row-sm-offset-3 omb_socialButtons">
        <div class="col-xs-4 col-sm-2">
            <a href="#" class="btn btn-lg btn-block omb_btn-facebook">
                <span class="hidden-xs">naver</span>
            </a>
        </div>
        <div class="col-xs-4 col-sm-2">
            <a href="#" class="btn btn-lg btn-block omb_btn-twitter">
                <span class="hidden-xs">kakao</span>
            </a>
        </div>
        <div class="col-xs-4 col-sm-2">
            <a href="#" class="btn btn-lg btn-block omb_btn-google">
                <span class="hidden-xs">Google+</span>
            </a>
        </div>
    </div>

    <div class="row omb_row-sm-offset-3 omb_loginOr">
        <div class="col-xs-12 col-sm-6">
            <hr class="omb_hrOr">
            <span class="omb_spanOr">or</span>
        </div>
    </div>

    <div class="row omb_row-sm-offset-3">
        <div class="col-xs-12 col-sm-6">
            <form class="omb_loginForm" action="" autocomplete="off" method="POST">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" class="form-control" name="username" placeholder="email address">
                </div>
                <span class="help-block"></span>

                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                    <input  type="password" class="form-control" name="password" placeholder="Password">
                </div>
                <span class="help-block">Password error</span>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
            </form>
        </div>
    </div>
    <div class="row omb_row-sm-offset-3">
        <div class="col-xs-12 col-sm-3">
        </div>
        <div class="col-xs-12 col-sm-3">
            <p class="omb_forgotPwd">
                <a href="#">Forgot password?</a>
            </p>
        </div>
    </div>
</div>

</body>
</html>