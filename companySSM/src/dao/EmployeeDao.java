package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Employee;

public interface EmployeeDao {
	
	public Employee searchEmp(int id);

	public List<Employee> searchBatch(String ids) ;
	
	public int add(Employee emp) ;

	public int update(Employee emp) ;

	public int updateBatch(List<Employee> empList) ;

	public int del(int id) ;

	public int delBatch(String ids);

	public int searchCountByEmp(Employee emp) ;

	public List<Employee> searchByEmp(@Param(value="emp") Employee emp,@Param(value="begin") int begin,@Param(value="end") int empNumInPage);
	
}
