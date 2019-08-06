<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
<link rel="stylesheet" type="text/css" href="css/register.css" />
<script src="js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script src="js/register.js" type="text/javascript" charset="utf-8"></script>
</head>

<body>
	<div class="top">
		<div class="tops">
			<div id="logo"><img src="img/logo.png" style="height: 45px;" /></div>
			<div id="text">用户注册</div>
		</div>
	</div>
	<div class="regist">
		<div class="regists">
			<form action="register.do" method="post">
			<div id="message">
				<ul>
					<li class="kind">
						<p style="text-indent: 1em;">用户名:</p>
						<input type="text" id="username" name="username" class="box" placeholder="请输入用户名" />
						<ul id="username_find">
							<li class="show">请输入您的用户名</li>
							<li class="error">用户名输入错误，请重新输入（只可包含中文）</li>
							<li class="true">格式正确</li>
						</ul>
					</li>
					<li class="kind">
						<p style="text-indent: 1em;">手机号:</p>
						<input type="text" id="tel" name="phone" class="box" placeholder="请输入手机号" />
						<ul id="tel_find">
							<li class="show">请输入您的手机号</li>
							<li class="error">手机号输入错误，请输入正确手机号码</li>
							<li class="true">格式正确</li>
						</ul>
					</li>
					<li class="kind">
						<p>真实姓名:</p>
						<input type="text" id="name" class="box" name="name" placeholder="请输入您的真实姓名" />
						<ul id="name_find">
							<li class="show">请输入您的真实姓名</li>
							<li class="error">姓名输入错误，请输入汉字</li>
							<li class="true">格式正确</li>
						</ul>
					</li>
					<li class="kind">
						<p style="text-indent: 2em;">密码:</p>
						<input type="password" id="pwd" class="box" name="password" placeholder="请输入密码" />
						<ul id="pwd_find">
							<li class="show">请设置您的密码</li>
							<li class="error">密码需包含大写字母，请重新输入</li>
							<li class="true">格式正确</li>
						</ul>
					</li>
					<li class="kind">
						<p>确认密码:</p>
						<input type="password" id="pwds" name="passwords" class="box" placeholder="请确认密码" />
						<ul id="pwds_find">
							<li class="show">请确认密码</li>
							<li class="error">密码不同，请重新输入</li>
							<li class="true">密码正确</li>
						</ul>
					</li>
				</ul>
			</div>
			<div><button id="button" type="submit">完成注册</button></div>
			</form>
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