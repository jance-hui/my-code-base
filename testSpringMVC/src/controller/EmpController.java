package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.MybatisSqlSession;
import entity.Department;
import entity.Employee;
import util.Constant;
import util.Pagination;

//ÉùÃ÷ÊÇ¿ØÖÆÆ÷
@Controller
public class EmpController {
	@RequestMapping(value="employee")
	public ModelAndView employee(Employee emp,String ye) {
		ModelAndView mv = new ModelAndView("employee/employee");
		List<Employee> empList = new ArrayList<>();
		SqlSession session = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = session.getMapper(EmployeeDao.class);
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		List<Department> depList = depDao.search();
		int page = 1;
		int count = empDao.searchCountByEmp(emp);
		if(ye != null) {
			page = Integer.parseInt(ye);
		}
		Pagination p = new Pagination(page, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		empList = empDao.searchByEmp(emp, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		mv.addObject("emps", empList);
		mv.addObject("emp", emp);
		mv.addObject("dep", depList);
		mv.addObject("p", p);
		session.commit();
		session.close();
		return mv;
	}
	@RequestMapping(value="showAddEmp1")
	public ModelAndView showAddEmp1() {
		ModelAndView mv = new ModelAndView("employee/add");
		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		List<Department> depList = depDao.search();
		mv.addObject("dep", depList);
		session.commit();
		session.close();
		return mv;
	}
	@RequestMapping(value="addEmp1",method=RequestMethod.POST)
	public String addEmp1(@RequestParam(value="photo") MultipartFile[] myfiles ,Employee emp) {
		String newName = "";
		try {
			String path="E://empImg";
			MultipartFile myFile = myfiles[0];
			if(!myFile.isEmpty()) {
				String oldName = myFile.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				newName = uuid.toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
				myFile.transferTo(new File(path +"/"+ newName));
			}
			emp.setImg(newName);
			SqlSession session = MybatisSqlSession.getSqlSession();
			EmployeeDao empDao = session.getMapper(EmployeeDao.class);
			empDao.add(emp);
			session.commit();
			session.close();
		}catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return "redirect:employee.do";
	}
	@RequestMapping(value="showAddEmp2")
	public ModelAndView showAddEmp2() {
		ModelAndView mv = new ModelAndView("employee/add2");
		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		List<Department> depList = depDao.search();
		mv.addObject("dep", depList);
		session.commit();
		session.close();
		return mv;
	}
	@RequestMapping(value="addEmp2")
	public String addEmp2(Employee emp) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = session.getMapper(EmployeeDao.class);
		empDao.add(emp);
		session.commit();
		session.close();
		return "redirect:employee.do";
	}
	@RequestMapping(value="showUpdateEmp")
	public ModelAndView showUpdateEmp(int id) {
		ModelAndView mv = new ModelAndView("employee/update");
		SqlSession session = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = session.getMapper(EmployeeDao.class);
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		List<Department> depList = depDao.search();
		Employee emp = empDao.searchEmp(id);
		mv.addObject("dep", depList.get(0));
		mv.addObject("emp", emp);
		session.commit();
		session.close();
		return mv;
	}
	@RequestMapping(value="updateEmp")
	public String updateEmp(Employee emp) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = session.getMapper(EmployeeDao.class);
		empDao.update(emp);
		session.commit();
		session.close();
		return "redirect:employee.do";
	}
	@RequestMapping(value="delEmp")
	public String delEmp(int id) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = session.getMapper(EmployeeDao.class);
		empDao.del(id);
		session.commit();
		session.close();
		return "redirect:employee.do";
	}
	@RequestMapping(value="delBatchEmp")
	public String delBatchEmp(String ids) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = session.getMapper(EmployeeDao.class);
		empDao.delBatch(ids);
		session.commit();
		session.close();
		return "redirect:employee.do";
	}
	@RequestMapping(value="upload",method=RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam(value="img") MultipartFile[] myfiles) {
		String newName = "";
		try {
			String path="E://empImg";
			MultipartFile myFile = myfiles[0];
			if(!myFile.isEmpty()) {
				String oldName = myFile.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				newName = uuid.toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
				myFile.transferTo(new File(path +"/"+ newName));
			} 
		}catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return newName;
	}
	
	@RequestMapping(value="ajaxUpdateEmp")
	@ResponseBody
	public String ajaxUpdateEmp(@RequestBody List<Employee> empList) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = session.getMapper(EmployeeDao.class);
		int flag = empDao.updateBatch(empList);
		if(flag>0) {
			return "true";
		}
		session.commit();
		session.close();
		return "false";
	}
	@RequestMapping(value="showUpdateBatchEmp")
	public ModelAndView showUpdateBatchEmp(String ids) {
		ModelAndView mv = new ModelAndView("employee/updateBatch");
		SqlSession session = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = session.getMapper(EmployeeDao.class);
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		List<Department> depList = depDao.search();
		List<Employee> empList = empDao.searchBatch(ids);
		mv.addObject("dep", depList);
		mv.addObject("emp", empList);
		session.commit();
		session.close();
		return mv;
	}
	@RequestMapping(value="updateBatchEmp")
	public String updateBatchEmp(@RequestParam("emps") String[] emps) {
		List<Employee> empList = null;
		SqlSession session = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = session.getMapper(EmployeeDao.class);
		empDao.updateBatch(empList);
		session.commit();
		session.close();
		return "redirect:employee.do";
	}

}
