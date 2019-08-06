package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;

public class ScoreDao extends BaseDao {

	public int searchCount(Score sc) {// 查询
		int count = 0;
		getConnection();
		ResultSet rs = null;
		try {
			String where = " where 1=1 ";
			if (sc.getEmp().getName()!=null && !"".equals(sc.getEmp().getName())) {
				where += " and e_name ='" + sc.getEmp().getName() + "'";
			}
			if (sc.getEmp().getDep().getId()!=-1) {
				where += " and d_id =" + sc.getEmp().getDep().getId() ;
			}
			if (sc.getPro().getId()!=-1) {
				where += " and p_id =" + sc.getPro().getId();
			}
			String sql = "SELECT count(*) from v_emp_sc "+where;
			rs = stat.executeQuery(sql);
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
	public List<Score> searchByCondition(Score sc ,int begin ,int size) {// 查询
		List<Score> list = new ArrayList<>();
		getConnection();
		ResultSet rs = null;
		try {
			String where = " where 1=1 ";
			if (sc.getEmp().getName()!=null && !"".equals(sc.getEmp().getName())) {
				where += " and e_name ='" + sc.getEmp().getName() + "'";
			}
			if (sc.getEmp().getDep().getId()!=-1) {
				where += " and d_id =" + sc.getEmp().getDep().getId() ;
			}
			if (sc.getPro().getId()!=-1) {
				where += " and p_id =" + sc.getPro().getId();
			}
			rs = stat.executeQuery("SELECT * from v_emp_sc " + where+" limit "+begin+","+size);
			while (rs.next()) {
				Score score = new Score();
				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_name"));
				emp.setDep(dep);
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				score.setValue((Integer)rs.getObject("value"));
				score.setId(rs.getInt("s_id"));
				score.setGrade(rs.getString("grade"));
				score.setEmp(emp);			
				score.setPro(pro);
				list.add(score);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public boolean add(Score sc) {// 增加
		boolean flag = false;
		getConnection();
		try {
			String sql = "insert into score(e_id,p_id,value) values("+sc.getEmp().getId()+","+sc.getPro().getId()+","+sc.getValue()+")";
			int rs = stat.executeUpdate(sql);
			if(rs >0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return flag ;
	}
	public boolean update(Score sc) {// 修改
		boolean flag = false;
		getConnection();
		try {
			String sql = "update score set value = "+sc.getValue()+" where id = "+sc.getId()+"";
			int rs = stat.executeUpdate(sql);
			if(rs >0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return flag ;
	}

	public void save(List<Score> saveList) {
		for(Score sc:saveList) {
			if(sc.getId() == 0) {
				add(sc);
			}else {
				update(sc);
			}
		}
	}
}
