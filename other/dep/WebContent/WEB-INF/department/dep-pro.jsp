<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$().ready(function(){
		
		$("tr").click(function() {
			$(this).toggleClass("select");
		})
		
		$("#back").click(function(){
			location.href = "department";
		})
		
		$("#addPro").click(function(){
			var d_id = $("#top").find("[name = d_id]").val();
			var p_id = $("#btn").find("[name = select]").val();
			var d_name = $("#top").find("[name = d_name]").text();
			//alert(d_id+" "+p_id);
			location.href = "department?type=addPro&d_id="+ d_id+"&p_id="+p_id+"&d_name="+d_name;
		})	
		
		$("#delPro").click(function(){
			var length = $("#table .select").length;
			if (length > 0) {
				var d_id = $("#top").find("[name = d_id]").val();
				var d_name = $("#top").find("[name = d_name]").text();
				var p_id = new Array();
				$("#table .select").each(function(index, element) {
					p_id.push($(this).find(".idTD").text());
				})
				location.href = "department?type=delPro&d_id="+ d_id+"&p_id="+p_id+"&d_name="+d_name;
			} else {
				alert("请选择数据");
			}
		})	
	})

</script>
<style type="text/css">
#main {
	width: 600px;
	margin: 20px auto;
}
#main .select {
	background: #337ab7;
	color: #fff;
	font-weight: bold;
}
#btn div{
	float: left;
	margin-left: 30px;
	text-align: center;
}
</style>
</head>
<body>
	<div id="main">
		<div id="top">
			<input type="hidden" name="d_id" value="${d_id}">
			<label class="col-sm-2 control-label">部门：</label>
			<label name="d_name" class="col-sm-2 control-label">${d_name}</label>	
		</div>
		<div>		
			<table id="table" class="table table-hover table-bordered table-striped">
				<thead>
					<tr>
						<td>项目ID</td>
						<td>部门名称</td>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pro}" var="pro">
					<tr>
						<td class="idTD">${pro.id}</td>
						<td class="nameTd">${pro.name}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div id="btn">
			<div>
				<select name="select" class="form-control">
					<c:forEach items="${proNo}" var="proNo">
					<option value="${proNo.id}">${proNo.name}</option>
					</c:forEach>
				</select>	
			</div>
			<div>
				<input id="addPro" class="btn btn-primary" type="button" value="增加项目">	
				<input id="delPro" class="btn btn-primary" type="button" value="删除项目">	
				<input id="back" class="btn btn-primary" type="button" value="返回">
			</div>
		</div>
	</div>
</body>
</html>