package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Employee;

public class EmployeeDao extends BaseDao {
	public List<Employee> search() {
		List<Employee> list = new ArrayList<>();
		getConnection();
		ResultSet rs = null;
		try {
			rs = stat.executeQuery(
					"select e.*,d.id as d_id,d.name as d_name,d.emp_count as d_count from employee as e left join department as d on e.d_id = d.id");
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_name"));
				dep.setEmp_count(rs.getInt("d_count"));
				emp.setDep(dep);
				list.add(emp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;

	}

	public boolean add(Employee emp) {
		getConnection();
		boolean flag = false;
		try {
			int rs = stat.executeUpdate("insert into employee values(" + emp.getId() + ",'" + emp.getName() + "','"
					+ emp.getSex() + "'," + emp.getAge() + "," + emp.getDep().getId() + ")");
			if (rs > 0) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return flag;

	}

	public List<Employee> search(String[] ids) {
		List<Employee> eList = new ArrayList<>();
		getConnection();
		ResultSet rs = null;
		try {
			String sql = " where 1=1 ";
			sql += " and e.id=" + Integer.parseInt(ids[0]) + "";
			for (int i = 1; i < ids.length; i++) {
				int idss = Integer.parseInt(ids[i]);
				sql += " or e.id=" + idss + "";
			}
			rs = stat.executeQuery(
					"select e.*,d.id as d_id,d.name as d_name,d.emp_count as d_count from employee as e left join department as d on e.d_id = d.id "
							+ sql);
			while (rs.next()) {
				Employee emps = new Employee();
				emps.setId(rs.getInt("id"));
				emps.setName(rs.getString("name"));
				emps.setSex(rs.getString("sex"));
				emps.setAge(rs.getInt("age"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_name"));
				dep.setEmp_count(rs.getInt("d_count"));
				emps.setDep(dep);
				eList.add(emps);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return eList;

	}

	public boolean update(List<Employee> emp) {
		getConnection();
		boolean flag = false;
		try {
			for (int i = 0; i < emp.size(); i++) {
				int rs = stat.executeUpdate("update employee set name ='" + emp.get(i).getName() + "',sex ='"
						+ emp.get(i).getSex() + "',age ='" + emp.get(i).getAge() + "',d_id ="
						+ emp.get(i).getDep().getId() + " where id =" + emp.get(i).getId() + "");
				if (rs > 0) {
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return flag;

	}

	public boolean del(List<Integer> ids) {
		getConnection();
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?characterEncoding=utf-8", "root",
					"1234");
			stat = conn.createStatement();
			for (int i = 0; i < ids.size(); i++) {
				int rs = stat.executeUpdate("delete from employee where id =" + ids.get(i) + "");
				if (rs > 0) {
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return flag;

	}
}
