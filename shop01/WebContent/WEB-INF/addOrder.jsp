<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加订单</title>
<title>确认订单-云购物商城</title>
<link rel="stylesheet" type="text/css" href="css/addOrder.css"/>
<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" >
$().ready(function(){
	
	$("#backCart").click(function(){
		location.href="cart.do?id=${cartID}";
	})
	
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
				$(".city").empty();
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
				$(".area").empty();
				$(".area").html(pro); 
			}
		})
	})
	
	$("#addNewPosition").click(function(){
		$("#newPosition").show(200);
	})
	$("#no").click(function(){
		$("#newPosition").hide(200);
	})
	
	$("#add").click(function(){
		var userID = $("#newPosition").find("[name=userID]").val();
		var areaID = $("#newPosition").find("[name=areaID]").val();
		var name = $("#newPosition").find("[name=name]").val();
		var phone = $("#newPosition").find("[name=phone]").val();
		var position = $("#newPosition").find("[name=position]").val();
		var area={
				id:null,
				areaID:areaID,
				area:null,
				city:null
		};
		var user={
				id:userID,
				username:null,
				password:null,
				name:null,
				photo:null
		};
		var pos={
				id:null,
				area:area,
				position:position,
				user:user,
				name:name,
				phone:phone
		};
		$.ajax({
			url:"addPosition.do",
			type:"post",
			dataType:"json",
			contentType:"application/json",
			data:JSON.stringify(pos),
			success:function(data){
				var str="<li class='myPosition'><input type='hidden' name='posID' value='"+data.id+"'/><p class='itemName'>"+data.name +"</p><p class='itemTel'>"+data.phone+"</p><p class='itemRegion'>"+data.area.city.province.province+" "+data.area.city.city+" "+data.area.area+"</p><p class='itemStreet'>"+data.position+"</p><span>编辑</span></li>";
				$("#positionList ul").append(str);
				$("#newPosition").hide(200);
			}
		})
	})
	$(document).on("click","#positionList li",function(){
		$("#positionList li").each(function(){
			$(this).removeClass("chosePos");
		});
		$(this).toggleClass("chosePos");
	})
	
	$(document).on("click","#buyNow",function(){
		var positionID = $("#positionList").find(".chosePos").find("[name=posID]").val();
		if(positionID == null){
			alert("请选择收货地址");
		}else{
			var userID = ${userID};
			var total = ${total};
			var user={
				id:userID,
				username:null,
				password:null,
				name:null,
				phine:null
			};
			var position ={
					id:positionID,
					area:null,
					position:null,
					user:null,
					name:null,
					phone:null,
			};
			var orders = {
					id:null,
					user:user,
					orderNUM:null,
					position:position,
					total:total,
					timeType:null,
					orderDetails:null
			};
			
			$.ajax({
				url:"addOrder.do",
				type:"post",
				dataType:"text",
				contentType:"application/json",
				data:JSON.stringify(orders),
				success:function(data){
					if(data > 0){
						var oDetails = new Array();
						var user={
								id:userID,
								username:null,
								password:null,
								name:null,
								phine:null
						};
						$(".goodsMesDetails ul").each(function(){
							var goodsID = $(this).data("id");
							var price = $(this).find(".gPrice").text();
							var number = $(this).find(".gNum").text();
							var money = $(this).find(".gMoney").text();
							var goods = {
									id:goodsID,
									name:null,
									price:null,
									details:null,
									brand:null,
									genre:null,
									stock:null,
									salesVolume:null
							};
							var orderDetails={
									id:null,
									goods:goods,
									price:price,
									number:number,
									money:money
							};
							oDetails.push(orderDetails);
						})
						var orders = {
								id:data,
								user:user,
								orderNUM:null,
								position:null,
								total:null,
								timeType:null,
								orderDetails:oDetails
						};
						$.ajax({
							url:"addOrderDetails.do",
							type:"post",
							dataType:"text",
							contentType:"application/json",
							data:JSON.stringify(orders),
							success:function(data){
								if(data == "true"){
									alert("添加订单成功");
									location.href="order.do";
								}
							}
						})
					}
				}
			})
		}	
	})
})
</script>
</head>
<body>
<div id="top">
	<ul id="top_left" class="topClass">
		<li>中国大陆</li>
		<c:if test="${username == null}">
			<li id="load" style="color:red;font-width:bord">请登录</li>
		</c:if>
		<li>${username }</li>
		<li>消息</li>
	</ul>
	<ul id="top_right" class="topClass">
		<li><a href="index.do">商城首页</a></li>
		<li><a href="order.do">我的订单</a></li>
		<li><a href="cart.do?id=${cartID}">购物车</a></li>
		<li><a href="">收藏夹</a></li>
		<li><a href="">商品分类</a></li>
		<li><a href="">卖家中心</a></li>
		<li><a href="">练习客服</a></li>
		<li><a href="">网站导航</a></li>
	</ul>
</div>
<div id="header">
	<div id="headers">
		<div id="header_logo">
			<a href="index.html" title="淘宝网">
				<img src="img/logo.png" alt="淘宝网logo" style="height: 43px;" />
			</a>
		</div>
		<div id="header_text">添加订单</div>
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

