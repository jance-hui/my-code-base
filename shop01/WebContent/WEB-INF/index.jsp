<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<title>首页</title>
<link rel="stylesheet" type="text/css" href="css/home.css" />
<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="js/home.js" type="text/javascript" charset="utf-8"></script>
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
					<img src="img/logo.png" alt="淘宝网logo" />
				</a>
			</div>
			<div id="header_search">
				<div id="header_ul">
					<ul>
						<li class="select">宝贝</li>
						<li>天猫</li>
						<li>店铺</li>
					</ul>
				</div>
				<div id="header_search_form">
					<form action="select.do" name="search">
						<div>
							<input id="search_box" type="text" name="name" placeholder="请输入关键字"/>
						</div>
						<div id="search_btns">
							<button type="submit" id="search_taoBao">搜 索</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="menu_top">
		<div id="menu_tops">
			<div id="menu_top_1">主题市场</div>
			<div id="menu_top_2">
				<ul id="menu_top_ul">
					<li><a href="">天猫</a></li>
					<li><a href="">聚划算</a></li>
					<li><a href="">天猫超市</a></li>
					<li><a href="">淘抢购</a></li>
					<li><a href="">电器城</a></li>
					<li><a href="">司法拍卖</a></li>
					<li><a href="">中国质造</a></li>
					<li><a href="">兴农扶贫</a></li>
					<li><a href="">飞猪旅行</a></li>
					<li><a href="">智能生活</a></li>
					<li><a href="">苏宁易购</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="menu">
		<div id="menus">
			<div id="menu_main">
				<ul>
					<c:forEach items="${brand}" var="brand">
						<li data-id="${brand.id}">
							<a href="select.do?brand.id=${brand.id}&brand.name=${brand.name}<c:if test="${goods.genre!=null and goods.genre.id!=null and goods.genre.id!=''}">&genre.id=${goods.genre.id}&genre.name=${goods.genre.name}</c:if>">${brand.name}</a>
						</li>
					</c:forEach>
					<li>——————————</li>
					<c:forEach items="${genre}" var="genre">
						<li data-id="${genre.id}">
							<a href="select.do?genre.id=${genre.id}&genre.name=${genre.name}<c:if test="${goods.brand!=null and goods.brand.id!=null and goods.brand.id!=''}">&brand.id=${goods.brand.id}&brand.name=${goods.brand.name}</c:if>">${genre.name}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div id="picture">
				<div id="picture_1">
					<div id="picture_1_show" class="picture_show">
						<ul>
							<li><img src="goodsImg/home_1.jpg" /></li>
							<li><img src="goodsImg/home_2.jpg" /></li>
							<li><img src="goodsImg/home_3.jpg" /></li>
							<li><img src="goodsImg/home_4.jpg" /></li>
							<li><img src="goodsImg/home_5.jpg" /></li>
						</ul>
					</div>
					<div id="picture_1_chose" class="picture_chose">
						<ul>
							<li class="selects">&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
						</ul>
					</div>
				</div>
				<div id="picture_2">
					<div id="tianmao">
						<img src="img/天猫.png" />
					</div>
					<div id="picture_2_chose" class="picture_chose">
						<ul>
							<li class="selectss">&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
						</ul>
					</div>
					<div id="picture_2_show" class="picture_show">
						<ul>
							<li><img src="goodsImg/home图片2_1.png" /></li>
							<li><img src="goodsImg/home图片2_2.png" /></li>
							<li><img src="goodsImg/home图片2_3.png" /></li>
							<li><img src="goodsImg/home图片2_4.png" /></li>
							<li><img src="goodsImg/home图片2_5.png"></li>
							<li><img src="goodsImg/home图片2_6.png" /></li>
						</ul>
					</div>
				</div>
			</div>
			<div id="guanggao">
				<img id="guanggao_1" src="//img.alicdn.com/tfs/TB1FYa5omYTBKNjSZKbXXXJ8pXa-160-280.jpg_290x290q90.jpg_.webp" />
				<div>
					<h5>今日热卖</h5>
					<img id="guanggao_2" src="goodsImg/广告_2.jpg" />
				</div>
			</div>
			<div id="my_message">
				<div id="load">
					<div id="touxiang">
						<img src="img/头像.jpg" />
					</div>
					<div id="anniu">
						<ul>
							<li>登录</li>
							<li>注册</li>
							<li>开店</li>
						</ul>
					</div>
				</div>
				<div id="message1">网上有害信息举报专区-></div>
				<div id="message2">
					<ul>
						<li>公告</li>
						<li>规则</li>
						<li>论坛</li>
						<li>安全</li>
						<li>公益</li>
					</ul>
				</div>
				<div id="message3">
					<ul>
						<li>
							<span></span>
							<p>充话费</p>
						</li>
						<li>
							<span></span>
							<p>旅行</p>
						</li>
						<li>
							<span></span>
							<p>车险</p>
						</li>
						<li>
							<span></span>
							<p>游戏</p>
						</li>
						<li>
							<span></span>
							<p>彩票</p>
						</li>
						<li>
							<span></span>
							<p>电影</p>
						</li>
						<li>
							<span></span>
							<p>酒店</p>
						</li>
						<li>
							<span></span>
							<p>理财</p>
						</li>
						<li>
							<span></span>
							<p>找服务</p>
						</li>
						<li>
							<span></span>
							<p>演出</p>
						</li>
						<li>
							<span></span>
							<p>水电煤</p>
						</li>
						<li>
							<span></span>
							<p>火车票</p>
						</li>
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
				<div>
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
				</div>
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