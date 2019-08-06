<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>查询</title>
		<link rel="stylesheet" type="text/css" href="css/select.css"/>
		<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
		<style type="text/css">
		#page {
			width: 100%;
			height:80px;
			margin-left:6%;
		}

		#pages {
			width:800px;
			margin: auto;
			float: left;
		}

		#message {
			float: left;
			margin-top: 30px;
			margin-left: 15px;
			color: #ccc;
			font-size: 14px;
		}
		</style>
		<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/select.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
		$().ready(function(){
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
			
			if($(".else_message_name").val() != null){
				$(".else_message_name").hover(function(){
					var aa ="<a href='select.do?ye=${p.ye}&brand.id=<c:forEach items='${brand}' var='brand'><c:if test='${goods.brand.id==brand.id}'>${brand.id}&brand.name=${brand.name}</c:if></c:forEach>"; 
					aa+="&genre.id=<c:forEach items='${genre}' var='genre'><c:if test='${goods.genre.id==genre.id}'>${genre.id}&genre.name=${genre.name}</c:if></c:forEach>'>取消</a>";
					$(this).html(aa);
				},function(){
					$(this).html("${goods.name}");
				})
			}
			if($(".else_message_brand").val() != null){
				$(".else_message_brand").hover(function(){
					var aa ="<a href='select.do?ye=${p.ye}&name=${goods.name}&genre.id=<c:forEach items='${genre}' var='genre'><c:if test='${goods.genre.id==genre.id}'>${genre.id}&genre.name=${genre.name}</c:if></c:forEach>'>取消</a>";
					$(this).html(aa);
				},function(){
					$(this).html("${goods.brand.name}");
				})
			}
			if($(".else_message_genre").val() != null){
				$(".else_message_genre").hover(function(){
					var aa ="<a href='select.do?ye=${p.ye}&name=${goods.name}&brand.id=<c:forEach items='${brand}' var='brand'><c:if test='${goods.brand.id==brand.id}'>${brand.id}&brand.name=${brand.name}</c:if></c:forEach>'>取消</a>";
					$(this).html(aa);
				},function(){
					$(this).html("${goods.genre.name}");
				})
			}
			$("#goodss li").click(function(){
				var id = $(this).data("id");
				location.href="goods.do?id="+id;
			})
		})
		</script>
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
			<div id="headers">
				<div id="header_logo">
					<a href="index.html" title="淘宝网">
						<img src="img/logo.png" alt="淘宝网logo" style="height: 43px;" />
					</a>
				</div>
				<div id="header_search">
					<div id="header_search_form">
						<form action="select.do" method="post">
							<div>
								<input id="search_box" type="text" name="name" value="${goods.name }" placeholder="请输入关键字" />
							</div>
							<div id="search_btns">
								<button id="search_taoBao" type="submit">搜 索</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="search">
			<div id="searchs">
				<ul>
					<li class="search">所有宝贝</li>
					<li>天猫</li>
					<li>二手</li>
				</ul>
			</div>
		</div>
		<div id="guanggao">
			<div id="guanggaos">
				<img src="img/select广告.png" />
			</div>
		</div>
		<div id="specific">
			<div id="specifics">
				<div id="specifics_top">
					<span>所有分类： 共</span>
					<span class="span_red">${allGoods}</span>
					<span>件商品</span>
				</div>
				<div id="specifics_message">
					<ul>
						<li>
							<div class="main_name" >
								<p>品牌：</p>
							</div>
							<div class="main_classify">
								<ul>
									<c:forEach items="${brand}" var="brand">
									<li data-id="${brand.id}">
									<a href="select.do?ye=${p.ye}&brand.id=${brand.id}&brand.name=${brand.name}<c:if test="${goods.genre!=null and goods.genre.id!=null and goods.genre.id!=''}">&genre.id=${goods.genre.id}&genre.name=${goods.genre.name}</c:if>">${brand.name}</a>
									</li>
									</c:forEach>
								</ul>
							</div>
						</li>
						<li>
							<div class="main_name">
								<p>选购热点：</p>
							</div>
							<div class="main_classify">
								<ul>
									<c:forEach items="${genre}" var="genre">
									<li data-id="${genre.id}">
									<a href="select.do?ye=${p.ye}&genre.id=${genre.id}&genre.name=${genre.name}<c:if test="${goods.brand!=null and goods.brand.id!=null and goods.brand.id!=''}">&brand.id=${goods.brand.id}&brand.name=${goods.brand.name}</c:if>">${genre.name}</a>
									</li>
									</c:forEach>
								</ul>
							</div>
						</li>
					</ul>
				</div>
				<div id="else_message">
					<div>
						<p>已选：</p>
					</div>
					<div>
						<ul>
						<c:if test="${goods.name!=null and goods.name!=''}">
							<li class="else_message_name">
							${goods.name}
							</li>
						</c:if>
						<c:if test="${goods.brand!=null and goods.brand.name!=null and goods.brand.name!=''}">
							<li class="else_message_brand">
								${goods.brand.name}
							</li>
						</c:if>
						<c:if test="${goods.genre!=null and goods.genre.name!=null and goods.genre.name!=''}">
							<li class="else_message_genre">
								${goods.genre.name}
							</li>
						</c:if>
						</ul>
					</div>
				</div>
				<div id="sort">
					<div id="sort1">
						<ul>
							<li class="select">综合排序</li>
							<li>销量</li>
							<li>信用</li>
						</ul>
					</div>
					<div id="sort2">
						<input type="checkbox" name="包邮" />包邮
						<input type="checkbox" name="赠送退货运费险" />赠送退货运费险
						<input type="checkbox" name="货到付款" />货到付款
						<input type="checkbox" name="二手" />二手
						<input type="checkbox" name="正品保障" />正品保障
						<input type="checkbox" name="天猫" />天猫
						<input type="checkbox" name="公益宝贝" />公益宝贝
					</div>
				</div>
			</div>
		</div>
		<div id="goods">
			<div id="goodss">
				<ul>
				<c:forEach items="${gList }" var="goods">
					<li data-id="${goods.id }">
						<div class="goods_img">
						<c:forEach items="${goods.goodsImgs}" var="gImg">
							<img style="width:228px;height:230px" src="goodsImg/${gImg.name}" />
						</c:forEach>
						</div>
						<div class="goods_money">
							<div class="money">￥${goods.price }</div>
							<div class="fukuan">${goods.salesVolume }人付款</div>
						</div>
						<div class="goods_mes">${goods.details }</div>
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>
		<div id="page">
			<div id="pages">
				<ul class="pagination">
					<li id="begin">
						<a href="select.do?ye=1&name=${goods.name}&brand.id=<c:forEach items='${brand}' var='brand'><c:if test='${goods.brand.id==brand.id}'>${brand.id}&brand.name=${brand.name}</c:if></c:forEach>&genre.id=<c:forEach items='${genre}' var='genre'><c:if test='${goods.genre.id==genre.id}'>${genre.id}&genre.name=${genre.name}</c:if></c:forEach>">首页</a>
					</li>
					
					<li id="pre">
						<a href='select.do?ye=${p.ye-1}&name=${goods.name}&brand.id=<c:forEach items='${brand}' var='brand'><c:if test='${goods.brand.id==brand.id}'>${brand.id}&brand.name=${brand.name}</c:if></c:forEach>&genre.id=<c:forEach items='${genre}' var='genre'><c:if test='${goods.genre.id==genre.id}'>${genre.id}&genre.name=${genre.name}</c:if></c:forEach>'>上一页</a>
					</li>
					
					<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
						<li <c:if test="${p.ye == status.index}">class="active"</c:if>>
							<a href='select.do?ye=${status.index}&name=${goods.name}&brand.id=<c:forEach items='${brand}' var='brand'><c:if test='${goods.brand.id==brand.id}'>${brand.id}&brand.name=${brand.name}</c:if></c:forEach>&genre.id=<c:forEach items='${genre}' var='genre'><c:if test='${goods.genre.id==genre.id}'>${genre.id}&genre.name=${genre.name}</c:if></c:forEach>'>${status.index}</a>
						</li>
					</c:forEach>
					
					<li id="next">
						<a href='select.do?ye=${p.ye+1}&name=${goods.name}&brand.id=<c:forEach items='${brand}' var='brand'><c:if test='${goods.brand.id==brand.id}'>${brand.id}&brand.name=${brand.name}</c:if></c:forEach>&genre.id=<c:forEach items='${genre}' var='genre'><c:if test='${goods.genre.id==genre.id}'>${genre.id}&genre.name=${genre.name}</c:if></c:forEach>'>下一页</a>
					</li>
					
					<li id="end">
						<a href='select.do?ye=${p.maxYe}&name=${goods.name}&brand.id=<c:forEach items='${brand}' var='brand'><c:if test='${goods.brand.id==brand.id}'>${brand.id}&brand.name=${brand.name}</c:if></c:forEach>&genre.id=<c:forEach items='${genre}' var='genre'><c:if test='${goods.genre.id==genre.id}'>${genre.id}&genre.name=${genre.name}</c:if></c:forEach>'>末页</a>
					</li>
				</ul>
			</div>
			<div id="message">
				<p>共${p.maxYe}页</p>
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