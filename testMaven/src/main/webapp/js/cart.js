$().ready(function(){
	var choses_ul_index = 0;
	$(".choses li").click(function(){
		choses_ul_index = $(this).index();
		$(".choses li").removeClass("select");
		$(".choses li").eq(choses_ul_index).addClass("select");
	})
	
	
	$(".goods_div").hover(function(){
		$(".p7").css("display","inline")
	},function(){
		$(".p7").css("display","none")
	})
})