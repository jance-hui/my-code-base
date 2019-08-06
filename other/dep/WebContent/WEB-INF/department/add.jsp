<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<style type="text/css">
#main {
	width: 600px;
	margin: 20px auto;
}
</style>
</head>
<body>
	<div id="main">
		<form class="form-horizontal" role="form" action="department" method="post">
			<input type="hidden" name="type" value="add">
			<input type="hidden" name="count" value="0">
			<div class="form-group">
				<label class="col-sm-2 control-label">部门名称：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name" placeholder="请输入部门名称">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">保存</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>