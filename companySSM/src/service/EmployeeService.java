package service;

import java.util.List;

import entity.Employee;

public interface EmployeeService {

	public Employee searchEmp(int id);

	public List<Employee> searchBatch(String ids) ;
	
	public boolean add(Employee emp) ;

	public boolean update(Employee emp) ;

	public boolean updateBatch(List<Employee> empList) ;

	public boolean del(int id) ;

	public boolean delBatch(String ids);

	public int searchCountByEmp(Employee emp) ;

	public List<Employee> searchByEmp(Employee emp,int begin,int empNumInPage);
	
}
