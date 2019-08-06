package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Goods;
import entity.GoodsImg;
import service.GoodsImgService;
import service.GoodsService;

@Controller
public class GoodsController {

	@Autowired
	GoodsService goodsService;
	@Autowired
	GoodsImgService goodsImgService;

	@RequestMapping("goods")
	public ModelAndView select(int id,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("goods");
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		List<GoodsImg> imgList = goodsImgService.searchByGoods(id);
		Goods goods = goodsService.searchById(id);
		goods.setGoodsImgs(imgList);
		mv.addObject("goods", goods);
		mv.addObject("id", id);
		return mv;

	}
}
