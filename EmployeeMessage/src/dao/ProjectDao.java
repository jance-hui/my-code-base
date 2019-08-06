package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Project;

public class ProjectDao extends BaseDao{

	public List<Project> search() {// 查询
		List<Project> list= new ArrayList<Project>();
		getConnection();
		ResultSet rs = null;
		try {
			rs = stat.executeQuery("select * from project");
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}
	public List<Project> searchByCondition(Project pros) {// 查询
		List<Project> list = new ArrayList<Project>();
		getConnection();
		ResultSet rs = null;
		try {
			String where = " where 1=1 ";
			if(!pros.getName().equals("")) {
				where +="and name ='"+pros.getName()+"'";
			}
			rs = stat.executeQuery(
					"select * from project" + where);// 查询使用ResultSet rs = stat.executeQuery()
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public boolean add(Project pro) {// 添加
		boolean flag = false;
		getConnection();
		try {
			int rs = stat.executeUpdate("insert into project(name) value('"+pro.getName()+"')");
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
	public boolean amend(Project pro) {// 修改
		boolean flag = false;
		getConnection();
		try {
			int rs = stat.executeUpdate("update project set name ='"+pro.getName()+"'  where id = "+pro.getId()+"");
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
	public boolean del(Project pro) {// 删除
		boolean flag = false;
		getConnection();
		try {
			conn.setAutoCommit(false);
			int rs = stat.executeUpdate("delete from project where id = "+pro.getId()+"");
			rs = stat.executeUpdate("delete from r_dep_pro where p_id = "+pro.getId()+"");
			
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
