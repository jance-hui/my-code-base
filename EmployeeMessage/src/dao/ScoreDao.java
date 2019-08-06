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

	public List<Score> search() {// 查询
		List<Score> list = new ArrayList<>();
		getConnection();
		ResultSet rs = null;
		try {
			String sql = "SELECT * from v_emp_sc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Score sc = new Score();
				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_name"));
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				sc.setValue((Integer)rs.getObject("value"));
				sc.setId(rs.getInt("s_id"));
				sc.setGrade(rs.getString("grade"));
				emp.setDep(dep);
				sc.setEmp(emp);				
				sc.setPro(pro);
				list.add(sc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public List<Score> searchByCondition(Score sc) {// 查询
		List<Score> list = new ArrayList<>();
		getConnection();
		ResultSet rs = null;
		try {
			String where = " where 1=1 ";
			if (!sc.getEmp().getName().equals("")) {
				where += " and e_name ='" + sc.getEmp().getName() + "'";
			}
			if (!sc.getEmp().getDep().getName().equals("")) {
				where += " and d_name ='" + sc.getEmp().getDep().getName() + "'";
			}
			if (!sc.getPro().getName().equals("")) {
				where += " and p_name ='" + sc.getPro().getName() + "'";
			}
			rs = stat.executeQuery("SELECT * from v_emp_sc " + where);
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

	public void save(Set<Score> saveSet) {
		for(Score sc:saveSet) {
			if(sc.getId() == 0) {
				add(sc);
			}else {
				update(sc);
			}
		}
	}
}
