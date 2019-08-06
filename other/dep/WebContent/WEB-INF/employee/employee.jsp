<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员工管理</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<style type="text/css">
#top,#main {
	width: 750px;
	margin: 30px auto;
}

#main .select {
	background: #337ab7;
	color: #fff;
	font-weight: bold;
}

#table td {
	width: 120px;
}

#table input {
	width: 80px;
	height: 25px;
}

select {
	width: 80px;
	height: 25px;
}

#page {
	width: 100%;
	float: left;
}

#pages {
	position: relative;
	float: left;
}

#message {
	float: left;
	position: relative;
	margin-top: 30px;
	margin-left: 15px;
	color: #ccc;
	font-size: 14px;
}
#select{
	margin-top: 20px;
	text-align: center;
}
#select div{
	float: left;
	margin-left: 10px;
}
#select button{
	margin-left: 30px;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {
		
		$("#showAdd").click(function() {
			location.href = "employee?type=showAdd";
		})
		
		var selectId = -1;
		var selectIds = new Array();
		
		$("tr").click(function() {
			$(this).toggleClass("select");
				selectId = $(this).data("id");
		})

		$("#showUpdate").click(function() {
			if (selectId > -1) {
				location.href = "employee?type=showUpdate&id="+ selectId;
			} else {
				alert("请选择数据");
			}
		})
		
		$("#del").click(function() {
			if (selectId > -1) {
				location.href = "employee?type=del&id="+ selectId;
			} else {
				alert("请选择数据");
			}
		})

		function doBatch(type) {
			var length = $("#table .select").length;
			var ids = new Array();
			if (length > 0) {
				$("#table .select").each(function(index, element) {
					ids.push($(this).data("id"));
				})
				location.href = "employee?type=" + type+ "&ids=" + ids;
			} else {
				alert("请选择数据");
			}
		}

		$("#showUpdateBatch").click(function() {
			doBatch("showUpdateBatch");
		})

		$("#delBatch").click(function() {
			doBatch("delBatch");
		})
		

		$(".emp").dblclick(function() {
			$(this).unbind("dblclick");
			$(this).unbind("click");
			
			$(this).addClass("selectTr");
			
			$(this).find(".name").css("display","none");
			$(this).find("[name = updateName]").css("display","inline");
			
			$(this).find(".sex").css("display","none");
			$(this).find("[name = updateSex]").css("display","inline");
			
			$(this).find(".age").css("display","none");
			$(this).find("[name = updateAge]").css("display","inline");
	
			$(this).find(".dep").css("display","none");
			$(this).find("[name = updateDep]").css("display","inline");
		
		})
		
		$("#updateBatch").click(function() {
			var length = $("#table .selectTr").length;
			if (length > 0) {
				var array = new Array();
				$("#table .selectTr").each(function(index,element) {
					$(this).each(function(index,element) {
						var ids = $(this).find("[name = id]").val();
						var names = $(this).find("[name = updateName]").val();
						var sexs = $(this).find("[name = updateSex]").val();
						var ages = $(this).find("[name = updateAge]").val();
						var dep_id = $(this).find("[name = updateDep]").val();
						var dep_name = $(this).find("[name = d_id]").val();
						var dep_count = $(this).find("[name = d_count]").val();
						var deps ={
								id:dep_id,
								name:dep_name,
								emp_count:dep_count
						};
						var emps ={
								id:ids,
								name:names,
								sex:sexs,
								age:ages,
								dep:deps
						};
						array.push(emps);
					})
				})
				var str = JSON.stringify(array);
				//url传输不能有大括号{} 使用str.replace()转换。
				str = str.replace(/{/g,"%7b");
				str = str.replace(/}/g,"%7d");
				alert(str);
				location.href = "employee?type=updateBatch2&emps="+ str;
			} else {
				alert("请选择数据");
			}
		})
		if(${p.ye}<=1){
			$("#pre").addClass("disabled");
			$("#pre").find("a").attr("onclick","return false");
			$("#begin").addClass("disabled");
			$("#begin").find("a").attr("onclick","return false")
		}
		if(${p.ye}>=${p.maxYe}){
			$("#next").addClass("disabled");
			$("#next").find("a").attr("onclick","return false");
			$("#end").addClass("disabled");
			$("#end").find("a").attr("onclick","return false")
		}
	})
