package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.DepartmentDao;
import dao.MybatisSqlSession;
import entity.Department;

public class testDep {

	public static void main(String[] args) {

		SqlSession session = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = session.getMapper(DepartmentDao.class);
		Department dep = new Department();
		Department dep1 = new Department();
		Department dep2 = new Department();
		List<Department> depList = new ArrayList<>();
		int a =0;
		
		a = depDao.setEmpDid("11");
		if(a>0) {
			System.out.println(a);
			a=depDao.del("11");
		}
	
		System.out.println(a);
		session.commit();
		session.close();

	}

}
