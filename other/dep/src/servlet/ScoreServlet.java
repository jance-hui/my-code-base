package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DepartmentDao;
import dao.ProjectDao;
import dao.ScoreDao;
import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import net.sf.json.JSONArray;
import util.Constant;
import util.Pagination;

public class ScoreServlet extends HttpServlet{
	String type = null;
	ScoreDao scDao = new ScoreDao();
	DepartmentDao depDao = new DepartmentDao();
	ProjectDao proDao = new ProjectDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		type = request.getParameter("type");
		if (type == null) {
			show(request, response);
		} else if ("update".equals(type)) {
			update(request, response);
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			String scs = request.getParameter("scs");
			List<Score> saveList = new ArrayList<>();
			JSONArray jsonArray = JSONArray.fromObject(scs);
			saveList = (List<Score>)jsonArray.toCollection(jsonArray,Score.class);
			scDao.save(saveList);
			response.sendRedirect("score");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void show(HttpServletRequest request, HttpServletResponse response) {
		List<Score> scList = new ArrayList<>();
		Score sc = new Score();
		Employee emp = new Employee();
		emp.setName(request.getParameter("empName"));
		
		Department dep = new Department();
		int dep_id = -1;
		if(request.getParameter("dep")!=null && !"".equals(request.getParameter("dep"))) {
			dep_id = Integer.parseInt(request.getParameter("dep"));
		}
		dep.setId(dep_id);
		emp.setDep(dep);
		
		Project pro = new Project();
		int pro_id = -1;
		if(request.getParameter("pro")!=null && !"".equals(request.getParameter("pro"))) {
			pro_id = Integer.parseInt(request.getParameter("pro"));
		}
		pro.setId(pro_id);
		sc.setEmp(emp);
		sc.setPro(pro);

		int ye = 1;
		if(request.getParameter("ye")!=null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}

		int count = scDao.searchCount(sc);
		Pagination p = new Pagination(ye, count, Constant.SC_NUM_IN_PAGE, Constant.SC_NUM_OF_PAGE);

		scList = scDao.searchByCondition(sc ,p.getBegin(), Constant.SC_NUM_IN_PAGE);
		List<Department> deps = depDao.search();
		List<Project> pros = proDao.search();
		request.setAttribute("dep", deps);
		request.setAttribute("pro", pros);
		request.setAttribute("p", p);
		request.setAttribute("sc", sc);
		request.setAttribute("scList", scList);
		try {
			request.getRequestDispatcher("WEB-INF/score/score.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
