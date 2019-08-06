<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="css/load.css" />
<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="js/load.js" type="text/javascript" charset="utf-8"></script>
<style type="text/css">
#error{
	color: red;
	display: none;
	height: 15px;
	line-height: 15px;
	font-size: 1px;
}
</style>
<script type="text/javascript">
$().ready(function(){
	if(${error} == error){
		$("#error").show(200);
	}
	$("[name = password]").focus(function () {
		$("#error").hide(200);
	})
})
</script>
</head>
<body>
	<div class="top">
		<div class="tops">
			<img src="img/logo.png" style="height: 42px;" />
			<div id="top_message">
				为确保您账户的安全及正常使用，依《网络安全法》相关要求，6月1日起会员账户需绑定手机。如您还未绑定，请尽快完成，感谢您的理解及支持！
			</div>
		</div>
	</div>
	<div class="load">
		<div class="loads">
			<div id="text">密码登录</div>
			<form action="load.do" method="post" enctype="application/x-www-form-urlencoded">
				<div><input type="text" id="name" name="username" value="${username }${name}" placeholder="会员名/邮箱号/手机号" /></div>
				<div>
					<input type="password" id="passwd" name="password" placeholder="请输入密码" />
					<p id="error">用户名或密码错误，请重新输入</p>
				</div>
				
				<div><button type="submit" id="btn">登录</button></div>
			</form>
			<div id="otherLoad">
				<ul>
					<li>微博登录</li>
					<li>支付宝登录</li>
				</ul>
			</div>
			<div id="other">
				<ul>
					<li>忘记密码</li>
					<li>忘记会员名</li>
					<li><a href="showRegister.do">免费注册</a></li>
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