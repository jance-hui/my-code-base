package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Department;

public interface DepartmentDao {
	
	public List<Department> search() ;

	public List<Department> search2(String ids) ;

	public int add(Department dep);

	public int updateBatch(List<Department> depList) ;
	
	public int update(Department dep) ;

	public int setEmpDid(String ids) ;
	public int del(String ids) ;
	
	public int searchCountByDep(Department dep) ;

	public List<Department> searchByDep(@Param(value="dep") Department dep,@Param(value="begin") int begin,@Param(value="end") int depNumInPage) ;
	
	public Department searchDep(Department dep) ;
}
