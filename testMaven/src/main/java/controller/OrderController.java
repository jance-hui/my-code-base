package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import entity.Goods;
import entity.GoodsImg;
import entity.OrderDetails;
import entity.Orders;
import position.Position;
import position.Province;
import service.CartDetailsService;
import service.CartService;
import service.GoodsImgService;
import service.GoodsService;
import service.OrderDetailsService;
import service.OrdersService;
import service.PositionService;
import util.GenerateOrderNo;

@Controller
public class OrderController {

	@Autowired
	PositionService positionServise;
	@Autowired
	GoodsService goodsServise;
	@Autowired
	GoodsImgService goodsImgServise;
	@Autowired
	OrdersService ordersServise;
	@Autowired
	OrderDetailsService orderDetailsServise;
	@Autowired
	CartService cartServise;
	@Autowired
	CartDetailsService cartDetailsServise;

	@RequestMapping("showAddOrder")
	public ModelAndView showAddOrder(String goodsID,String goodsNUM,HttpSession session) {
		if(null == session.getAttribute("userID")) {
			ModelAndView mv = new ModelAndView("load");
			session.setAttribute("url", "addOrder.do");
			return mv;
		}else {
			String[] numbers = goodsNUM.split(",");
			List<Integer> numList = new ArrayList<>();
			for(int i=0;i<numbers.length;i++) {
				numList.add(Integer.parseInt(numbers[i]));
			}
			List<Goods> goodsList = goodsServise.searchByIds(goodsID);
			for(int i=0;i<goodsList.size();i++) {
				List<GoodsImg> imgList = new ArrayList<>();
				GoodsImg img = goodsImgServise.searchByID(goodsList.get(i).getId());
				imgList.add(img);
				goodsList.get(i).setGoodsImgs(imgList);
			}
			float total = 0;
			for(int i=0;i<goodsList.size();i++) {
				total += goodsList.get(i).getPrice()*numList.get(i);
			}
			/*String totals = String.valueOf(total);
			totals = totals.substring(0, ".".);*/
			float totals = (total*100)/100;
			
			int id = (int)session.getAttribute("userID");
			ModelAndView mv = new ModelAndView("addOrder");
			List<Position> positionList = positionServise.searchPosition(id);
			List<Province> proList = positionServise.searchPro();
			mv.addObject("proList", proList);
			mv.addObject("goodsList", goodsList);
			mv.addObject("numList", numList);
			mv.addObject("total", totals);
			mv.addObject("positionList", positionList);
			return mv;
		}
	}
	
	@RequestMapping("addOrder")
	@ResponseBody
	public String addOrder(@RequestBody Orders orders,HttpSession session) {
		if(null == session.getAttribute("userID")) {
			return "redirect:showLoad.do";
		}else {
			String orderNUM = GenerateOrderNo.generate();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String timeType = df.format(new Date());// new Date()为获取当前系统时间
			orders.setOrderNUM(orderNUM);
			orders.setTimeType(timeType);
			int id = ordersServise.add(orders);
			String ids = ""+id;
			return ids;
		}
	}
	
	@RequestMapping("addOrderDetails")
	@ResponseBody
	public String addOrderDetails(@RequestBody Orders orders,HttpSession session) {
		if(null == session.getAttribute("userID")) {
			return "redirect:showLoad.do";
		}else {
			boolean flag = false;
			for(int i=0;i<orders.getOrderDetails().size();i++) {
				flag = orderDetailsServise.add(orders.getOrderDetails().get(i),orders.getId());
				if(flag) {
					CartDetails cartDetails = new CartDetails();
					int userID = orders.getUser().getId();
					int goodsID = orders.getOrderDetails().get(i).getGoods().getId();
					int num = orders.getOrderDetails().get(i).getNumber();
					Cart cart = cartServise.searchByUID(userID);
					cartDetails.setCart(cart);
					Goods goods = new Goods();
					goods.setId(goodsID);
					cartDetails.setGoods(goods);
					flag = cartDetailsServise.del(cartDetails);
					flag = goodsServise.amendNum(goodsID, num);
				}
			}
			if(flag) {
				return "true";
			}else {
				return "false";
			}
		}
	}
	
	@RequestMapping("order")
	public ModelAndView order(HttpSession session) {
		if(session.getAttribute("userID") != null) {
			ModelAndView mv = new ModelAndView("order");
			int id = (int) session.getAttribute("userID");
			List<Orders> ordersList = ordersServise.searchByUID(id);
			for(int i=0;i<ordersList.size();i++) {
				List<OrderDetails> orderDetailsList = orderDetailsServise.searchByOID(ordersList.get(i).getId());
				for(int j=0;j<orderDetailsList.size();j++) {
					List<GoodsImg> imgList = new ArrayList<>();
					GoodsImg img = goodsImgServise.searchByID(orderDetailsList.get(j).getGoods().getId());
					imgList.add(img);
					orderDetailsList.get(j).getGoods().setGoodsImgs(imgList);
				}
				ordersList.get(i).setOrderDetails(orderDetailsList);
			}
			mv.addObject("ordersList", ordersList);
			return mv;
		}else {
			session.setAttribute("url", "order");
			ModelAndView mv = new ModelAndView("load");
			return mv;
		}
	}
}
