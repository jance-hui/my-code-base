package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Project;

public interface Dep2ProDao {
	
	public List<Project> searchByDepartment(@Param(value="d_id")int d_id) ;

	public List<Project> searchByNotDepartment(@Param(value="d_id")int d_id) ;

	public int add(@Param(value="d_id")int d_id,@Param(value="p_id") List<String> p_id) ;

	public int del(@Param(value="did")int d_id,@Param(value="pid") List<String> p_id);

}
