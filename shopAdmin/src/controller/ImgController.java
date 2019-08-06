package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Goods;
import entity.GoodsImg;
import service.GoodsImgService;
import service.GoodsService;


@Controller
public class ImgController {
	@Autowired
	GoodsService goodsService;
	@Autowired
	GoodsImgService goodsImgService;

	@RequestMapping("advertising")
	public ModelAndView advertising(String id) {
		ModelAndView mv = new ModelAndView("advertising");
		List<Goods> goodsList = new ArrayList<>();
		List<Goods> goodsList2 = goodsService.search();
		List<GoodsImg> imgList = new ArrayList<>();
		if(id == null) {
			goodsList = goodsService.search();
			for(int i=0;i<goodsList.size();i++) {
				imgList = goodsImgService.searchByGoods(goodsList.get(i).getId());
				goodsList.get(i).setGoodsImgs(imgList);;
			}
		}else {
			Goods goods = goodsService.searchById(Integer.parseInt(id));
			imgList = goodsImgService.searchByGoods(goods.getId());
			goods.setGoodsImgs(imgList);;
			goodsList.add(goods);
		}
		mv.addObject("goodsList", goodsList);
		mv.addObject("goodsList2", goodsList2);
		return mv;
	}
	
	@RequestMapping("Sort_ads")
	public ModelAndView Sort_ads() {
		ModelAndView mv = new ModelAndView("Sort_ads");
		List<Goods> goodsList = goodsService.search();
		for(int i=0;i<goodsList.size();i++) {
			List<GoodsImg> imgList = goodsImgService.searchByGoods(goodsList.get(i).getId());
			goodsList.get(i).setGoodsImgs(imgList);;
		}
		mv.addObject("goodsList", goodsList);
		return mv;
	}
}
