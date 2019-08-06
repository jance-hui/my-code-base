<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<style type="text/css">
#top,#login{
	width: 400px;
	margin: 60px auto;
}
#login div{margin-top: 5px}
</style>
</head>
<body>
<div id="top">
	<h3 align="center">登录</h3>
</div>
<div id="login">
	<form action="user" method="post">
		<input type="hidden" name="type" value="doLogin">
		<div class="form-group col-sm-12">
			<input type="text" class="form-control" name="username" placeholder="请输入用户名">
		</div>
		<div class="form-group col-sm-12">
			<input type="password" class="form-control" name="password" placeholder="请输入密码">
		</div>
		<div class="form-group col-sm-offset-5">
			<button type="submit" class="btn btn-primary">登录</button>
		</div>
	</form>
</div>
</body>
</html>