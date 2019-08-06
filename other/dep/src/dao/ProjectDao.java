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
	public List<Project> search(String ids) {// 查询
		List<Project> list= new ArrayList<Project>();
		getConnection();
		ResultSet rs = null;
		try {
			rs = stat.executeQuery("select * from project where id in("+ids+")");
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
	public boolean del(String ids) {// 删除
		boolean flag = false;
		getConnection();
		try {
			conn.setAutoCommit(false);
			int rs = stat.executeUpdate("delete from project where id in( "+ids+")");
			rs = stat.executeUpdate("delete from r_dep_pro where p_id in( "+ids+")");
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
	public boolean update(List<Project> pro) { //修改
		boolean flag = false;
		getConnection();
		try {
			for(int i=0;i<pro.size();i++) {
			int rs = stat.executeUpdate("update project set name ='"+pro.get(i).getName()+"'  where id = "+pro.get(i).getId()+"");
			if(rs >0) {
				flag = true;
			}
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, stat, null);
		}
		return flag;
	}
	public int searchCountByDep(Project pro) {
		int count = 0;
		getConnection();
		ResultSet rs = null;
		try {
			String where = " where 1=1 ";
			if(pro.getName()!=null&&!pro.getName().equals("")) {
				where +="and name ='"+pro.getName()+"'";
			}
			rs = stat.executeQuery(
					"select count(*) from project" + where);// 查询使用ResultSet rs = stat.executeQuery()
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
	public List<Project> searchByDep(Project pro, int begin, int proNumInPage) {
		List<Project> proList = new ArrayList<>(); 
		getConnection();
		ResultSet rs = null;
		try {
			String where = " where 1=1 ";
			if(pro.getName()!=null&&!pro.getName().equals("")) {
				where +="and name ='"+pro.getName()+"'";
			}
			rs = stat.executeQuery(
					"select * from project " + where+" limit "+begin+","+proNumInPage+"");// 查询使用ResultSet rs = stat.executeQuery()
			while (rs.next()) {
				Project pros = new Project();
				pros.setId(rs.getInt("id"));
				pros.setName(rs.getString("name"));
				proList.add(pros);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return proList;
	}

}
