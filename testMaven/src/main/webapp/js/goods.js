$().ready(function(){
	//图片轮播
	var length = $("#picture_show li").length;
	var index = 0;
	$("#picture_show li").fadeOut(500);
	$("#picture_show li").eq(index).fadeIn(500);
	function show(){
		$("#picture_show li").fadeOut(500);
		$("#picture_show li").eq(index).fadeIn(500);
		$("#picture_chose li").removeClass("select");
		$("#picture_chose li").eq(index).addClass("select");
	}
	function change(){
		if(index <length-1){
			index++;
		}else{
			index = 0;
		}
		show();
	}
	$("#picture_show").hover(function() {
		clearInterval(timer);
	}, function() {
		timer = setInterval(change, 2000);
	})
	$("#picture_chose li").click(function(){
		index = $(this).index();
		show();
	})
	var timer = setInterval(change,2000);


	//图片放大
	
})