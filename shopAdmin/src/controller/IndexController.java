package controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.OrdersService;
import service.UserService;

@Controller
public class IndexController {
	@Autowired
	UserService userService;
	@Autowired
	OrdersService ordersService;

	@RequestMapping(value="index")
	public ModelAndView index(HttpSession session) {
		String username = (String)session.getAttribute("username");
		if(username == null) {
			ModelAndView mv = new ModelAndView("login");
			return mv;
		}else {
			ModelAndView mv = new ModelAndView("index");
			mv.addObject("username", username);
			return mv;
		}
	}
	@RequestMapping(value="home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home");
		int userCount = userService.searchCount();
		int orderCount = ordersService.searchCount();
		float orderMoneys = 0;
		List<Float> money = ordersService.searchMoney();
		for(int i=0;i<money.size();i++) {
			orderMoneys += money.get(i);
		}
		String orderMoney = String.valueOf(orderMoneys);
		orderMoney = orderMoney.substring(0, orderMoney.indexOf(".")+3);//保留两位小数
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");//设置日期格式
		String timeType = df.format(new Date());// new Date()为获取当前系统时间
		try {
			InetAddress addr = InetAddress.getLocalHost();
	        String ip=addr.getHostAddress().toString(); //获取本机ip
			mv.addObject("ip", ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		mv.addObject("userCount", userCount);
		mv.addObject("orderCount", orderCount);
		mv.addObject("orderMoney", orderMoney);
		mv.addObject("timeType", timeType);
		return mv;	
	}

}
