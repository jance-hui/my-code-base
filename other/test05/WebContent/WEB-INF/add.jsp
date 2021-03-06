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
	width: 600px;
	margin: 20px auto;
}
</style>
</head>
<body>
	<div id="main">
		<form class="form-horizontal" role="form" action="employee" method="post">
			<input type="hidden" name="type" value="add">
			<div class="form-group">
				<label class="col-sm-2 control-label">员工姓名：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name" placeholder="请输入姓名">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">员工性别：</label>
				<div class="col-sm-10">
					<input type="radio" value="男" name="sex" checked="checked">男</input> 
					<input type="radio" value="女" name="sex" >女</input>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">员工年龄：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age" placeholder="请输入年龄">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">员工部门：</label>
				<div class="col-sm-10">
					<select name="dep">
						<option value="0"></option>
						<c:forEach items="${dep}" var="deps">
						<option value="${deps.id}">${deps.name}</option>
						</c:forEach>
					</select>
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