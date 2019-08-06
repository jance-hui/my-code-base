<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>购物车</title>
<link rel="stylesheet" type="text/css" href="css/cart.css" />
<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$().ready(function(){
		$(document).on("change",".goods_num",function(){
			var goods_num = $(this);
			var cartID = ${cartID};
			var goodsID = goods_num.parent().parent().parent().data("id");
			var goodsNUM = $(this).val();
			var aa = $(this).parent().prev().find(".price").text();
			var price = aa.substring(1,aa.length);
			var money = goodsNUM*price;
			money = money.toFixed(2);
			var cart={
					id:cartID,
					user:null,
					total:null
			};
			var goods={
					id:goodsID,
					name:null,
					price:null,
					details:null,
					brand:null,
					genre:null,
					stock:null,
					salesVolume:null
			};
			var cartDetails={
					id:null,
					cart:cart,
					goods:goods,
					goodsNUM:goodsNUM,
					money:null
			};
			$.ajax({
				url:"updateCartDetails.do",
				type:"post",
				dataType:"text",
				contentType:"application/json",
				data:JSON.stringify(cartDetails),
				success:function(data){
					if(data == "true"){
						var newMoney = "￥"+money;
						goods_num.parent().next().find(".money").html(newMoney);
					}
				}
			})
		})
		$(document).on("click",".p6",function(){
			var del = $(this);
			var cartID = ${cartID};
			var goodsID = del.parent().parent().parent().data("id");
			
			var cart={
					id:cartID,
					user:null,
					total:null
			};
			var goods={
					id:goodsID,
					name:null,
					price:null,
					details:null,
					brand:null,
					genre:null,
					stock:null,
					salesVolume:null
			};
			var cartDetails={
					id:null,
					cart:cart,
					goods:goods,
					goodsNUM:null,
					money:null
			};
			$.ajax({
				url:"delCartDetails.do",
				type:"post",
				dataType:"text",
				contentType:"application/json",
				data:JSON.stringify(cartDetails),
				success:function(data){
					if(data == "true"){
						del.parent().parent().parent().css("display","none");
						del.parent().parent().parent().html("");
					}
				}
			})
		})
		function addMoney(){
			var allMoney = 0;
			var num = 0;
			$(".goods_div").each(function(){
				if($(this).find("[name=sub]").is(':checked')){
					var money = $(this).find(".money").text();
					money = money.substring(1,money.length);
					allMoney =  Number(allMoney) + Number(money);
					num++;
				}
			})
			if(allMoney>0){
				allMoney = Math.round(allMoney*100)/100;
				$("#buy_s .buy_money").html("￥"+allMoney);
				$("#buy_s .buy_number").html("已选商品"+num+"件");
				$("#buy_s .buy").css("background","#f40");
			}else{
				delMoney();
			}
		}
		function delMoney(){
			$("#buy_s .buy_money").html("￥0.00");
			$("#buy_s .buy_number").html("已选商品0件");
			$("#buy_s .buy").css("background","#aaa");
		}
		$(document).on("click",".goods_chose input",function(){
			addMoney();
			if($('#allChose').is(':checked')) {
				$('#allChose').prop('checked', false);
			} else {
				var checked = $('input:checked').length;
				var checkboxs = $('input[type="checkbox"]').length;
				if(parseInt(checked) == parseInt(checkboxs) - 1) {
					$('#allChose').prop('checked', true);
				}
			}
		})
		
		 $(document).on('click','#allChose',function(){
			if(this.checked) {
				$("#goods_new").find("[name='sub']").each(function(index, element) {
					$(this).prop('checked',true);
				})
				addMoney();
			}else {
				$("#goods_new").find("[name='sub']").each(function(index, element) {
					$(this).prop('checked',false);
				})
				delMoney();
			}
		});

		$("#allDel").on("click",function(){
			var cartID = ${cartID};
			
			var cart={
					id:cartID,
					user:null,
					total:null
			};
			var cartDetails={
					id:null,
					cart:cart,
					goods:null,
					goodsNUM:null,
					money:null
			};
			$.ajax({
				url:"delCartDetails.do",
				type:"post",
				dataType:"text",
				contentType:"application/json",
				data:JSON.stringify(cartDetails),
				success:function(data){
					if(data == "true"){
						$("#goods_new").css("display","none");
					}
				}
			})
		})
		$(document).on("click",".buy",function(){
			var money = $(this).prev().text();
			if(money != "￥0.00"){
				var goodsID = new Array();
				var goodsNUM = new Array();
				$(".goods_div").each(function(){
					if($(this).find("[name=sub]").is(':checked')){
						var id = $(this).data("id");
						goodsID.push(id);
						var num = $(this).find(".goods_num").val();
						goodsNUM.push(num);
					}
				})
				location.href = "showAddOrder.do?goodsID=" + goodsID + "&goodsNUM="+goodsNUM;
			}
		})
	})
