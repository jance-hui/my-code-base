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
			var nameTd = $(this).find(".nameTd");
			var name = nameTd.text();
			var sexTd = $(this).find(".sexTd");
			var sex = sexTd.text();
			var ageTd = $(this).find(".ageTd");
			var age = ageTd.text();
			var depTd = $(this).find(".depTd");
			var dep = depTd.text();
		//	alert(name + " " + sex + " " + age + " " + dep);

			var nameInput = $("<input type='text'value='" + name + "'name='updateName'/>");
			nameTd.html(nameInput);
			
			if ("男" == sex) {
				var sexInput = $("<select><option value='男' name='updateSex' selected>男</option><option value='女' name='updateSex'>女</option></select>");
			} else if ("女" == sex) {
				var sexInput = $("<select><option value='男' name='updateSex'>男</option><option value='女' name='updateSex' selected>女</option></select>");
			}
			sexTd.html(sexInput);
			
			var ageInput = $("<input type='text' value='" + age + "'name='updateAge'/>");
			ageTd.html(ageInput);
	
			if ("开发部" == dep) {
				var depInput = $("<select><option value='1' name='updateDep' selected>开发部</option> <option value='2' name='updateDep'>测试部</option> <option value='3' name='updateDep'>QA部</option> <option value='4' name='updateDep'>财务部</option> <option value='5' name='updateDep'>人事部</option> </select>");
			} else if ("测试部" == dep) {
				var depInput = $("<select><option value='1' name='updateDep'>开发部</option> <option value='2' name='updateDep' selected>测试部</option> <option value='3' name='updateDep'>QA部</option> <option value='4' name='updateDep'>财务部</option> <option value='5' name='updateDep'>人事部</option> </select>");
			} else if ("QA部" == dep) {
				var depInput = $("<select><option value='1' name='updateDep'>开发部</option> <option value='2' name='updateDep'>测试部</option> <option value='3' name='updateDep' selected>QA部</option> <option value='4' name='updateDep'>财务部</option> <option value='5' name='updateDep'>人事部</option> </select>");
			} else if ("财务部" == dep) {
				var depInput = $("<select><option value='1' name='updateDep'>开发部</option> <option value='2' name='updateDep'>测试部</option> <option value='3' name='updateDep'>QA部</option> <option value='4' name='updateDep' selected>财务部</option> <option value='5' name='updateDep'>人事部</option> </select>");
			} else if ("人事部" == dep) {
				var depInput = $("<select><option value='1' name='updateDep'>开发部</option> <option value='2' name='updateDep'>测试部</option> <option value='3' name='updateDep'>QA部</option> <option value='4' name='updateDep'>财务部</option> <option value='5' name='updateDep' selected>人事部</option> </select>");
			}
			depTd.html(depInput);
		
		})
		
		$("#updateBatch").click(function() {
			var length = $("#table .selectTr").length;
			if (length > 0) {
				var array = new Array();
				$("#table .selectTr").each(function(index,element) {
					$(this).each(function(index,element) {
						var ids = $(this).find("[name = id]").val();
						var names = $(this).find("[name = updateName]").val();
						var sexs = $(this).find("[name = updateSex]:selected").val();
						var ages = $(this).find("[name = updateAge]").val();
						var deps = $(this).find("[name = updateDep]:selected").val();
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
			<thead>
				<tr>
					<td>姓名</td>
					<td>性别</td>
					<td>年龄</td>
					<td>部门</td>
				</tr>
			</thead>
			<tbody>
				<%
					for (int i = 0; i < list.size(); i++) {
				%>
				<tr class="emp" data-id="<%=list.get(i).getId()%>">
					<input type="hidden" name="id" value="<%=list.get(i).getId()%>">
					<td class="nameTd"><%=list.get(i).getName()%></td>
					<td class="sexTd"><%=list.get(i).getSex()%></td>
					<td class="ageTd"><%=list.get(i).getAge()%></td>
					<td class="depTd"><%=list.get(i).getDep().getName()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<input id="showAdd" class="btn btn-primary" type="button" value="增加">
		<input id="showUpdate" class="btn btn-primary" type="button" value="修改"> 
		<input id="del" class="btn btn-primary" type="button" value="删除"> 
		<input id="showUpdateBatch" class="btn btn-primary" type="button" value="批量修改1"> 
		<input id="updateBatch" class="btn btn-primary" type="button" value="批量修改2">
		<input id="delBatch" class="btn btn-primary" type="button" value="批量删除"> 
		<input id="find" class="btn btn-primary" type="button" value="查找">
	</div>
</body>
</html>