package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.User;
import service.UserService;
import util.CreateMD5;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping("user_list")
	public ModelAndView user_list(User user) {
		ModelAndView mv = new ModelAndView("user_list");
		List<User> users = userService.searchByUser(user);
		mv.addObject("users", users);
		mv.addObject("user", user);
		return mv;
	}
	@RequestMapping("member-Grading")
	public ModelAndView memberGrading() {
		ModelAndView mv = new ModelAndView("member-Grading");
		List<User> users = userService.search();
		mv.addObject("users", users);
		return mv;
	}
	@RequestMapping("integration")
	public ModelAndView integration(User user) {
		ModelAndView mv = new ModelAndView("integration");
		List<User> users = userService.searchByUser(user);
		mv.addObject("users", users);
		mv.addObject("user", user);
		return mv;
	}
	@RequestMapping("member-show")
	public ModelAndView memberShow(int id) {
		ModelAndView mv = new ModelAndView("member-show");
		User user = userService.searchByID(id);
		mv.addObject("user", user);
		return mv;
	}
	@RequestMapping("addUser")
	@ResponseBody
	public String addUser(@RequestBody User user) {
		user.setPassword(CreateMD5.getMd5(user.getPassword()));
		boolean flag = userService.add(user);
		return String.valueOf(flag);
	}
}
