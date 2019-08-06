package controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dao.MybatisSqlSession;
import dao.ProjectDao;
import entity.Project;
import util.Constant;
import util.Pagination;

//ÉùÃ÷ÊÇ¿ØÖÆÆ÷
@Controller
public class ProController {
	@RequestMapping(value = "project")
	public ModelAndView project(Project pro, String ye) {
		ModelAndView mv = new ModelAndView("project/project");
		List<Project> proList = new ArrayList<>();
		SqlSession session = MybatisSqlSession.getSqlSession();
		ProjectDao proDao = session.getMapper(ProjectDao.class);
		int page = 1;
		int count = proDao.searchCountByPro(pro);
		if (ye != null) {
			page = Integer.parseInt(ye);
		}
		Pagination p = new Pagination(page, count, Constant.PRO_NUM_IN_PAGE, Constant.PRO_NUM_OF_PAGE);
		proList = proDao.searchByPro(pro, p.getBegin(), Constant.DEP_NUM_IN_PAGE);
		mv.addObject("pro", pro);
		mv.addObject("proList", proList);
		mv.addObject("p", p);
		session.commit();
		session.close();
		return mv;
	}

	@RequestMapping(value = "showAddPro")
	public ModelAndView showAddPro() {
		ModelAndView mv = new ModelAndView("project/add");
		return mv;
	}

	@RequestMapping(value = "addPro")
	public String addPro(Project pro) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		ProjectDao proDao = session.getMapper(ProjectDao.class);
		proDao.add(pro);
		session.commit();
		session.close();
		return "redirect:project.do";
	}

	@RequestMapping(value = "delPro")
	public String delPro(String ids) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		ProjectDao proDao = session.getMapper(ProjectDao.class);
		int flag = proDao.del(ids);
		if(flag>0) {
			flag = proDao.delRPid(ids);
		}
		session.commit();
		session.close();
		return "redirect:project.do";
	}

	@RequestMapping(value = "showUpdatePro")
	public ModelAndView showUpdatePro(String ids) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		ProjectDao proDao = session.getMapper(ProjectDao.class);
		List<Project> proList = new ArrayList<>();
		proList = proDao.search2(ids);
		ModelAndView mv = new ModelAndView("project/update");
		mv.addObject("pro", proList);
		session.commit();
		session.close();
		return mv;
	}

	@RequestMapping(value = "ajaxUpdatePro")
	@ResponseBody
	public String ajaxUpdatePro(@RequestBody List<Project> proList) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		ProjectDao proDao = session.getMapper(ProjectDao.class);
		int flag = proDao.update(proList);
		if (flag>0) {
			return "true";
		}
		session.commit();
		session.close();
		return "false";
	}

}