</script>
</head>
<body>
	<div id="top">
		<h2 align="center">员工管理</h2>
	</div>
	<div id="main">
		<div id="select">
			<form action="employee" method="post">
				<div class="form-group">
					<div>
						<input type="text" class="col-sm-4 form-control" name="name" placeholder="请输入名称" <c:if test="${emp.name!=''}">value="${emp.name}"</c:if>>
					</div>	
					<div>
						<select name="sex"  class="col-sm-4 form-control">
							<option value="">请选择性别</option>
							<option value="男" <c:if test="${emp.sex=='男'}">selected</c:if>>男</option>
							<option value="女" <c:if test="${emp.sex=='女'}">selected</c:if>>女</option>
						</select>
					</div>
					<div>
						<input type="text" class="col-sm-4 form-control" name="age" placeholder="请输入年龄" value="${emp.age!=-1?emp.age:''}">
					</div>
					<div>
						<select name="dep"  class="col-sm-4 form-control">
							<option value="0">请选择部门</option>
							<c:forEach items="${dep}" var="deps">
							<option value="${deps.id}" <c:if test="${emp.dep.id==deps.id}">selected</c:if>>${deps.name}</option>
							</c:forEach>
						</select>
					</div>
					<button type="submit" class="btn btn-primary">查找</button>
				</div>
			</form>
		</div>
		<table id="table"
			class="table table-hover table-bordered table-striped">
			<thead>
				<tr>
					<td>姓名</td>
					<td>性别</td>
					<td>年龄</td>
					<td>部门</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${emps}" var="emp">
					<tr class="emp" data-id="${emp.id}">
						<input type="hidden" name="id" value="${emp.id}">
						<input type="hidden" name="d_id" value="${emp.dep.id}">
						<input type="hidden" name="d_count" value="${emp.dep.emp_count}">
						<td class="nameTd">
							<p class="name">${emp.name}</p> <input type="text"
							value="${emp.name}" name="updateName" style="display: none;" />
						</td>
						<td class="sexTd">
							<p class="sex">${emp.sex}</p> <select name="updateSex"
							style="display: none;">
								<option value="男"
									<c:if test="${emp.sex == '男'}"> selected </c:if>>男</option>
								<option value="女"
									<c:if test="${emp.sex == '女'}"> selected </c:if>>女</option>
						</select>
						</td>
						<td class="ageTd">
							<p class="age">${emp.age}</p> <input type="text"
							value="${emp.age}" name="updateAge" style="display: none;" />
						</td>
						<td class="depTd">
							<p class="dep">${emp.dep.name}</p> <select name="updateDep"
							style="display: none;">
								<option value="0"></option>
								<c:forEach items="${dep}" var="dep">
									<option value="${dep.id}"
										<c:if test="${dep.id == emp.dep.id}"> selected </c:if>>${dep.name}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="page">
			<div id="pages">
				<ul class="pagination">
					<li id="begin"><a href="employee?ye=1&name=${emp.name}&sex=${emp.sex}&age=${emp.age!=-1?emp.age:''}&dep=${emp.dep.id}">首页</a></li>
					<li id="pre"><a href="employee?ye=${p.ye-1}&name=${emp.name}&sex=${emp.sex}&age=${emp.age!=-1?emp.age:''}&dep=${emp.dep.id}">上一页</a></li>
					<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
						<li <c:if test="${p.ye == status.index}">class="active"</c:if>><a
							href="employee?ye=${status.index}&name=${emp.name}&sex=${emp.sex}&age=${emp.age!=-1?emp.age:''}&dep=${emp.dep.id}">${status.index}</a></li>
					</c:forEach>
					<li id="next"><a href="employee?ye=${p.ye+1}&name=${emp.name}&sex=${emp.sex}&age=${emp.age!=-1?emp.age:''}&dep=${emp.dep.id}">下一页</a></li>
					<li id="end"><a href="employee?ye=${p.maxYe}&name=${emp.name}&sex=${emp.sex}&age=${emp.age!=-1?emp.age:''}&dep=${emp.dep.id}">末页</a></li>
				</ul>
			</div>
			<div id="message">
				<p>共${p.maxYe}页</p>
			</div>
		</div>
		<div>
			<input id="showAdd" class="btn btn-success" type="button" value="增加">
			<input id="showUpdate" class="btn btn-primary" type="button" value="修改">
			<input id="del" class="btn btn-warning" type="button" value="删除"> 
			<input id="showUpdateBatch" class="btn btn-info" type="button" value="转页修改"> 
			<input id="updateBatch" class="btn btn-primary" type="button" value="直接修改">
			<input id="delBatch" class="btn btn-warning" type="button" value="批量删除"> 
			<input id="find" class="btn btn-success" type="button" value="查找">
		</div>
	</div>
</body>
</html>