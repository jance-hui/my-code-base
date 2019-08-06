<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {
	
	$(document).on("change",".province",function(){
		var p_id = $(this).val();
		$.ajax({
			url:"findCity.do",
			type:"post",
			data:{p_id:p_id},
			dataType:"json",
			success: function(data) {
				var result = data;
				var pro = "<option value='-1'>请选择所在城市</option>";
				$.each(result,function(index, n){
					pro+="<option value='"+result[index].cityID+"'>"+result[index].city+"</option>";
				});
				$("。city").empty();
				$(".city").html(pro); 
			}
		})
	})
	
	$(document).on("change",".city",function(){
		var c_id = $(this).val();
		$.ajax({
			url:"findArea.do",
			type:"post",
			data:{c_id:c_id},
			dataType:"json",
			success: function(data) {
				var result = data;
				var pro = "<option value='-1'>请选择所在地区</option>";
				$.each(result,function(index, n){
					pro+="<option value='"+result[index].areaID+"'>"+result[index].area+"</option>";
				});
				$("。area").empty();
				$(".area").html(pro); 
			}
		})
	})
	
})

</script>
<title>Insert title here</title>
</head>
<body>
	<select name="province" class="province col-sm-4 form-control">
		<option value="">请选择所在省</option>
		<c:forEach items="${proList}" var="province">
			<option value="${province.provinceID}">${province.province}</option>
		</c:forEach>
	</select>
	
	<select name="city"  class="city col-sm-4 form-control">
		<option value="">请选择所在城市</option>
	</select>
	
	<select name="area"  class="area col-sm-4 form-control">
		<option value="">请选择所在地区</option>
	</select>
</body>
</html>