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
import service.Dep2ProService;
import service.DepartmentService;
import util.Constant;
import util.Pagination;

//ÉùÃ÷ÊÇ¿ØÖÆÆ÷
@Controller
public class DepController {
	@Autowired
	DepartmentService depService;
	@Autowired
	Dep2ProService d2pService;
	
	@RequestMapping(value="department")
	public ModelAndView department(Department dep,String ye) {
		ModelAndView mv = new ModelAndView("department/department");
		List<Department> depList = new ArrayList<>();
		int page = 1;
		int count = depService.searchCountByDep(dep);
		if(ye != null) {
			page = Integer.parseInt(ye);
		}
		Pagination p = new Pagination(page, count, Constant.DEP_NUM_IN_PAGE, Constant.DEP_NUM_OF_PAGE);
		depList = depService.searchByDep(dep, p.getBegin(), Constant.DEP_NUM_IN_PAGE);
		mv.addObject("dep", dep);
		mv.addObject("depList", depList);
		mv.addObject("p", p);
		
		
		return mv;
	}
	@RequestMapping(value="showAddDep")
	public ModelAndView showAddDep() {
		ModelAndView mv = new ModelAndView("department/add");
		return mv;
	}
	@RequestMapping(value="addDep")
	public String addDep(Department dep) {
		depService.add(dep);
		
		
		return "redirect:department.do";
	}
	@RequestMapping(value="delDep")
	public String delDep(String ids) {
		
		depService.del(ids);
		return "redirect:department.do";
	}
	@RequestMapping(value="showUpdateDep")
	public ModelAndView showUpdateDep(String ids) {
		
		List<Department> depList = new ArrayList<>();
		depList = depService.search2(ids);
		ModelAndView mv = new ModelAndView("department/update");
		mv.addObject("dep", depList);
		
		
		return mv;
	}
	@RequestMapping(value="updateDep")
	public String updateDep(Department dep) {
		
		depService.update(dep);
		
		
		return "redirect:department.do";
	}
	@RequestMapping(value="ajaxUpdateDep")
	@ResponseBody
	public String ajaxUpdateDep(@RequestBody List<Department> depList) {
		
		boolean flag = depService.updateBatch(depList);
		if(flag) {
			return "true";
		}
		
		
		return "false";
	}
	@RequestMapping(value="showProByDep")
	public ModelAndView showProByDep(Department dep) {
		Department dep2= new Department();
		dep2 = depService.searchDep(dep);
		ModelAndView mv = new ModelAndView("department/dep-pro");
		List<Project> pro = d2pService.searchByDepartment(dep.getId());
		List<Project> proNo = d2pService.searchByNotDepartment(dep.getId());
		mv.addObject("dep", dep2);
		mv.addObject("pro", pro);
		mv.addObject("proNo", proNo);
		
		
		return mv;
	}
	@RequestMapping(value="showProByDep1")
	public ModelAndView showProByDep1(Department dep) {
		Department dep2= new Department();
		dep2 = depService.searchDep(dep);
		ModelAndView mv = new ModelAndView("department/test1");
		List<Project> pro = d2pService.searchByDepartment(dep.getId());
		List<Project> proNo = d2pService.searchByNotDepartment(dep.getId());
		mv.addObject("dep", dep2);
		mv.addObject("pro", pro);
		mv.addObject("proNo", proNo);
		
		
		return mv;
	}
	@RequestMapping(value="showProByDep2")
	public ModelAndView showProByDep2(Department dep) {
		Department dep2= new Department();
		dep2 = depService.searchDep(dep);
		ModelAndView mv = new ModelAndView("department/test2");
		List<Project> pro = d2pService.searchByDepartment(dep.getId());
		List<Project> proNo = d2pService.searchByNotDepartment(dep.getId());
		mv.addObject("dep", dep2);
		mv.addObject("pro", pro);
		mv.addObject("proNo", proNo);
		
		
		return mv;
	}
	@RequestMapping(value="showProByDep3")
	public ModelAndView showProByDep3(Department dep) {
		Department dep2= new Department();
		dep2 = depService.searchDep(dep);
		ModelAndView mv = new ModelAndView("department/test3");
		List<Project> pro = d2pService.searchByDepartment(dep.getId());
		List<Project> proNo = d2pService.searchByNotDepartment(dep.getId());
		mv.addObject("dep", dep2);
		mv.addObject("pro", pro);
		mv.addObject("proNo", proNo);
		return mv;
	}
	
	@RequestMapping(value="addProByDep")
	@ResponseBody
	public String addProByDep(int d_id,String p_id) {
		String[] strs = p_id.split(",");
		List<String> strList = new ArrayList<>();
		for(int i=0;i<strs.length;i++) {
			strList.add(strs[i]);
		}
		boolean flag = d2pService.add(d_id, strList);
		if(flag) {
			return "true";
		}
		return "false";
	}
	@RequestMapping(value="delProByDep")
	@ResponseBody
	public String delProByDep(int d_id,String p_id) {
		String[] strs = p_id.split(",");
		List<String> strList = new ArrayList<>();
		for(int i=0;i<strs.length;i++) {
			strList.add(strs[i]);
		}
		boolean flag = d2pService.del(d_id, strList);
		if(flag) {
			return "true";
		}
		return "false";
	}
}
