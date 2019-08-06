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
					"select e.*,d.id as d_id,d.name as d_name,d.emp_count as d_count from employee as e left join department as d on e.d_id = d.id ");
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

	public int searchCount() {
		int count = 0;
		getConnection();
		ResultSet rs = null;
		try {
			rs = stat.executeQuery("select count(*) from employee");
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return count;
	}

	public List<Employee> search(int begin, int empNumInPage) {
		List<Employee> list = new ArrayList<>();
		getConnection();
		ResultSet rs = null;
		try {
			rs = stat.executeQuery(
					"select e.*,d.id as d_id,d.name as d_name,d.emp_count as d_count from employee as e left join department as d on e.d_id = d.id limit "
							+ begin + "," + empNumInPage + "");
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
			int rs = stat.executeUpdate("insert into employee(name,sex,age,d_id) values('" + emp.getName() + "','"
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

	public Employee searchEmp(int id) {
		Employee emp = new Employee();
		getConnection();
		ResultSet rs = null;
		try {
			rs = stat.executeQuery(
					"select e.*,d.id as d_id,d.name as d_name,d.emp_count as d_count from employee as e left join department as d on e.d_id = d.id where e.id ="
							+ id);
			while (rs.next()) {
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_name"));
				dep.setEmp_count(rs.getInt("d_count"));
				emp.setDep(dep);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return emp;
	}

	public List<Employee> searchBatch(String ids) {
		List<Employee> list = new ArrayList<>();
		getConnection();
		ResultSet rs = null;
		try {
			rs = stat.executeQuery(
					"select e.*,d.id as d_id,d.name as d_name,d.emp_count as d_count from employee as e left join department as d on e.d_id = d.id where e.id in("
							+ ids + ")");
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

	public boolean update(Employee emp) {
		getConnection();
		boolean flag = false;
		try {
			int rs = stat.executeUpdate("update employee set name ='" + emp.getName() + "',sex ='" + emp.getSex()
					+ "',age ='" + emp.getAge() + "',d_id =" + emp.getDep().getId() + " where id =" + emp.getId() + "");
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

	public boolean updateBatch(List<Employee> emp) {
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

	public boolean del(int id) {
		getConnection();
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?characterEncoding=utf-8", "root",
					"1234");
			stat = conn.createStatement();
			int rs = stat.executeUpdate("delete from employee where id =" + id + "");
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

	public boolean delBatch(String ids) {
		getConnection();
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?characterEncoding=utf-8", "root",
					"1234");
			stat = conn.createStatement();
			int rs = stat.executeUpdate("delete from employee where id in(" + ids + ")");
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

	public int searchCountByEmp(Employee emp) {
		int count = 0;
		getConnection();
		ResultSet rs = null;
		try {
			String where = " where 1=1 ";
			if(emp.getName()!=null&&!emp.getName().equals("")) {
				where +="and name ='"+emp.getName()+"'";
			}
			if(emp.getSex()!=null&&!emp.getSex().equals("")) {
				where +="and sex ='"+emp.getSex()+"'";
			}
			if(emp.getAge() != -1) {
				where +="and age ="+emp.getAge();
			}
			if(emp.getDep().getId() != 0) {
				where +="and d_id ="+emp.getDep().getId();
			}
			rs = stat.executeQuery("select count(*) from employee "+where);
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return count;
	}

	public List<Employee> searchByEmp(Employee emp, int begin, int empNumInPage) {
		List<Employee> list = new ArrayList<>();
		getConnection();
		ResultSet rs = null;
		try {
			String where = " where 1=1 ";
			if(emp.getName()!=null&&!emp.getName().equals("")) {
				where +="and e.name ='"+emp.getName()+"'";
			}
			if(emp.getSex()!=null&&!emp.getSex().equals("")) {
				where +="and e.sex ='"+emp.getSex()+"'";
			}
			if(emp.getAge() != -1) {
				where +="and e.age ="+emp.getAge();
			}
			if(emp.getDep().getId() != 0) {
				where +="and e.d_id ="+emp.getDep().getId();
			}
			rs = stat.executeQuery(
					"select e.*,d.id as d_id,d.name as d_name,d.emp_count as d_count from employee as e left join department as d on e.d_id = d.id "+where+" limit "
							+ begin + "," + empNumInPage + "");
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
				list.add(emps);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

}
