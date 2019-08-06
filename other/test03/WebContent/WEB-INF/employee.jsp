<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="entity.*"%>
<%@page import="java.util.List"%>
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
	background:
}

#main .select {
	background: #337ab7;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#add").click(function() {
			location.href = "employee?type=showAdd";
		})
		var selectId = new Array();
		$("tr").click(function() {
			if($(this).is(".select")){
				alert("you");
				$(this).removeClass("select");
				var id = $(this).data("id");
				for(var i=0;i<selectId.length;i++){
					if(selectId[i] == id){
						selectId.splice(i,1);
					}
				}
				
			}else{
				alert("mei you");
				$(this).addClass("select");
				var id = $(this).data("id");
				selectId.push(id);
			}
		})
		$("#update").click(function() {
			if (selectId.length>0) {
				var str ="";
				for(var i=0;i<selectId.length;i++){
					if(i<selectId.length-1){
					str+="id="+selectId[i]+"&";
					}else{
						str+="id="+selectId[i];
					}
				}
				location.href = "employee?type=showUpdate&" + str;
			} else {
				alert("请选择数据");
			}
		})
		$("#del").click(function() {
			if (selectId.length>0) {
				var str ="";
				for(var i=0;i<selectId.length;i++){
					if(i<selectId.length-1){
					str+="id="+selectId[i]+"&";
					}else{
						str+="id="+selectId[i];
					}
				}
				location.href = "employee?type=showDel&" + str;
			} else {
				alert("请选择数据");
			}
		})
		$("#select").click(function() {
			location.href = "employee?type=showSelect";
		})
	})
</script>
</head>
<body>
	<div id="main">
		<%
			List<Employee> list = (List<Employee>) request.getAttribute("emps");
		%>
		<table id="table"
			class="table table-hover table-bordered table-striped">
			<tr>
				<td>工号</td>
				<td>姓名</td>
				<td>性别</td>
				<td>年龄</td>
				<td>部门</td>
			</tr>
			<%
				for (int i = 0; i < list.size(); i++) {
			%>
			<tr data-id="<%=list.get(i).getId()%>">
				<td><%=list.get(i).getId()%></td>
				<td><%=list.get(i).getName()%></td>
				<td><%=list.get(i).getSex()%></td>
				<td><%=list.get(i).getAge()%></td>
				<td><%=list.get(i).getDep().getName()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<input id="add" class="btn btn-primary" type="button" value="增加">
		<input id="update" class="btn btn-primary" type="button" value="修改">
		<input id="del" class="btn btn-primary" type="button" value="删除">
		<input id="select" class="btn btn-primary" type="button" value="查找">
	</div>
</body>
</html>