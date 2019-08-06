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
import entity.Department;
import entity.Project;
import util.Constant;
import util.Pagination;

//ÉùÃ÷ÊÇ¿ØÖÆÆ÷
@Controller
public class DepController {
	@RequestMapping(value="department")
	public ModelAndView department(Department dep,String ye) {
		ModelAndView mv = new ModelAndView("department/department");
		List<Department> depList = new ArrayList<>();
		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		int page = 1;
		int count = depDao.searchCountByDep(dep);
		if(ye != null) {
			page = Integer.parseInt(ye);
		}
		Pagination p = new Pagination(page, count, Constant.DEP_NUM_IN_PAGE, Constant.DEP_NUM_OF_PAGE);
		depList = depDao.searchByDep(dep, p.getBegin(), Constant.DEP_NUM_IN_PAGE);
		mv.addObject("dep", dep);
		mv.addObject("depList", depList);
		mv.addObject("p", p);
		session.commit();
		session.close();
		return mv;
	}
	@RequestMapping(value="showAddDep")
	public ModelAndView showAddDep() {
		ModelAndView mv = new ModelAndView("department/add");
		return mv;
	}
	@RequestMapping(value="addDep")
	public String addDep(Department dep) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		depDao.add(dep);
		session.commit();
		session.close();
		return "redirect:department.do";
	}
	@RequestMapping(value="delDep")
	public String delDep(String ids) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		int flag = depDao.del(ids);
		if(flag>0) {
			flag = depDao.setEmpDid(ids);
		}
		session.commit();
		session.close();
		return "redirect:department.do";
	}
	@RequestMapping(value="showUpdateDep")
	public ModelAndView showUpdateDep(String ids) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		List<Department> depList = new ArrayList<>();
		depList = depDao.search2(ids);
		ModelAndView mv = new ModelAndView("department/update");
		mv.addObject("dep", depList);
		session.commit();
		session.close();
		return mv;
	}
	@RequestMapping(value="updateDep")
	public String updateDep(Department dep) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		depDao.update(dep);
		session.commit();
		session.close();
		return "redirect:department.do";
	}
	@RequestMapping(value="ajaxUpdateDep")
	@ResponseBody
	public String ajaxUpdateDep(@RequestBody List<Department> depList) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		int flag = depDao.updateBatch(depList);
		if(flag>0) {
			return "true";
		}
		session.commit();
		session.close();
		return "false";
	}
	@RequestMapping(value="showProByDep")
	public ModelAndView showProByDep(Department dep) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		Dep2ProDao d2pDao = session.getMapper(Dep2ProDao.class);
		Department dep2= new Department();
		dep2 = depDao.searchDep(dep);
		ModelAndView mv = new ModelAndView("department/dep-pro");
		List<Project> pro = d2pDao.searchByDepartment(dep.getId());
		List<Project> proNo = d2pDao.searchByNotDepartment(dep.getId());
		mv.addObject("dep", dep2);
		mv.addObject("pro", pro);
		mv.addObject("proNo", proNo);
		session.commit();
		session.close();
		return mv;
	}
	@RequestMapping(value="showProByDep1")
	public ModelAndView showProByDep1(Department dep) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		Dep2ProDao d2pDao = session.getMapper(Dep2ProDao.class);
		Department dep2= new Department();
		dep2 = depDao.searchDep(dep);
		ModelAndView mv = new ModelAndView("department/test1");
		List<Project> pro = d2pDao.searchByDepartment(dep.getId());
		List<Project> proNo = d2pDao.searchByNotDepartment(dep.getId());
		mv.addObject("dep", dep2);
		mv.addObject("pro", pro);
		mv.addObject("proNo", proNo);
		session.commit();
		session.close();
		return mv;
	}
	@RequestMapping(value="showProByDep2")
	public ModelAndView showProByDep2(Department dep) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		Dep2ProDao d2pDao = session.getMapper(Dep2ProDao.class);
		Department dep2= new Department();
		dep2 = depDao.searchDep(dep);
		ModelAndView mv = new ModelAndView("department/test2");
		List<Project> pro = d2pDao.searchByDepartment(dep.getId());
		List<Project> proNo = d2pDao.searchByNotDepartment(dep.getId());
		mv.addObject("dep", dep2);
		mv.addObject("pro", pro);
		mv.addObject("proNo", proNo);
		session.commit();
		session.close();
		return mv;
	}
	@RequestMapping(value="showProByDep3")
	public ModelAndView showProByDep3(Department dep) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		Dep2ProDao d2pDao = session.getMapper(Dep2ProDao.class);
		Department dep2= new Department();
		dep2 = depDao.searchDep(dep);
		ModelAndView mv = new ModelAndView("department/test3");
		List<Project> pro = d2pDao.searchByDepartment(dep.getId());
		List<Project> proNo = d2pDao.searchByNotDepartment(dep.getId());
		mv.addObject("dep", dep2);
		mv.addObject("pro", pro);
		mv.addObject("proNo", proNo);
		session.commit();
		session.close();
		return mv;
	}
	
	@RequestMapping(value="addProByDep")
	@ResponseBody
	public String addProByDep(int d_id,String p_id) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		Dep2ProDao d2pDao = session.getMapper(Dep2ProDao.class);
		String[] strs = p_id.split(",");
		List<String> strList = new ArrayList<>();
		for(int i=0;i<strs.length;i++) {
			strList.add(strs[i]);
		}
		int flag = d2pDao.add(d_id, strList);
		session.commit();
		session.close();
		if(flag>0) {
			return "true";
		}
		return "false";
	}
	@RequestMapping(value="delProByDep")
	@ResponseBody
	public String delProByDep(int d_id,String p_id) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		Dep2ProDao d2pDao = session.getMapper(Dep2ProDao.class);
		String[] strs = p_id.split(",");
		List<String> strList = new ArrayList<>();
		for(int i=0;i<strs.length;i++) {
			strList.add(strs[i]);
		}
		int flag = d2pDao.del(d_id, strList);
		session.commit();
		session.close();
		if(flag>0) {
			return "true";
		}
		return "false";
	}
}
