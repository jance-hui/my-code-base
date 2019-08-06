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
		<form class="form-horizontal" role="form" action="employee"
			method="post">
			<input type="hidden" name="type" value="update">
			<input type="hidden" name="id" value="${emp.id}">
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">修改员工姓名：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						value="${emp.name}">
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">修改员工性别：</label>
				<div class="col-sm-10">
					<input type="radio" value="男" name="sex"
						<c:if test="${emp.sex == '男'}"> checked </c:if>>男
					<input type="radio" value="女" name="sex"
						<c:if test="${emp.sex == '女'}"> checked </c:if>>女
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">修改员工年龄：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age"
						value="${emp.age}">
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">修改员工部门：</label>
				<div class="col-sm-10">
					<select name="dep">
						<option value="0"></option>
						<c:forEach items="${dep}" var="dep">
						<option value="${dep.id}" <c:if test="${dep.name == emp.dep.name}"> selected </c:if>>${dep.name}</option>
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