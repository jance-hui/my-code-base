package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import entity.Department;
import entity.Employee;
import service.DepartmentService;
import service.EmployeeService;
import util.Constant;
import util.Pagination;

//ÉùÃ÷ÊÇ¿ØÖÆÆ÷
@Controller
public class EmpController {
	@Autowired
	EmployeeService empService;
	@Autowired
	DepartmentService depService;
	
	@RequestMapping(value = "employee")
	public ModelAndView employee(Employee emp, String ye) {
		ModelAndView mv = new ModelAndView("employee/employee");
		List<Employee> empList = new ArrayList<>();
		List<Department> depList = depService.search();
		int page = 1;
		int count = empService.searchCountByEmp(emp);
		if (ye != null) {
			page = Integer.parseInt(ye);
		}
		Pagination p = new Pagination(page, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		empList = empService.searchByEmp(emp, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		mv.addObject("emps", empList);
		mv.addObject("emp", emp);
		mv.addObject("dep", depList);
		mv.addObject("p", p);
		return mv;
	}

	@RequestMapping(value = "showAddEmp1")
	public ModelAndView showAddEmp1() {
		ModelAndView mv = new ModelAndView("employee/add");
		
		List<Department> depList = depService.search();
		mv.addObject("dep", depList);
		return mv;
	}

	@RequestMapping(value = "addEmp1", method = RequestMethod.POST)
	public String addEmp1(@RequestParam(value = "photo") MultipartFile[] myfiles, Employee emp) {
		String newName = "";
		try {
			String path = "E://empImg";
			MultipartFile myFile = myfiles[0];
			if (!myFile.isEmpty()) {
				String oldName = myFile.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				newName = uuid.toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
				myFile.transferTo(new File(path + "/" + newName));
			}
			emp.setImg(newName);
			empService.add(emp);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return "redirect:employee.do";
	}

	@RequestMapping(value = "showAddEmp2")
	public ModelAndView showAddEmp2() {
		ModelAndView mv = new ModelAndView("employee/add2");
		List<Department> depList = depService.search();
		mv.addObject("dep", depList);
		return mv;
	}

	@RequestMapping(value = "addEmp2")
	public String addEmp2(Employee emp) {
		empService.add(emp);
		return "redirect:employee.do";
	}

	@RequestMapping(value = "showUpdateEmp")
	public ModelAndView showUpdateEmp(int id) {
		ModelAndView mv = new ModelAndView("employee/update");
		List<Department> depList = depService.search();
		Employee emp = empService.searchEmp(id);
		mv.addObject("dep", depList);
		mv.addObject("emp", emp);
		return mv;
	}

	@RequestMapping(value = "updateEmp")
	public String updateEmp(Employee emp) {
		empService.update(emp);
		return "redirect:employee.do";
	}

	@RequestMapping(value = "delEmp")
	public String delEmp(int id) {
		empService.del(id);
		return "redirect:employee.do";
	}

	@RequestMapping(value = "delBatchEmp")
	public String delBatchEmp(String ids) {
		empService.delBatch(ids);
		return "redirect:employee.do";
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam(value = "img") MultipartFile[] myfiles) {
		String newName = "";
		try {
			String path = "E://empImg";
			MultipartFile myFile = myfiles[0];
			if (!myFile.isEmpty()) {
				String oldName = myFile.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				newName = uuid.toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
				myFile.transferTo(new File(path + "/" + newName));
			}
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return newName;
	}

	@RequestMapping(value = "ajaxUpdateEmp")
	@ResponseBody
	public String ajaxUpdateEmp(@RequestBody List<Employee> empList) {
		boolean flag = empService.updateBatch(empList);
		if (flag ) {
			return "true";
		}
		return "false";
	}

	@RequestMapping(value = "showUpdateBatchEmp")
	public ModelAndView showUpdateBatchEmp(String ids) {
		ModelAndView mv = new ModelAndView("employee/updateBatch");
		List<Department> depList = depService.search();
		List<Employee> empList = empService.searchBatch(ids);
		mv.addObject("dep", depList);
		mv.addObject("emp", empList);
		return mv;
	}

	@RequestMapping(value = "updateBatchEmp")
	public String updateBatchEmp(@RequestParam("emps") String[] emps) {
		List<Employee> empList = null;
		empService.updateBatch(empList);
		return "redirect:employee.do";
	}

}
