<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="js/html5.js"></script>
<script type="text/javascript" src="js/respond.min.js"></script>
<script type="text/javascript" src="js/PIE_IE678.js"></script>
<![endif]-->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="css/style.css" />
<link href="assets/css/codemirror.css" rel="stylesheet">
<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="Widget/zTree/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->
<link href="Widget/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="Widget/webuploader/0.1.5/webuploader.css" rel="stylesheet"
	type="text/css" />

	<script src="js/jquery-1.9.1.min.js"></script>   
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>
<script src="assets/layer/layer.js" type="text/javascript" ></script>
<script src="assets/laydate/laydate.js" type="text/javascript"></script>
<script type="text/javascript" src="Widget/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="Widget/icheck/jquery.icheck.min.js"></script> 
 <script type="text/javascript" src="Widget/zTree/js/jquery.ztree.all-3.5.min.js"></script> 
<script type="text/javascript" src="Widget/Validform/5.3.2/Validform.min.js"></script> 
<script type="text/javascript" src="Widget/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript" src="Widget/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="Widget/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="Widget/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script> 
<script src="js/lrtk.js" type="text/javascript" ></script>
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script>
$().ready(function(){
	$("#update").click(function(){
		var id = $("#form").find("[name=id]").val();
		var name = $("#form").find("[name=name]").val();
		var price = $("#form").find("[name=price]").val();
		var brandID = $("#form").find("[name=brandId]").val();
		var genreID = $("#form").find("[name=genreId]").val();
		var details = $("#form").find("[name=details]").val();
		var stock = $("#form").find("[name=stock]").val();
		var salesVolume = $("#form").find("[name=salesVolume]").val();
		var brand={
				id:brandID,
				name:null
		}
		var genre={
				id:genreID,
				name:null
		}
		var goods={
				id:id,
				name:name,
				price:price,
				details:details,
				brand:brand,
				genre:genre,
				stock:stock,
				salesVolume:salesVolume,
				goodsImgs:null
		}
		$.ajax({
			url:"updateGoods.do",
			type:"post",
			dataType:"text",
			contentType:"application/json",
			data:JSON.stringify(goods),
			success:function(data){
				if(data == "true"){
					window.parent.location.reload();
					$("#close").trigger('click');
				}
			}
		})
	})
})
</script>
<title>修改商品</title>
</head>
<body>
	<div class="clearfix" id="add_picture">
		
		<div class="" style="margin:0 50px; text-align:center">
			<div class="type_title">修改商品</div>
			<form id="form" action="updateGoods.do" method="post"
				class="form form-horizontal" id="form-article-add">
				<div class="clearfix cl" style="margin-top:10px;">
					<input type="hidden" name="id" value="${goods.id }">
					<label class="form-label col-2"><span class="c-red">*</span>商品名称：</label>
					<div class="formControls col-10">
						<input type="text" class="input-text" value="${goods.name}"
							placeholder="" id="" name="name">
					</div>
				</div>
				<div id="aaa" class=" clearfix cl" style="margin-top:10px;">

					<div class="Add_p_s">
						<label class="form-label col-2">品&nbsp;&nbsp;&nbsp;&nbsp;牌：</label>
						<div class="formControls col-2">
							<span class="select-box"> <select class="select"
								name="brandId">
									<option>请选择</option>
									<c:forEach items="${brand }" var="brand">
										<option value="${brand.id }"
											<c:if test="${goods.brand.id == brand.id}">selected</c:if>>${brand.name }</option>
									</c:forEach>
							</select>
							</span>
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">类&nbsp;&nbsp;&nbsp;&nbsp;别：</label>
						<div class="formControls col-2">
							<span class="select-box"> <select class="select"
								name="genreId">
									<option>请选择</option>
									<c:forEach items="${genre }" var="genre">
										<option value="${genre.id }"
											<c:if test="${goods.genre.id == genre.id}">selected</c:if>>${genre.name }</option>
									</c:forEach>
							</select>
							</span>
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">价&nbsp;&nbsp;&nbsp;&nbsp;格：</label>
						<div class="formControls col-2">
							<input type="text" class="input-text" value="${goods.price}"
								placeholder="" id="" name="price">
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">库&nbsp;&nbsp;&nbsp;&nbsp;存：</label>
						<div class="formControls col-2">
							<input type="text" class="input-text" value="${goods.stock}"
								placeholder="" id="" name="stock">
						</div>
					</div>
					<div class="Add_p_s">
						<label class="form-label col-2">销&nbsp;&nbsp;&nbsp;&nbsp;量：</label>
						<div class="formControls col-2">
							<input type="text" class="input-text"
								value="${goods.salesVolume}" placeholder="" id=""
								name="salesVolume">
						</div>
					</div>


				</div>

				<div class="clearfix cl" style="margin-top:10px;">
					<label class="form-label col-2">商品详情：</label>
					<div class="formControls col-10">
						<textarea name="details" cols="" rows="" class="textarea"
							placeholder="说点什么...最少输入10个字符" datatype="*10-100"
							dragonfly="true" nullmsg="备注不能为空！"
							onKeyUp="textarealength(this,200)">${goods.details}</textarea>
						<p class="textarea-numberbar">
							<em class="textarea-length">0</em>/200
						</p>
					</div>
				</div>

				<div class="clearfix cl">
					<label class="form-label col-2">允许评论：</label>
					<div class="formControls col-2 skin-minimal">
						<div class="check-box" style="margin-top: 9px">
							<input type="checkbox" id="checkbox-1"><label
								for="checkbox-1">&nbsp;</label>
						</div>
					</div>
				</div>
				<div class="clearfix cl">
					<div class="Button_operation">
						<button id="update"
							class="btn btn-primary radius" type="button">
							<i class="icon-save "></i>保存并提交审核
						</button>
						<button id="close" onClick="layer_close();" class="btn btn-default radius"
							type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>