<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/order.css"/>
<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$().ready(function(){
	
})
</script>
<title>订单页</title>
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
		<div id="header_text">我的订单</div>
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
		
<div class="order">
	<div class="orders">
		<div class="ordersTop">
			<div id="text">我的订单</div>
			<div id="search">
				<input type="text" placeholder="订单编号"/>
				<button>搜索</button>
			</div>
		</div>
		<div class="ordersMesTop">
			<ul>
				<li id="ordersMes">订单信息</li>
				<li>收货人</li>
				<li>订单金额</li>
				<li>订单时间</li>
				<li>订单状态</li>
				<li>订单操作</li>
			</ul>
		</div>
		<div class="myOrder">
			<c:forEach items="${ordersList }" var="orders">
			<div class="myOrderTop">
				<div  class="myOrderNUM">${orders.timeType}</div>
				<div  class="myOrderTimeText">订单号</div>
				<div  class="myOrderTime">${orders.orderNUM }</div>
			</div>
			<div class="myOrderDetails">
				<ul>
					<li class="myOrderDetailsMes">
						<c:forEach items="${orders.orderDetails }" var="orderDetails">
						<div class="goodsMes">
							<c:forEach items="${orderDetails.goods.goodsImgs }" var="img">
								<img src="goodsImg/${img.name }" style="height: 50px;margin-top:5px;margin-left:30px">
							</c:forEach>
							<div class="goodsName">${orderDetails.goods.name}</div>
							<div class="goodsNum">X ${orderDetails.number}</div>
						</div>
						</c:forEach>
					</li>
					<li>${orders.position.name}</li>
					<li>${orders.total}</li>
					<li>${orders.timeType}</li>
					<li>订单状态</li>
					<li>订单操作</li>
				</ul>
			</div>
			</c:forEach>
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