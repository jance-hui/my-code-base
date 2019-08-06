package service;

import java.util.List;

import entity.Project;

public interface ProjectService {
public List<Project> search() ;
	
	public List<Project> search2(String ids) ;

	public boolean add(Project pro) ;
	
	public boolean del(String ids) ;
	public boolean delRPid(String ids) ;
	
	public boolean update(List<Project> pro);
	
	public int searchCountByPro(Project pro) ;
	
	public List<Project> searchByPro(Project pro,int begin,int proNumInPage) ;

}
