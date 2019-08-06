<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品页</title>
		<link rel="stylesheet" type="text/css" href="css/goods.css"/>
		<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/jquerySession.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/goods.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		$().ready(function(){
			
			$("#message_8_2").click(function(){
				if("${username}" != "" || "${cartID}" != ""){
					var cartID = $(this).data("id");
					var goodsNUM = $("#select").val();
					var goodsID = ${goods.id};
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
						url:"addCartDetails.do",
						type:"post",
						dataType:"text",
						contentType:"application/json",
						data:JSON.stringify(cartDetails),
						success:function(data){
							if(data == "true"){
								var aa = "<a href='cart.do?id=${cartID}' title='点击进入购物车。'>已加入购物车</a>"
								$("#message_8_2").empty();
								$("#message_8_2").html(aa);
								$("#message_8_2").attr("disabled","disabled");
							}
						}
					})
				}else{
					location.href="showLoad.do";	
				}
			})
			$("#load").click(function(){
				location.href="showLoad.do";
			})
			$("#message_8_1").click(function(){
				if("${username}" != "" || "${userID}" != ""){
					var goodsNUM = $("#select").val();
					var goodsID = ${goods.id};
					location.href="showAddOrder.do?goodsID="+goodsID+"&goodsNUM="+goodsNUM;	
				}else{
					location.href="showLoad.do";	
				}
			})
		})
		</script>
	</head>
<body>

	<%
	int id = (int)session.getAttribute("id");
	String url = "goods.do?id="+id;
	session.setAttribute("url",url);
	session.removeAttribute("id");
	%>
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
				<li>
					<a href="index.do">商城首页</a>
				</li>
				<li><a href="order.do">我的订单</a></li>
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
			<div class="header_logo">
				<a href="index.html" title="淘宝网">
					<img src="img/logo.png" alt="淘宝网logo" style="height: 38px;" />
				</a>
			</div>
			<div class="header_search">
				<form action="select.do" method="post">
					<div>
						<input type="text" class="search_box" name="name" placeholder="请输入关键字" />
					</div>
					<div>
						<button type="submit" class="search_taoBao">搜淘宝</button>
					</div>
				</form>
			</div>
		</div>
		<div id="shop_message">
			<div id="signboard">
				<img src="img/店铺招牌.jpg" />
			</div>
			<div id="navigation">
				<ul>
					<li>所有宝贝</li>
					<li>首页</li>
					<li>爆款热卖</li>
					<li>键鼠单品</li>
					<li>键鼠套装</li>
					<li>机械键盘专区</li>
					<li>周边配件</li>
					<li>收藏店铺</li>
				</ul>
			</div>
		</div>
		<div id="detail">
			<div id="details">
				<div id="picture">
					<div id="picture_show">
						<ul>
							<c:forEach items="${goods.goodsImgs}" var="img">	
								<li><img src="goodsImg/${img.name }" /></li>
							</c:forEach>
						</ul>
					</div>
					<div id="picture_chose" style="margin-top: 15px;">
						<ul>
							<c:forEach items="${goods.goodsImgs}" var="img">	
								<li <c:if test="${img.sort==1}"> class='select'</c:if> ><img src="goodsImg/${img.name }" style="height: 50px;"/></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div id="message">
					<p id="message_p1">${goods.name }</p>
					<p id="message_p2">${goods.details }</p>
					<div id="message_2">
						<dl id="message_2_p1">
							<dt>价格</dt>
							<dd>￥ <fmt:formatNumber type="number" value="${goods.price+100.00}" pattern="0.00" maxFractionDigits="2"/></dd>
						</dl>
						<dl id="message_2_p2">
							<dt>促销价</dt>
							<dd>￥${goods.price }-<fmt:formatNumber type="number" value="${goods.price+40.00}" pattern="0.00" maxFractionDigits="2"/></dd>
						</dl>
					</div>
					<div id="message_3">
						<dl id="message_3_p">
							<dt>运费</dt>
							<dd>全国包邮</dd>
						</dl>
					</div>
					<div id="message_4">
						<ul>
							<li>
								<span class="message_4_span1">销量</span>
								<span class="message_4_span2">${goods.salesVolume }</span>
							</li>
							<li class="message_4_span1">|</li>
							<li>
								<span class="message_4_span1">累计评价</span>
								<span class="message_4_span2">8888</span>
							</li>
							<li class="message_4_span1">|</li>
							<li>
								<span class="message_4_span1">购买增送</span>
								<span class="message_4_span2">女朋友一个</span>
							</li>
						</ul>
					</div>
					<div id="message_5">
						<dl>
							<dt>套餐类型</dt>
							<dd>
								<ul>
									<li>
										<button>黑色</button>
									</li>
								</ul>
							</dd>
						</dl>
					</div>
					<div id="message_6">
						<dl>
							<dt>套餐类型</dt>
							<dd>
								<ul>
									<li>
										<button>官方标配</button>
									</li>
								</ul>
							</dd>
						</dl>
					</div>
					<div id="message_7">
						<dl>
							<dt>数量</dt>
							<dd>
								<span><select id="select">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
							</span>
								<span>件</span>
								<em>库存 ${goods.stock}件</em>
							</dd>
						</dl>
					</div>
					<div id="message_8">
						<button id="message_8_1" title="点击此按钮，到下一步确认购买信息。">立即购买</button>
						<button id="message_8_2" data-id="${cartID }">加入购物车</button>
					</div>
					<div id="message_9">
						<ul>
							<li class="message_9_1">服务承诺</li>
							<li class="message_9_2">正品保证</li>
							<li class="message_9_2">极速退款</li>
							<li class="message_9_2">七天无理由退换</li>
						</ul>
					</div>
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