package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Department;
import entity.Project;
import entity.Score;
import service.Dep2ProService;
import service.DepartmentService;
import service.ProjectService;
import service.ScoreService;
import util.Constant;
import util.Pagination;

//ÉùÃ÷ÊÇ¿ØÖÆÆ÷
@Controller
public class ScController {
	@Autowired
	ProjectService proService;
	@Autowired
	ScoreService scService;
	@Autowired
	DepartmentService depService;
	@Autowired
	Dep2ProService d2pService;
	@RequestMapping(value = "score")
	public ModelAndView score(Score sc, String ye) {
		ModelAndView mv = new ModelAndView("score/score");
		List<Score> scList = new ArrayList<>();
		List<Department> depList = depService.search();
		List<Project> proList = proService.search();
		int page = 1;
		int count = scService.searchCount(sc);
		if (ye != null) {
			page = Integer.parseInt(ye);
		}
		Pagination p = new Pagination(page, count, Constant.SC_NUM_IN_PAGE, Constant.SC_NUM_OF_PAGE);
		scList = scService.searchByCondition(sc,  p.getBegin(), Constant.SC_NUM_IN_PAGE);
		mv.addObject("sc", sc);
		mv.addObject("scList", scList);
		mv.addObject("dep", depList);
		mv.addObject("pro", proList);
		mv.addObject("p", p);
		return mv;
	}
	@RequestMapping(value = "scoreShow")
	public ModelAndView scoreShow(Score sc, String ye) {
		ModelAndView mv = new ModelAndView("score/show");
		List<Score> scList = new ArrayList<>();
		List<Department> depList = depService.search();
		List<Project> proList = proService.search();
		int page = 1;
		int count = scService.searchCount(sc);
		if (ye != null) {
			page = Integer.parseInt(ye);
		}
		Pagination p = new Pagination(page, count, Constant.SC_NUM_IN_PAGE, Constant.SC_NUM_OF_PAGE);
		scList = scService.searchByCondition(sc,  p.getBegin(), Constant.SC_NUM_IN_PAGE);
		mv.addObject("sc", sc);
		mv.addObject("scList", scList);
		mv.addObject("dep", depList);
		mv.addObject("pro", proList);
		mv.addObject("p", p);
		return mv;
	}

	@RequestMapping(value = "ajaxUpdateSc")
	@ResponseBody
	public Score ajaxUpdateSc(@RequestBody Score sc) {
		int id = 0;
		if(sc.getId() != null) {
			id = sc.getId();
		};
		boolean flag = false;
		if (id == 0) {
			id = scService.add(sc);
			if(id>0) {
				flag = true;
			}
		} else {
			flag = scService.update(sc);
		}
		Score score = scService.search(id);
		if (flag) {
			return score;
		}
		return null;
	}
	@RequestMapping(value = "findPro")
	@ResponseBody
	public List<Project> findPro(int d_id) {
		List<Project> proList = new ArrayList<>();
		proList = d2pService.searchByDepartment(d_id);
		return proList;
	}

}
