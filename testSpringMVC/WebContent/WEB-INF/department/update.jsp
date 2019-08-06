<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		var array = new Array();
		$(".dep").each(function(index,element) {
			$(this).each(function(index,element) {
				var id = $(this).find("[name = id]").val();
				var name = $(this).find("[name = name]").val();
				var count = $(this).find("[name = emp_count]").val();
				var deps ={
						id:id,
						name:name,
						emp_count:count
				};
				array.push(deps);
			})
		})
		$.ajax({
			url:"ajaxUpdateDep.do",
			type:"post",
			dataType:"text",
			contentType:"application/json",
			data:JSON.stringify(array),
			success:function(data){
				if(data =="true"){
					location.href = "department.do";
				}
			},
			error:function(data){
				alert("保存失败");
			}
		})
	})
})
</script>
<style type="text/css">
#main {
	width: 500px;
	margin: 20px auto;
}
#saveBtn{
text-align: center;
}
#top {
	width: 100%;
	height: 60px;
	text-align: center;
	font-size: 28px;
	font-weight: bold;
	line-height: 60px;
	margin: 0 auto;
}
</style>
</head>
<body>
<div id="top">
		<label>修改部门</label>	
	</div>
	<div id="main">
	<c:forEach items="${dep}" var="dep">
		<form class="form-horizontal dep" role="form" action="department"
			method="post">
			<input type="hidden" name="id" value="${dep.id}">
			<input type="hidden" name="emp_count" value="${dep.emp_count}">
			<div class="form-group">
				<label for="firstname" class="col-sm-4 control-label">修改部门姓名：</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="name" value="${dep.name}">
				</div>
			</div>
		</form>
		<hr><hr>
		</c:forEach>
		<div id="saveBtn" class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<button type="button" class="btn btn-primary">保存</button>
			</div>
		</div>
	</div>
</body>
</html>