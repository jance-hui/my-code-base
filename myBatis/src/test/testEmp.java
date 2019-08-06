package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.EmployeeDao;
import dao.MybatisSqlSession;
import entity.Employee;

public class testEmp {

	public static void main(String[] args) {
		SqlSession session = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = session.getMapper(EmployeeDao.class);
		
		Employee emp = new Employee();
		Employee emp1 = new Employee();
		Employee emp2 = new Employee();
		List<Employee> empList = new ArrayList<>();

		emp1.setId(95);
		emp1.setName("");
		emp1.setAge(22);
		emp2.setId(94);
		emp2.setName("11");
		emp2.setSex("11");
		empList = empDao.searchByEmp(emp1, 0, 5);
		for(int i=0;i<empList.size();i++) {
			System.out.println(empList.get(i).getName());
		}
		
		session.commit();
		session.close();

	}

}
