<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script type="text/javascript">

</script>
<title>Insert title here</title>
</head>
<body>

<div id="top">
	<input type="hidden" name="d_id" value="${d_id}">
	<label name="d_name" class="col-sm-2 control-label">${d_name}</label>	
</div>
<div>

	<div id="have">
	
	<label>已选</label>
	</div>
	<div>
		<button>加入</button>
		<button>移除</button>
	</div>
	<div id="noHave">
	
	<label>未选</label>
	</div>
</div>
</body>
</html>