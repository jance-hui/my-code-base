package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.Dep2ProDao;
import dao.MybatisSqlSession;
import entity.Project;

public class testD2P {

	public static void main(String[] args) {

		SqlSession session = MybatisSqlSession.getSqlSession();
		Dep2ProDao d2pDao = session.getMapper(Dep2ProDao.class);
		Project pro = new Project();
		Project pro1 = new Project();
		Project pro2 = new Project();
		List<Project> proList = new ArrayList<>();
		int a=0;
		
		List<String> strList = new ArrayList<>();
		strList.add("2");
		strList.add("6");
		for(int i=0;i<strList.size();i++) {
			System.out.print(strList.get(i));
		}
		a = d2pDao.del(6, strList);
		
		System.out.println(a);
		session.commit();
		session.close();

	}

}
