package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Brand;
import entity.Genre;
import entity.Goods;
import entity.GoodsImg;
import service.BrandService;
import service.GenreService;
import service.GoodsImgService;
import service.GoodsService;
import util.Constant;
import util.Pagination;

@Controller
public class SelectController {
	
	@Autowired
	BrandService brandService;
	@Autowired
	GenreService generService;
	@Autowired
	GoodsService goodsService;
	@Autowired
	GoodsImgService goodsImgService;
	
	@RequestMapping("select")
	public ModelAndView select(Goods goods ,String ye) {
		ModelAndView mv = new ModelAndView("select");
		List<Brand> bList = brandService.search();
		List<Genre> gList = generService.search();
		int page = 1;
		int count = goodsService.searchCountByGoods(goods);
		if (ye != null) {
			page = Integer.parseInt(ye);
		}
		int allGoods = goodsService.searchAll();
		Pagination p = new Pagination(page, count, Constant.GOODS_NUM_IN_PAGE, Constant.GOODS_NUM_OF_PAGE);
		List<Goods> goodsList = goodsService.searchByGoods(goods,p.getBegin(),Constant.GOODS_NUM_IN_PAGE);
		for(int i=0;i<goodsList.size();i++) {
			List<GoodsImg> gImgList = new ArrayList<>();
			GoodsImg img =goodsImgService.searchByID(goodsList.get(i).getId());
			gImgList.add(img);
			goodsList.get(i).setGoodsImgs(gImgList);
		}
		mv.addObject("brand", bList);
		mv.addObject("genre", gList);
		mv.addObject("gList", goodsList);
		mv.addObject("goods", goods);
		mv.addObject("p", p);
		mv.addObject("allGoods", allGoods);
		return mv;
	}
	
	@RequestMapping("index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		List<Brand> bList = brandService.search();
		List<Genre> gList = generService.search();
		mv.addObject("brand", bList);
		mv.addObject("genre", gList);
		return mv;
	}
}
