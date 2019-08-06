<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="entity.*"%>
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
	<%
		Employee emp = (Employee) request.getAttribute("emp");
	%>
	<div id="main">
		<form class="form-horizontal" role="form" action="employee"
			method="post">
			<input type="hidden" name="type" value="update">
			<input type="hidden" name="id" value="<%=emp.getId()%>">
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">修改员工姓名：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						value="<%=emp.getName()%>">
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">修改员工性别：</label>
				<div class="col-sm-10">
					<input type="radio" value="男" name="sex"
						<%if ("男".equals(emp.getSex())) {%> checked <%}%>>男
					<input type="radio" value="女" name="sex"
						<%if ("女".equals(emp.getSex())) {%> checked <%}%>>女
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">修改员工年龄：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age"
						value="<%=emp.getAge()%>">
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">修改员工部门：</label>
				<div class="col-sm-10">
					<select name="dep">
						<option value="0"></option>
						<option value="1" <%if ("开发部".equals(emp.getDep().getName())) {%> selected <%}%>>开发部</option>
						<option value="2" <%if ("测试部".equals(emp.getDep().getName())) {%> selected <%}%>>测试部</option>
						<option value="3" <%if ("QA部".equals(emp.getDep().getName())) {%> selected <%}%>>QA部</option>
						<option value="4" <%if ("财务部".equals(emp.getDep().getName())) {%> selected <%}%>>财务部</option>
						<option value="5" <%if ("人事部".equals(emp.getDep().getName())) {%> selected <%}%>>人事部</option>
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