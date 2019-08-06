package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ProjectDao;
import entity.Project;
import service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
	@Autowired
	ProjectDao proDao;
	@Override
	public List<Project> search() {
		List<Project> proList =  proDao.search();
		return proList;
	}

	@Override
	public List<Project> search2(String ids) {
		List<Project> proList =  proDao.search2(ids);
		return proList;
	}

	@Override
	public boolean add(Project pro) {
		int rs =  proDao.add(pro);
		return rs>0;
	}

	@Override
	public boolean del(String ids) {
		int rs =  proDao.del(ids);
		return rs>0;
	}

	@Override
	public boolean delRPid(String ids) {
		int rs =  proDao.delRPid(ids);
		return rs>0;
	}

	@Override
	public boolean update(List<Project> pro) {
		int rs =  proDao.update(pro);
		return rs>0;
	}

	@Override
	public int searchCountByPro(Project pro) {
		int rs =  proDao.searchCountByPro(pro);
		return rs;
	}

	@Override
	public List<Project> searchByPro(Project pro, int begin, int proNumInPage) {
		List<Project> proList =  proDao.searchByPro(pro, begin, proNumInPage);
		return proList;
	}

	
}
