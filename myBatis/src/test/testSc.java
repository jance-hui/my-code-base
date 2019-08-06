package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.ScoreDao;
import dao.MybatisSqlSession;
import entity.Score;

public class testSc {

	public static void main(String[] args) {

		SqlSession session = MybatisSqlSession.getSqlSession();
		ScoreDao scDao = session.getMapper(ScoreDao.class);
		Score sc = new Score();
		List<Score> scList = new ArrayList<>();
		
		scList = scDao.searchByCondition(sc, 30, 5);
		
		System.out.println(scList.size());
		System.out.println("s_id\t姓名\t项目\t\t成绩");
		for (int i = 0; i < scList.size(); i++) {
			//System.out.println(scList.get(i));
			System.out.println(scList.get(i).getId()+"\t"+scList.get(i).getEmp().getName()+"\t"+scList.get(i).getPro().getName()+"\t\t"+scList.get(i).getValue());
		}
		session.commit();
		session.close();

	}

}
