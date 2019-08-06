package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Cart;
import entity.User;
import service.CartService;
import service.UserService;
import util.CreateMD5;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;

	@RequestMapping("showLoad")
	public ModelAndView showLoad(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("load");
		Cookie[] cookies = request.getCookies();
		String name = "";
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("username".equals(cookies[i].getName())) {
					name = cookies[i].getValue();
				}
			}
		}
		mv.addObject("name", name);
		return mv;
	}

	@RequestMapping(value = "load")
	public String load(User user, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		user.setPassword(CreateMD5.getMd5(user.getPassword()));
		boolean flag = userService.loginSearch(user);
		if (flag) {
			User user2 = userService.searchByName(user.getUsername());
			Cart cart = cartService.searchByUID(user2.getId());
			session.setAttribute("userID", user2.getId());
			session.setAttribute("cartID", cart.getId());
			session.setAttribute("username", user.getUsername());
			Cookie cookie = new Cookie("username", user.getUsername());
			// cookie的存在时间5分钟
			cookie.setMaxAge(60 * 5);
			response.addCookie(cookie);
			String url = (String) session.getAttribute("url");
			if (url != "" && url != null) {
				session.removeAttribute("url");
				if ("cart".equals(url)) {
					return "redirect:" + url + ".do?id=" + cart.getId();
				} else if ("order".equals(url)) {
					return "redirect:" + url + ".do?id=" + user2.getId();
				} else if ("addOrder.do".equals(url)) {
					return "redirect:" + url;
				} else {
					return "redirect:" + url;
				}
			} else {
				return "redirect:select.do";
			}
		} else {
			request.setAttribute("error", "error");
			return "redirect:showLoad.do";
		}
	}

	@RequestMapping("showRegister")
	public ModelAndView showRegister() {
		ModelAndView mv = new ModelAndView("register");
		return mv;
	}

	@RequestMapping("register")
	public ModelAndView register(User user) {
		user.setPassword(CreateMD5.getMd5(user.getPassword()));
		int newUserID = userService.add(user);
		user.setId(newUserID);
		Cart cart = new Cart();
		cart.setUser(user);
		int cartNewID = cartService.add(cart);
		if (cartNewID > 0 && newUserID > 0) {
			ModelAndView mv = new ModelAndView("load");
			mv.addObject("name", user.getUsername());
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("register");
			return mv;
		}
	}
}
