package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Cart;
import entity.CartDetails;
import entity.GoodsImg;
import service.CartDetailsService;
import service.CartService;
import service.GoodsImgService;
import service.GoodsService;

@Controller
public class CartController {
	
	@Autowired
	CartService cartService;
	@Autowired
	CartDetailsService cartDetailsService;
	@Autowired
	GoodsService goodsService;
	@Autowired
	GoodsImgService goodsImgService;
	
	@RequestMapping("cart")
	public ModelAndView select(String id,HttpSession session) {
		if(session.getAttribute("userID") != null) {
			ModelAndView mv = new ModelAndView("cart");
			int ids = Integer.parseInt(id);
			List<CartDetails> cartDetails = cartService.search(ids);
			for(int i=0;i<cartDetails.size();i++) {
				int gID = cartDetails.get(i).getGoods().getId();
				List<GoodsImg> imgList = new ArrayList<>();
				GoodsImg img = goodsImgService.searchByID(gID);
				imgList.add(img);
				cartDetails.get(i).getGoods().setGoodsImgs(imgList);
			}
			Cart cart = cartService.searchCart(ids);
			mv.addObject("cart", cart);
			mv.addObject("cartDetails", cartDetails);
			return mv;
		}else {
			session.setAttribute("url", "cart");
			ModelAndView mv = new ModelAndView("load");
			return mv;
		}
	}
	
	@RequestMapping("addCartDetails")
	@ResponseBody
	public boolean addCartDetails(@RequestBody CartDetails cartDetails) {
		boolean flag = false;
		int num = cartDetailsService.searchNUM(cartDetails);
		if(num == 0) {
			flag = cartDetailsService.add(cartDetails);
		}else {
			cartDetails.setGoodsNUM(cartDetails.getGoodsNUM()+num);
			flag = cartDetailsService.update(cartDetails);
		}
		return flag;
	}
	@RequestMapping("updateCartDetails")
	@ResponseBody
	public boolean updateCartDetails(@RequestBody CartDetails cartDetails) {
		boolean flag = cartDetailsService.update(cartDetails);
		
		return flag;
	}
	@RequestMapping("delCartDetails")
	@ResponseBody
	public boolean delCartDetails(@RequestBody CartDetails cartDetails) {
		boolean flag = cartDetailsService.del(cartDetails);
		return flag;
	}
}
