package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Department;

public class DepartmentDao extends BaseDao {

	public List<Department> search() {// 查询
		List<Department> list = new ArrayList<Department>();
		getConnection();
		ResultSet rs = null;
		try {
			rs = stat.executeQuery("select * from department");
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmp_count(rs.getInt("emp_count"));
				list.add(dep);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public List<Department> search(String ids) {// 查询
		List<Department> list = new ArrayList<Department>();
		getConnection();
		ResultSet rs = null;
		try {
			rs = stat.executeQuery("select * from department where id in(" + ids + ")");
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmp_count(rs.getInt("emp_count"));
				list.add(dep);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public boolean add(Department dep) {// 添加
		boolean flag = false;
		getConnection();
		try {
			int rs = stat.executeUpdate(
					"insert into department(name,emp_count) value('" + dep.getName() + "'," + dep.getEmp_count() + ")");
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

	public boolean update(List<Department> dep) {// 修改
		boolean flag = false;
		getConnection();
		try {
			for (int i = 0; i < dep.size(); i++) {
				int rs = stat.executeUpdate("update department set name ='" + dep.get(i).getName() + "'  where id = "
						+ dep.get(i).getId() + "");
				if (rs > 0) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return flag;
	}

	public boolean del(String ids) {// 删除
		boolean flag = false;
		getConnection();
		try {
			conn.setAutoCommit(false);
			int	rs = stat.executeUpdate("update employee set d_id = null where d_id in( " + ids + ")");
			 rs = stat.executeUpdate("delete from department where id in( " + ids + ")");
			conn.commit();
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

	public int searchCountByDep(Department dep) {
		int count = 0;
		getConnection();
		ResultSet rs = null;
		try {
			String where = " where 1=1 ";
			if (dep.getName()!=null&&!dep.getName().equals("")) {
				where += "and name ='" + dep.getName() + "'";
			}
			if (dep.getEmp_count() != -1) {
				where += "and ifnull(emp_count,0) =" + dep.getEmp_count();
			}
			rs = stat.executeQuery("select count(*) from department" + where);
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return count;
	}

	public List<Department> searchByDep(Department dep, int begin, int depNumInPage) {
		List<Department> list = new ArrayList<Department>();
		getConnection();
		ResultSet rs = null;
		try {
			String where = " where 1=1 ";
			if (dep.getName()!=null&&!dep.getName().equals("")) {
				where += "and name ='" + dep.getName() + "'";
			}
			if (dep.getEmp_count() != -1) {
				where += "and ifnull(emp_count,0) =" + dep.getEmp_count();
			}
			rs = stat.executeQuery("select * from department "+where+" limit " + begin + "," + depNumInPage + "");
			while (rs.next()) {
				Department deps = new Department();
				deps.setId(rs.getInt("id"));
				deps.setName(rs.getString("name"));
				deps.setEmp_count(rs.getInt("emp_count"));
				list.add(deps);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

}
