$().ready(function(){
	var searchs_li_index = 0;
	$("#searchs li").click(function(){
		searchs_li_index = $(this).index();
		$("#searchs li").removeClass("search");
		$("#searchs li").eq(searchs_li_index).addClass("search");
	})
	
})