</script>
<script src="js/cart.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<div id="top">
		<ul id="top_left" class="topClass">
			<li class="diqu">中国大陆</li>
			<c:if test="${username == null}">
				<li style="color:red;font-width:bord"><a href="showLoad.do">请登录</a></li>
			</c:if>
			<li>${username }</li>
			<li>消息</li>
		</ul>
		<ul id="top_right" class="topClass">
			<li>
				<a href="index.do">商城首页</a>
			</li>
			<li>
				<a href="order.do">我的订单</a>
			</li>
			<li>
				<a href="cart.do?id=${cartID}">购物车</a>
			</li>
			<li>
				<a href="">收藏夹</a>
			</li>
			<li>
				<a href="">商品分类</a>
			</li>
			<li>
				<a href="">卖家中心</a>
			</li>
			<li>
				<a href="">练习客服</a>
			</li>
			<li>
				<a href="">网站导航</a>
			</li>
		</ul>
	</div>
	<div id="header">
		<div id="headers">
			<div id="header_logo">
				<a href="index.html" title="淘宝网">
					<img src="img/logo.png" alt="淘宝网logo" style="height: 43px;" />
				</a>
			</div>
			<div id="header_text">购物车</div>
			<div id="header_search">
				<div id="header_search_form">
					<form action="select.do" name="search">
						<div>
							<input id="search_box" type="text" name="name" placeholder="请输入关键字" />
						</div>
						<div id="search_btns">
							<button type="submit" id="search_taoBao">搜 索</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="chose">
		<div class="choses">
			<div>
				<ul>
					<li class="select">全部商品</li>
					<li>降价商品</li>
					<li>库存紧张</li>
				</ul>
			</div>
		</div>
	</div>
	<div id="goods">
		<div id="goodss">
			<div id="goods_menu">
				<ul>
					<li>请选择</li>
					<li class="goods_mes">商品信息</li>
					<li>单价</li>
					<li>数量</li>
					<li>金额</li>
					<li>操作</li>
				</ul>
			</div>
			<div id="goods_new">
			<c:forEach items="${cartDetails }" var ="cartDetails">
				<div class="goods_div" data-id="${cartDetails.goods.id}">
					<ul>
						<li class="goods_chose">
							<input type="checkbox" name="sub"/>
						</li>
						<li class="goods_img">
						<c:forEach items="${cartDetails.goods.goodsImgs }" var ="img">
							<img style="width:80px;height:80px" src="goodsImg/${img.name}" />
						</c:forEach>
						</li>
						<li class="goods_mes">
							<div>
								<p>${cartDetails.goods.details}</p>
								<p class="p2">套餐类型：官方标配 颜色分类：加强版:黑色青轴(白光)</p>
							</div>
						</li>
						<li class="goods_other">
							<p class="p3"><del>￥ <fmt:formatNumber type="number" value="${cartDetails.goods.price+100.00}" pattern="0.00" maxFractionDigits="2"/></del></p>
							<p class="p4 price">￥${cartDetails.goods.price}</p>
						</li>
						<li class="goods_other">
							<select class="goods_num">
								<option value="1" <c:if test="${cartDetails.goodsNUM ==1}">selected</c:if>>1</option>
								<option value="2" <c:if test="${cartDetails.goodsNUM ==2}">selected</c:if>>2</option>
								<option value="3" <c:if test="${cartDetails.goodsNUM ==3}">selected</c:if>>3</option>
								<option value="4" <c:if test="${cartDetails.goodsNUM ==4}">selected</c:if>>4</option>
								<option value="5" <c:if test="${cartDetails.goodsNUM ==5}">selected</c:if>>5</option>
							</select>
						</li>
						<li class="goods_other">
							<p class="p5 money">￥${cartDetails.money}</p>
						</li>
						<li class="goods_other">
							<p class="p6">删除</p>
							<p class="p7">相似宝贝</p>
						</li>
					</ul>
				</div>
			</c:forEach>
			</div>
		</div>
	</div>
	<div id="buy">
		<div id="buys">
			<ul>
				<li><input id="allChose" type="checkbox" />全选</li>
				<li id="allDel" class="buy_col">清空</li>
				<li class="buy_col">移入收藏夹</li>
				<li class="buy_col">分享</li>
			</ul>
			<div id="buy_s">
				<ul>
					<li class="buy_number">已选商品0件</li>
					<li>合计（不含运费）</li>
					<li class="buy_money">￥0.00</li>
					<li class="buy">结 算</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="bottom">
		<div class="bottoms">
		<div id="bottom1">
			<ul>
				<li>阿里巴巴集团</li>
				<li>阿里巴巴国际站</li>
				<li>阿里巴巴中国站</li>
				<li>全球速卖通</li>
				<li>淘宝网</li>
				<li>天猫</li>
				<li>聚划算</li>
				<li>一淘</li>
				<li>阿里妈妈</li>
				<li>飞猪</li>
				<li>虾米</li>
				<li>阿里云计算</li>
				<li>云OS</li>
				<li>万网</li>
				<li>支付宝</li>
				<li>来往</li>
			</ul>
		</div>
		<div id="bottom2">
			<ul>
				<li>关于淘宝</li>
				<li>合作伙伴</li>
				<li>营销中心</li>
				<li>廉政举报</li>
				<li>联系客服</li>
				<li>开放平台</li>
				<li>诚征英才</li>
				<li>联系我们</li>
				<li>网站地图</li>
				<li>法律声明级隐私权政策</li>
			</ul>
			<div id="changhao">© 2018 Taobao.com 版权所有</div>
		</div>
		<div id="bottom3">
			<ul>
				<li>网络文化经营许可证：浙网文[2016]0132-032号</li>
				<li>|</li>
				<li>增值电信业务经营许可证：浙B2-20080224-1</li>
				<li>|</li>
				<li>信息网络传播视听节目许可证：1109364</li>
			</ul>
		</div>
	</div>
</div>
</body>

</html>