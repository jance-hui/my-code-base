package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Employee;

public class EmployeeDao extends BaseDao{


	public EmployeeDao() {
	}

	public List<Employee> search() {// 查询
		List<Employee> list = new ArrayList<Employee>();
		getConnection();
		ResultSet rs = null;
		try {
			rs = stat.executeQuery(
					"select e.*,d.name as dName,emp_count from employee as e LEFT JOIN department as d\r\n"
							+ "on e.d_id = d.id \r\n" + "");// 查询使用ResultSet rs = stat.executeQuery()
			// 6.对结果集进行处理
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("dName"));
				dep.setEmp_count(rs.getInt("emp_count"));
				emp.setDep(dep);
				list.add(emp);
			}
			// 7.关闭
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}
	public List<Employee> searchByCondition(Employee emps) {// 查询
		List<Employee> list = new ArrayList<Employee>();
		getConnection();
		ResultSet rs = null;
		try {
			String where = " where 1=1 ";
			if(!emps.getName().equals("")) {
				where +="and e.name ='"+emps.getName()+"'";
			}
			if(!emps.getSex().equals("")) {
				where +="and e.sex ='"+emps.getSex()+"'";
			}
			if(emps.getAge() != -1) {
				where +="and e.age ="+emps.getAge();
			}
			if(emps.getDep().getId() != 0) {
				where +="and e.d_id ="+emps.getDep().getId();
			}
			rs = stat.executeQuery(
					"select e.*,d.name as dName,emp_count from employee as e LEFT JOIN department as d\r\n"
							+ "on e.d_id = d.id \r\n" + "" + where);// 查询使用ResultSet rs = stat.executeQuery()
			// 6.对结果集进行处理
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("dName"));
				dep.setEmp_count(rs.getInt("emp_count"));
				emp.setDep(dep);
				list.add(emp);
			}
			// 7.关闭
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public boolean add(Employee emp) {// 添加
		boolean flag = false;
		getConnection();
		try {
			int rs = stat.executeUpdate("insert into employee(name,sex,age,d_id) value('" + emp.getName() + "','"
					+ emp.getSex() + "'," + emp.getAge() + "," + emp.getDep().getId() + ")");
			if (rs > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return flag;
	}

	public boolean amend(Employee emp) {// 修改
		boolean flag = false;
		getConnection();
		try {
			int rs = stat.executeUpdate("update employee set name ='" + emp.getName() + "',sex ='" + emp.getSex()
					+ "',age =" + emp.getAge() + ",d_id =" + emp.getDep().getId() + "  where id = " + emp.getId() + "");
			if (rs > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return flag;
	}

	public boolean del(Employee	emp) {
		boolean flag = false;
		getConnection();
		try {
			int rs = stat.executeUpdate("delete from employee where id = " + emp.getId() + "");
			if (rs > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return flag;
	}

}
