package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Dep2ProDao;
import entity.Project;
import service.Dep2ProService;

@Service
public class Dep2ProServiceImpl implements Dep2ProService{
	@Autowired
	Dep2ProDao d2pDao;
	@Override
	public List<Project> searchByDepartment(int d_id) {
		List<Project> proList =  d2pDao.searchByDepartment(d_id);
		return proList;
	}

	@Override
	public List<Project> searchByNotDepartment(int d_id) {
		List<Project> proList =  d2pDao.searchByNotDepartment(d_id);
		return proList;
	}

	@Override
	public boolean add(int d_id, List<String> p_id) {
		int rs =  d2pDao.add(d_id, p_id);
		return rs>0;
	}

	@Override
	public boolean del(int d_id, List<String> p_id) {
		int rs =  d2pDao.del(d_id, p_id);
		return rs>0;
	}

}