<div id="addOrder">
	<div id="addOrders">
		<div id="addOrdersTop" ></div>
		<!--收货地址body部分开始-->
		<div id="position">
			<div id="positions">
				<div class="addOrderTop">
					<h2 class="title">收货地址</h2>
				</div>
				<div id="positionList">
					<ul>
						<c:forEach items="${positionList }" var="item">
						<li class="myPosition">
							<input type="hidden" name="posID" value="${item.id}"/>
							<p class="itemName">${item.name }</p>
							<p class="itemTel">${item.phone }</p>
							<p class="itemRegion">${item.area.city.province.province } ${item.area.city.city } ${item.area.area }</p>
							<p class="itemStreet">${item.position }</p>
							<span>编辑</span>
						</li>
						</c:forEach>
					</ul>
					<div id="addNewPosition">
						<div id="addNewPositions">
							<span><img src="img/add_cart.png" /></span>
							使用新地址
						</div>
					</div>
				</div>
				<div id="newPosition">
					<input type="hidden" name="userID" value="${userID}"/>
					<div>
						<input class="input" type="text" name="name" placeholder="收货人姓名"/>
						<input class="input" type="text" name="phone" placeholder="收货人联系方式"/>
					</div>
					<div>
						<select class="select province">
							<option value="">请选择所在省份</option>
							<c:forEach items="${proList}" var="province">
								<option value="${province.provinceID}">${province.province}</option>
							</c:forEach>
						</select>
		
						<select class="select city">
							<option value="">请选择所在城市</option>
						</select>
	
						<select class="select area" name="areaID">
							<option value="">请选择所在地区</option>
						</select>
					</div>
					<div>
						<input class="input" type="text" name="position" placeholder="道路、街道、门牌号"/>
					</div>
					<div id="addBtn">
						<p id="no">取消</p>
						<button id="add">保存</button>
					</div>
				</div>
			</div>
		</div>
		<div id="buyMethod">
		<!-- 支付方式 -->
			<div id="buyMethods">
				<div class="addOrderTop">
					<h2 class="title">支付方式</h2>
				</div>
				<div class="method">
					<div class="selectMethod methods">
						在线支付
					</div> 
				</div>
			</div>
		</div>
		<div id="goMethod">
		<!-- 配送方式 -->
			<div id="goMethods">
				<div class="addOrderTop">
					<h2 class="title">配送方式</h2>
				</div>
				<div class="method">
					<div class="selectMethod methods">
						快递配送（免运费）
					</div> 
				</div>
			</div>
		</div>
		<div id="faPiaoMethod">
		<!-- 发票信息 -->
			<div id="faPiaoMethods">
				<div class="addOrderTop">
					<h2 class="title">发票信息</h2>
				</div>
				<div class="method">
					<div class="selectMethod methods">
						电子发票（非纸质）
					</div> 
					<div class="methods">
						普通发票（纸质）
					</div> 
				</div>
			</div>
		</div>
		<div id="sureOrderMes">
		<!-- 确认订单信息 -->
			<div id="sureOrderMess">
				<div class="addOrderTop">
					<h2 class="title">确认订单信息</h2>
				</div>
				<div class="goodsMes">
					<div class="goodsMesTop">
						<ul>
							<li id="goodsDetails">商品名称</li>
							<li>购买价格</li>
							<li>购买数量</li>
							<li>小计（元）</li>
						</ul>
					</div> 
					<div class="goodsMesDetails">
						<c:forEach items="${goodsList }" var="goods" varStatus="status">
						<ul data-id="${goods.id}">
							<li id="goodsDetails2">
								<div>
									<c:forEach items="${goods.goodsImgs }" var ="img">
										<img style="width:40px;height:40px;margin-top:5px" src="goodsImg/${img.name}" />
									</c:forEach>
								</div>
								<div style="margin-left:20px;">${goods.name}</div>
							</li>
							<li class="gPrice">${goods.price}</li>
							<li class="gNum">
							<c:forEach items="${numList}" var="num" varStatus="statusNUM">
							<c:if test="${status.index == statusNUM.index}">${num}</c:if>
							</c:forEach>
							</li>
							<li class="gMoney" style="color:#f40"><c:forEach items="${numList}" var="nums" varStatus="statusNUMs"><c:if test="${status.index == statusNUMs.index}"><fmt:formatNumber type="number" value="${nums*goods.price}" pattern="0.00" maxFractionDigits="2"/></c:if></c:forEach></li>
						</ul>
						</c:forEach>
					</div> 
				</div>
			</div>
		</div>
		<div class="orderBottom">
			<ul>
				<li>订单总额：</li>
				<li class="money">￥${total }</li>
				<li>活动优惠：</li>
				<li class="money">￥0.00</li>
				<li>优惠券抵扣：</li>
				<li class="money">￥0.00</li>
				<li>运费：</li>
				<li class="money">￥0.00</li>
				<li>应付总额：</li>
				<li class="finalMoney">￥${total }</li>
			</ul>
		</div>
		<div id="orderBtn">
			<button id="backCart">返回购物车</button>
			<button id="buyNow">立即下单</button>
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