<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="entity.*"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$().ready(function () {
	$("#saveBtn").click(function () {
		var emps = "";
		$(".emp").each(function(index,element){
			var ids = $(this).find("[name = id]").val();
			var names = $(this).find("[name = name]").val();
			var sexs = $(this).find("[name = sex]:checked").val();
			var ages = $(this).find("[name = age]").val();
			var deps = $(this).find("[name = dep]").val();
			emps+=ids+","+names+","+sexs+","+ages+","+deps+";"
		//	alert(ids+" "+names+" "+sexs+" "+ages+" "+deps);
		})
		emps = emps.substring(0,emps.length-1);
		//alert(emps);
		location.href = "employee?type=updateBatch&emps=" + emps;
	})
})
</script>
<style type="text/css">
#main {
	width: 800px;
	margin: 20px auto;
}
#saveBtn{
text-align: center;
}
</style>
</head>
<body>
	<%
		List<Employee> emp = (List<Employee>) request.getAttribute("emp");
	%>
	<div id="main">
	<%for(int i=0;i<emp.size();i++){ %>
		<form class="form-horizontal emp" role="form" action="employee"
			method="post">
			<input type="hidden" name="id" value="<%=emp.get(i).getId()%>">
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">修改员工姓名：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name" value="<%=emp.get(i).getName()%>">
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">修改员工性别：</label>
				<div class="col-sm-10">
					<input type="radio" value="男" name="sex"
						<%if ("男".equals(emp.get(i).getSex())) {%> checked <%}%>>男
					<input type="radio" value="女" name="sex"
						<%if ("女".equals(emp.get(i).getSex())) {%> checked <%}%>>女
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">修改员工年龄：</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age"
						value="<%=emp.get(i).getAge()%>">
				</div>
			</div>
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">修改员工部门：</label>
				<div class="col-sm-10">
					<select name="dep">
						<option value="0"></option>
						<option value="1" <%if ("开发部".equals(emp.get(i).getDep().getName())) {%> selected <%}%>>开发部</option>
						<option value="2" <%if ("测试部".equals(emp.get(i).getDep().getName())) {%> selected <%}%>>测试部</option>
						<option value="3" <%if ("QA部".equals(emp.get(i).getDep().getName())) {%> selected <%}%>>QA部</option>
						<option value="4" <%if ("财务部".equals(emp.get(i).getDep().getName())) {%> selected <%}%>>财务部</option>
						<option value="5" <%if ("人事部".equals(emp.get(i).getDep().getName())) {%> selected <%}%>>人事部</option>
					</select>
				</div>
			</div>
			
		</form>
		<hr><hr><hr>
		<% }%>
		<div id="saveBtn" class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-primary">保存</button>
				</div>
			</div>
	</div>

</body>
</html>