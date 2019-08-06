package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.EmployeeDao;
import entity.Employee;
import service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	EmployeeDao empDao;
	@Override
	public Employee searchEmp(int id) {
		Employee emp =  empDao.searchEmp(id);
		return emp;
	}

	@Override
	public List<Employee> searchBatch(String ids) {
		List<Employee> empList =  empDao.searchBatch(ids);
		return empList;
	}

	@Override
	public List<Employee> searchByEmp(Employee emp, int begin, int empNumInPage) {
		List<Employee> empList =  empDao.searchByEmp(emp, begin, empNumInPage);
		return empList;
	}

	@Override
	public boolean add(Employee emp) {
		int rs =  empDao.add(emp);
		return rs>0;
	}

	@Override
	public boolean update(Employee emp) {
		int rs =  empDao.update(emp);
		return rs>0;
	}

	@Override
	public boolean updateBatch(List<Employee> empList) {
		int rs =  empDao.updateBatch(empList);
		return rs>0;
	}

	@Override
	public boolean del(int id) {
		int rs =  empDao.del(id);
		return rs>0;
	}

	@Override
	public boolean delBatch(String ids) {
		int rs =  empDao.delBatch(ids);
		return rs>0;
	}

	@Override
	public int searchCountByEmp(Employee emp) {
		int rs =  empDao.searchCountByEmp(emp);
		return rs;
	}

}
