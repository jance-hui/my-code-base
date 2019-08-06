package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Project;

public class Dep2ProDao extends BaseDao {

	public List<Project> searchByDepartment(int id) {// ��ѯ
		List<Project> list = new ArrayList<Project>();
		getConnection();
		ResultSet rs = null;
		try {
			rs = stat.executeQuery("SELECT p_id,p_name from v_dep_pro where d_id = " + id + "");
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

	public List<Project> searchByNotDepartment(int id) {// ��ѯδ�е���Ŀ
		List<Project> newList = new ArrayList<>();
		getConnection();
		ResultSet rs = null;
		try {
			String sql = "SELECT * from project where id not in (SELECT p_id from v_dep_pro where d_id = "+id+") ";		
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

	public boolean add(int d_id, int p_id) {// ���
		boolean flag = false;
		getConnection();
		try {
			int rs = stat
					.executeUpdate("INSERT into r_dep_pro(d_id,p_id) value(" + d_id + "," + p_id + ") ");
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

	public boolean del(int d_id, String p_id) {// ɾ��
		boolean flag = false;
		getConnection();
		try {
			int rs = stat.executeUpdate(
					"delete from r_dep_pro where d_id = " + d_id + " and p_id in(" + p_id + ")");
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
