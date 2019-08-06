$().ready(function() {

	var flag = null;
	$("#name").on("blur",function(){
		var name = $("#name").val();
		var name_text = /^[\u4e00-\u9fa5]+$/i;
		if(name_text.test(name)) {
			flag =flag && true;
			$("#name_find .show").css("display", "none");
			$("#name_find .error").css("display", "none");
			$("#name_find .true").css("display", "inline");
		} else {
			flag =flag &&  false;
			$("#name_find .show").css("display", "none");
			$("#name_find .error").css("display", "inline");
		}
	})
	$("#tel").on("blur",function(){
		var tel = $("#tel").val();
		var tel_text = /^1[34578]\d{9}$/;
		if(tel_text.test(tel)) {
			flag =flag && true;
			$("#tel_find .show").css("display", "none");
			$("#tel_find .error").css("display", "none");
			$("#tel_find .true").css("display", "inline");
		} else {
			flag =flag &&  false;
			$("#tel_find .show").css("display", "none");
			$("#tel_find .error").css("display", "inline");
		}
	})	
	$("#pwd").on("blur",function(){
		var pwd = $("#pwd").val();
		var pwd_text = /^\w*[A-Z]+\w*$/;
		if(pwd_text.test(pwd)) {
			flag =flag && true;
			$("#pwd_find .show").css("display", "none");
			$("#pwd_find .error").css("display", "none");
			$("#pwd_find .true").css("display", "inline");
		} else {
			flag =flag &&  false;
			$("#pwd_find .show").css("display", "none");
			$("#pwd_find .error").css("display", "inline");
		}
	})
	$("#pwds").on("blur",function(){
		if($("#pwds").val() == $("#pwd").val()) {
			flag =flag && true;
			$("#pwds_find .show").css("display", "none");
			$("#pwds_find .error").css("display", "none");
			$("#pwds_find .true").css("display", "inline");
		} else {
			flag =flag &&  false;
			$("#pwds_find .show").css("display", "none");
			$("#pwds_find .error").css("display", "inline");
		}
	})

})