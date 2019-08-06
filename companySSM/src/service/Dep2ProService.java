package service;

import java.util.List;

import entity.Project;

public interface Dep2ProService {
	
	public List<Project> searchByDepartment(int d_id) ;

	public List<Project> searchByNotDepartment(int d_id) ;

	public boolean add(int d_id, List<String> p_id) ;

	public boolean del(int d_id, List<String> p_id);

}
