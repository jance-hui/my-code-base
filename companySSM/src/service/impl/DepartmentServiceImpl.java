package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Department;
import service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
	EmployeeDao empDao;
	@Autowired
	DepartmentDao depDao;
	@Override
	public List<Department> search() {
		List<Department> depList = depDao.search();
		return depList;
	}

	@Override
	public List<Department> search2(String ids) {
		List<Department> depList = depDao.search2(ids);
		return depList;
	}

	@Override
	public boolean add(Department dep) {
		int rs = depDao.add(dep);
		return rs>0;
	}

	@Override
	public boolean updateBatch(List<Department> depList) {
		int rs = depDao.updateBatch(depList);
		return rs>0;
	}

	@Override
	public boolean update(Department dep) {
		int rs = depDao.update(dep);
		return rs>0;
	}

	@Override
	public boolean del(String ids) {
		int rs = depDao.del(ids);
		if(rs>0) {
			rs = depDao.setEmpDid(ids);
		}
		return rs>0;
	}

	@Override
	public int searchCountByDep(Department dep) {
		int rs = depDao.searchCountByDep(dep);
		return rs;
	}

	@Override
	public List<Department> searchByDep(Department dep, int begin, int depNumInPage) {
		List<Department> depList = depDao.searchByDep(dep, begin, depNumInPage);
		return depList;
	}

	@Override
	public Department searchDep(Department dep) {
		Department dep2 = depDao.searchDep(dep);
		return dep2;
	}


}
