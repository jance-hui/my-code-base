package controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dao.Dep2ProDao;
import dao.DepartmentDao;
import dao.MybatisSqlSession;
import dao.ProjectDao;
import dao.ScoreDao;
import entity.Department;
import entity.Project;
import entity.Score;
import util.Constant;
import util.Pagination;

//ÉùÃ÷ÊÇ¿ØÖÆÆ÷
@Controller
public class ScController {
	
	@RequestMapping(value = "score")
	public ModelAndView score(Score sc, String ye) {
		ModelAndView mv = new ModelAndView("score/score");
		List<Score> scList = new ArrayList<>();
		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		ProjectDao proDao = session.getMapper(ProjectDao.class);
		ScoreDao scDao = session.getMapper(ScoreDao.class);
		List<Department> depList = depDao.search();
		List<Project> proList = proDao.search();
		int page = 1;
		int count = scDao.searchCount(sc);
		if (ye != null) {
			page = Integer.parseInt(ye);
		}
		Pagination p = new Pagination(page, count, Constant.SC_NUM_IN_PAGE, Constant.SC_NUM_OF_PAGE);
		scList = scDao.searchByCondition(sc,  p.getBegin(), Constant.SC_NUM_IN_PAGE);
		mv.addObject("sc", sc);
		mv.addObject("scList", scList);
		mv.addObject("dep", depList);
		mv.addObject("pro", proList);
		mv.addObject("p", p);
		session.commit();
		session.close();
		return mv;
	}
	@RequestMapping(value = "scoreShow")
	public ModelAndView scoreShow(Score sc, String ye) {
		ModelAndView mv = new ModelAndView("score/show");
		List<Score> scList = new ArrayList<>();
		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		ProjectDao proDao = session.getMapper(ProjectDao.class);
		ScoreDao scDao = session.getMapper(ScoreDao.class);
		List<Department> depList = depDao.search();
		List<Project> proList = proDao.search();
		int page = 1;
		int count = scDao.searchCount(sc);
		if (ye != null) {
			page = Integer.parseInt(ye);
		}
		Pagination p = new Pagination(page, count, Constant.SC_NUM_IN_PAGE, Constant.SC_NUM_OF_PAGE);
		scList = scDao.searchByCondition(sc,  p.getBegin(), Constant.SC_NUM_IN_PAGE);
		mv.addObject("sc", sc);
		mv.addObject("scList", scList);
		mv.addObject("dep", depList);
		mv.addObject("pro", proList);
		mv.addObject("p", p);
		session.commit();
		session.close();
		return mv;
	}

	@RequestMapping(value = "ajaxUpdateSc")
	@ResponseBody
	public Score ajaxUpdateSc(@RequestBody Score sc) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		ScoreDao scDao = session.getMapper(ScoreDao.class);
		int id = 0;
		if(sc.getId() != null) {
			id = sc.getId();
		};
		int flag = 0;
		if (id == 0) {
			flag = scDao.add(sc);
			if (flag > 0) {
				id = scDao.returnNewId();
			}
		} else {
			flag = scDao.update(sc);
		}
		Score score = scDao.search(id);
		session.commit();
		session.close();
		if (flag>0) {
			return score;
		}
		return null;
	}
	@RequestMapping(value = "findPro")
	@ResponseBody
	public List<Project> findPro(int d_id) {
		List<Project> proList = new ArrayList<>();
		SqlSession session = MybatisSqlSession.getSqlSession();
		Dep2ProDao d2pDao = session.getMapper(Dep2ProDao.class);
		proList = d2pDao.searchByDepartment(d_id);
		session.commit();
		session.close();
		return proList;
	}

}
