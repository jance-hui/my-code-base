package service;

import java.util.List;

import entity.Department;

public interface DepartmentService {
	
	public List<Department> search() ;

	public List<Department> search2(String ids) ;

	public boolean add(Department dep);

	public boolean updateBatch(List<Department> depList) ;
	
	public boolean update(Department dep) ;

	public boolean del(String ids) ;
	
	public int searchCountByDep(Department dep) ;

	public List<Department> searchByDep( Department dep,int begin,int depNumInPage) ;
	
	public Department searchDep(Department dep) ;
}
