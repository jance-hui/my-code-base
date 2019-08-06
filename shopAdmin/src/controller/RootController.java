package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Root;
import service.RootService;
import util.CreateMD5;

@Controller
public class RootController {
	@Autowired
	RootService rootService;

	@RequestMapping("showLogin")
	public ModelAndView showLoad(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("login");
		Cookie[] cookies = request.getCookies();
		String name = "";
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("rootname".equals(cookies[i].getName())) {
					name = cookies[i].getValue();
				}
			}
		}
		mv.addObject("name", name);
		return mv;
	}

	@RequestMapping(value = "login")
	@ResponseBody
	public String load(@RequestBody Root root, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		root.setPassword(CreateMD5.getMd5(root.getPassword()));
		//System.out.println(root.getUsername());
		//System.out.println(root.getPassword());

		boolean flag = rootService.loginSearch(root);
		if (flag) {
			Root root2 = rootService.searchByName(root.getUsername());
			session.setAttribute("rootID", root2.getId());
			session.setAttribute("username", root.getUsername());
			Cookie cookie = new Cookie("username", root.getUsername());
			// cookie的存在时间5分钟
			cookie.setMaxAge(60 * 5);
			response.addCookie(cookie);
//			String url = (String) session.getAttribute("url");
//			if (url != "" && url != null) {
//				session.removeAttribute("url");
//				if ("cart".equals(url)) {
//					return "redirect:" + url + ".do?id=" + cart.getId();
//				} else if ("order".equals(url)) {
//					return "redirect:" + url + ".do?id=" + user2.getId();
//				} else if ("addOrder.do".equals(url)) {
//					return "redirect:" + url;
//				} else {
//					return "redirect:" + url;
//				}
//			} else {
//				return "redirect:select.do";
//			}
			return "true";
		} else {
			return "false";
		}
	}

	/*@RequestMapping("showRegister")
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
	}*/
}
