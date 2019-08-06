package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Project;
import service.ProjectService;
import util.Constant;
import util.Pagination;

//ÉùÃ÷ÊÇ¿ØÖÆÆ÷
@Controller
public class ProController {
	@Autowired
	ProjectService proService;
	@RequestMapping(value = "project")
	public ModelAndView project(Project pro, String ye) {
		ModelAndView mv = new ModelAndView("project/project");
		List<Project> proList = new ArrayList<>();
		int page = 1;
		int count = proService.searchCountByPro(pro);
		if (ye != null) {
			page = Integer.parseInt(ye);
		}
		Pagination p = new Pagination(page, count, Constant.PRO_NUM_IN_PAGE, Constant.PRO_NUM_OF_PAGE);
		proList = proService.searchByPro(pro, p.getBegin(), Constant.DEP_NUM_IN_PAGE);
		mv.addObject("pro", pro);
		mv.addObject("proList", proList);
		mv.addObject("p", p);
		return mv;
	}

	@RequestMapping(value = "showAddPro")
	public ModelAndView showAddPro() {
		ModelAndView mv = new ModelAndView("project/add");
		return mv;
	}

	@RequestMapping(value = "addPro")
	public String addPro(Project pro) {
		proService.add(pro);
		return "redirect:project.do";
	}

	@RequestMapping(value = "delPro")
	public String delPro(String ids) {
		proService.del(ids);
		return "redirect:project.do";
	}

	@RequestMapping(value = "showUpdatePro")
	public ModelAndView showUpdatePro(String ids) {
		List<Project> proList = new ArrayList<>();
		proList = proService.search2(ids);
		ModelAndView mv = new ModelAndView("project/update");
		mv.addObject("pro", proList);
		return mv;
	}

	@RequestMapping(value = "ajaxUpdatePro")
	@ResponseBody
	public String ajaxUpdatePro(@RequestBody List<Project> proList) {
		boolean flag = proService.update(proList);
		if (flag) {
			return "true";
		}
		return "false";
	}

}
