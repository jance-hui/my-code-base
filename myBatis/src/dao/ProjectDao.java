package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Project;

public interface ProjectDao {
	
	public List<Project> search() ;
	
	public List<Project> search2(String ids) ;

	public int add(Project pro) ;
	
	public int del(String ids) ;
	public int delRPid(String ids) ;
	
	public int update(List<Project> pro);
	
	public int searchCountByPro(Project pro) ;
	
	public List<Project> searchByPro(@Param(value="pro")Project pro,@Param(value="begin") int begin,@Param(value="end") int proNumInPage) ;

}
