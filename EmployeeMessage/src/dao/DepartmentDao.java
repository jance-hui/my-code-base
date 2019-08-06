package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Department;

public class DepartmentDao extends BaseDao{

	public List<Department> search() {// 查询
		List<Department> list= new ArrayList<Department>();
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
		}finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}
	public List<Department> searchByCondition(Department deps) {// 查询
		List<Department> list = new ArrayList<Department>();
		getConnection();
		ResultSet rs = null;
		try {
			String where = " where 1=1 ";
			if(!deps.getName().equals("")) {
				where +="and name ='"+deps.getName()+"'";
			}
			if(deps.getEmp_count() != -1) {
				where +="and emp_count ="+deps.getEmp_count();
			}
			rs = stat.executeQuery(
					"select * from department" + where);
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
			int rs = stat.executeUpdate("insert into department(name,emp_count) value('"+dep.getName()+"',"+dep.getEmp_count()+")");
			if(rs >0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, stat, null);
		}
		return flag;
	}
	public boolean amend(Department dep) {// 修改
		boolean flag = false;
		getConnection();
		try {
			int rs = stat.executeUpdate("update department set name ='"+dep.getName()+"'  where id = "+dep.getId()+"");
			if(rs >0) {
				flag = true;
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, stat, null);
		}
		return flag;
	}
	public boolean del(Department dep) {// 删除
		boolean flag = false;
		getConnection();
		try {
			conn.setAutoCommit(false);
			int rs = stat.executeUpdate("delete from department where id = "+dep.getId()+"");
			rs = stat.executeUpdate("update employee set d_id = null where d_id = "+dep.getId()+"");
			conn.commit();
			if(rs >0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, stat, null);
		}
		return flag;
	}

}
