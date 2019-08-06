$().ready(function() {
	var time;
	$(".diqu a").hover(function() {
		clearTimeout(timer);
		$(".xinxi").show();
	}, function() {
		time = setTimeout(function() {
			$(".xinxi").hide();
		}, 0)
	});
	$(".xinxi ul").hover(function() {
		clearTimeout(time);
	}, function() {
		$(".xinxi").hide()
	});

	var hearder_ul_index = 0;
	$("#header_ul li").click(function() {
		hearder_ul_index = $(this).index();
		$("#header_ul li").removeClass("select");
		$("#header_ul li").eq(hearder_ul_index).addClass("select");
	})

	//图片轮播
	var length = $("#picture_1_show li").length;
	var index = 0;
	$("#picture_1_show li").fadeOut(500);
	$("#picture_1_show li").eq(index).fadeIn(500);

	function show() {
		$("#picture_1_show li").fadeOut(500);
		$("#picture_1_show li").eq(index).fadeIn(500);
		$("#picture_1_chose li").removeClass("selects");
		$("#picture_1_chose li").eq(index).addClass("selects");
	}

	function change() {
		if(index < length - 1) {
			index++;
		} else {
			index = 0;
		}
		show();
	}
	$("#picture_1_show").hover(function() {
		clearInterval(timer);
	}, function() {
		timer = setInterval(change, 2000);
	})
	$("#picture_1_chose li").click(function() {
		index = $(this).index();
		show();
	})
	var timer = setInterval(change, 2000);

	var lengths = $("#picture_1_show li").length;
	var indexs = 0;
	$("#picture_2_show li").fadeOut(800);
	$("#picture_2_show li").eq(index).fadeIn(800);

	function shows() {
		$("#picture_2_show li").fadeOut(800);
		$("#picture_2_show li").eq(index).fadeIn(800);
		$("#picture_2_chose li").removeClass("selectss");
		$("#picture_2_chose li").eq(index).addClass("selectss");
	}

	function changes() {
		if(index < length - 1) {
			indexs++;
		} else {
			indexs = 0;
		}
		shows();
	}
	$("#picture_2_show").hover(function() {
		clearInterval(timers);
	}, function() {
		timers = setInterval(changes, 3000);
	})
	$("#picture_2_chose li").click(function() {
		indexs = $(this).index();
		shows();
	})
	var timers = setInterval(changes, 3000);
})