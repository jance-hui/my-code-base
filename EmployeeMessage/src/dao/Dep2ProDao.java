package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Project;

public class Dep2ProDao extends BaseDao {

	public List<Project> searchByDepartment(Department dep) {// ��ѯ
		List<Project> list = new ArrayList<Project>();
		getConnection();
		ResultSet rs = null;
		try {
			rs = stat.executeQuery("SELECT p_id,p_name from v_dep_pro where d_id = " + dep.getId() + "");
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				list.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public List<Project> searchByNotDepartment(Department dep) {// ��ѯδ�е���Ŀ
		List<Project> newList = new ArrayList<>();
		getConnection();
		ResultSet rs = null;
		try {
			String sql = "SELECT * from project where id not in (SELECT p_id from v_dep_pro where d_id = "+dep.getId()+") ";		
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				newList.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return newList;
	}

	public boolean add(Project pro, Department dep) {// ���
		boolean flag = false;
		getConnection();
		try {
			int rs = stat
					.executeUpdate("INSERT into r_dep_pro(d_id,p_id) value(" + dep.getId() + "," + pro.getId() + ") ");
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

	public boolean del(Project pro, Department dep) {// ɾ��
		boolean flag = false;
		getConnection();
		try {
			int rs = stat.executeUpdate(
					"delete from r_dep_pro where d_id = " + dep.getId() + " and p_id = " + pro.getId() + "");
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
