<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<style type="text/css">
#main {
	width: 800px;
	margin: 20px auto;
}
</style>
</head>
<body>
	<div id="main">
		<form class="form-horizontal" role="form" action="user"
			method="post">
			<input type="hidden" name="type" value="update">
			<input type="hidden" name="id" value="${user.id}">
			<div class="form-group col-sm-10">
				<input type="text" class="form-control" name="name" placeholder="请输入新的用户名">
			</div>
			<div class="form-group col-sm-10">
				<input type="password" class="form-control" name="pwd" placeholder="请输入新的密码">
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">确认修改</button>
				</div>
			</div>
		</form>
	</div>

</body>
</html>