package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import net.sf.json.JSONArray;

public class EmployeeServlet extends HttpServlet {
	EmployeeDao empDao = new EmployeeDao();
	String type = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		type = request.getParameter("type");
		if (type == null) {
			show(request, response);
		} else if ("showAdd".equals(type)) {
			showAdd(request, response);
		} else if ("add".equals(type)) {
			add(request, response);
		} else if ("showUpdate".equals(type)) {
			showUpdate(request, response);
		} else if ("update".equals(type)) {
			update(request, response);
		} else if ("showUpdateBatch".equals(type)) {
			showUpdateBatch(request, response);
		} else if ("updateBatch".equals(type)) {
			updateBatch(request, response);
		} else if ("updateBatch2".equals(type)) {
			updateBatch2(request, response);
		}else if ("del".equals(type)) {
			del(request, response);
		} else if ("delBatch".equals(type)) {
			delBatch(request, response);
		}

	}

	public void show(HttpServletRequest request, HttpServletResponse response) {
		List<Employee> list = new ArrayList<>();
		list = empDao.search();
		request.setAttribute("emps", list);
		try {
			request.getRequestDispatcher("WEB-INF/employee.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/add.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			Employee emp = new Employee();
			boolean flag = false;
			Department dep = new Department();
			emp.setName(request.getParameter("name"));
			emp.setAge(Integer.parseInt(request.getParameter("age")));
			emp.setSex(request.getParameter("sex"));
			dep.setId(Integer.parseInt(request.getParameter("dep")));
			emp.setDep(dep);
			flag = empDao.add(emp);
			if (flag) {
				response.sendRedirect("employee");
			} else {
				request.getRequestDispatcher("WEB-INF/fail.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Employee emp = new Employee();
		emp = empDao.search(id);
		request.setAttribute("emp", emp);
		try {
			request.getRequestDispatcher("WEB-INF/update.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean flag = false;
			Employee emp = new Employee();
			Department dep = new Department();
			emp.setId(Integer.parseInt(request.getParameter("id")));
			emp.setName(request.getParameter("name"));
			emp.setAge(Integer.parseInt(request.getParameter("age")));
			emp.setSex(request.getParameter("sex"));
			dep.setId(Integer.parseInt(request.getParameter("dep")));
			emp.setDep(dep);
			flag = empDao.update(emp);
			if (flag) {
				response.sendRedirect("employee");
			} else {
				request.getRequestDispatcher("WEB-INF/fail.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showUpdateBatch(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");
		List<Employee> emp = empDao.searchBatch(ids);
		request.setAttribute("emp", emp);
		try {
			request.getRequestDispatcher("WEB-INF/updateBatch.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateBatch(HttpServletRequest request, HttpServletResponse response) {
		try {
			String emps = request.getParameter("emps");
			String[] empss = emps.split(";");
			boolean flag = false;
			List<Employee> eList = new ArrayList<>();

			for (int i = 0; i < empss.length; i++) {
				Employee emp = new Employee();
				Department dep = new Department();
				String[] temp = empss[i].split(",");
				emp.setId(Integer.parseInt(temp[0]));
				emp.setName(temp[1]);
				emp.setSex(temp[2]);
				emp.setAge(Integer.parseInt(temp[3]));
				dep.setId(Integer.parseInt(temp[4]));
				emp.setDep(dep);
				eList.add(emp);
			}
			flag = empDao.updateBatch(eList);
			if (flag) {
				response.sendRedirect("employee");
			} else {
				request.getRequestDispatcher("WEB-INF/fail.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void updateBatch2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String emps = request.getParameter("emps");
			boolean flag = false;

			JSONArray jsonArray = JSONArray.fromObject(emps);
			List<Employee> eList = (List<Employee>)jsonArray.toCollection(jsonArray,Employee.class);
			flag = empDao.updateBatch(eList);
			if (flag) {
				response.sendRedirect("employee");
			} else {
				request.getRequestDispatcher("WEB-INF/fail.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void del(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean flag = false;
			int id = Integer.parseInt(request.getParameter("id"));
			flag = empDao.del(id);
			if (flag) {
				response.sendRedirect("employee");
			} else {
				request.getRequestDispatcher("WEB-INF/fail.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delBatch(HttpServletRequest request, HttpServletResponse response) {
		try {
			boolean flag = false;
			String ids = request.getParameter("ids");
			flag = empDao.delBatch(ids);
			if (flag) {
				response.sendRedirect("employee");
			} else {
				request.getRequestDispatcher("WEB-INF/fail.jsp").forward(request, response);
			}
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
