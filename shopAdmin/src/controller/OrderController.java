package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.GoodsImg;
import entity.OrderDetails;
import entity.Orders;
import service.GoodsImgService;
import service.OrderDetailsService;
import service.OrdersService;

@Controller
public class OrderController {
	@Autowired
	OrdersService ordersService;
	@Autowired
	OrderDetailsService orderDetailsServise;
	@Autowired
	GoodsImgService goodsImgServise;

	@RequestMapping(value="transaction")
	public ModelAndView transaction() {
		ModelAndView mv = new ModelAndView("transaction");
		int count = ordersService.searchCount();
		float orderMoneys = 0;
		List<Float> money = ordersService.searchMoney();
		for(int i=0;i<money.size();i++) {
			orderMoneys += money.get(i);
		}
		String orderMoney = String.valueOf(orderMoneys);
		orderMoney = orderMoney.substring(0, orderMoney.indexOf(".")+3);//保留两位小数
		mv.addObject("orderMoney", orderMoney);
		mv.addObject("count", count);
		return mv;
	}
	
	@RequestMapping(value="Orderform")
	public ModelAndView Orderform(Orders orders) {
		ModelAndView mv = new ModelAndView("Orderform");
		List<Orders> ordersList = ordersService.searchByOrder(orders);
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
		mv.addObject("order", orders);
		mv.addObject("ordersList", ordersList);
		return mv;
	}
		
	@RequestMapping(value="order_detailed")
	public ModelAndView order_detailed(int id) {
		ModelAndView mv = new ModelAndView("order_detailed");
		Orders orders = ordersService.searchByID(id);
		List<OrderDetails> orderDetailsList = orderDetailsServise.searchByOID(id);
		for(int j=0;j<orderDetailsList.size();j++) {
			List<GoodsImg> imgList = new ArrayList<>();
			GoodsImg img = goodsImgServise.searchByID(orderDetailsList.get(j).getGoods().getId());
			imgList.add(img);
			orderDetailsList.get(j).getGoods().setGoodsImgs(imgList);
		}
		mv.addObject("orders", orders);
		mv.addObject("orderDetailsList", orderDetailsList);
		return mv;
	}
	
	@RequestMapping(value="delOrders")
	@ResponseBody
	public String delOrders(String ids) {
		boolean flag = ordersService.del(ids);
		flag = orderDetailsServise.del(ids);
		return String.valueOf(flag);
	}
}
