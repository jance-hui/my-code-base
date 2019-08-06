package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.ProjectDao;
import dao.MybatisSqlSession;
import entity.Project;

public class testPro {

	public static void main(String[] args) {

		SqlSession session = MybatisSqlSession.getSqlSession();
		ProjectDao proDao = session.getMapper(ProjectDao.class);
		Project pro = new Project();
		Project pro1 = new Project();
		Project pro2 = new Project();
		List<Project> proList = new ArrayList<>();
		int a =0;
		
		a = proDao.del("10");
		if (a>0) {
			System.out.println(a);
			a= proDao.delRPid("10");
		}
		System.out.println(a);
		session.commit();
		session.close();

	}

}
