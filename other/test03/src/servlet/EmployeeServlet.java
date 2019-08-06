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
		} else if ("showDel".equals(type)) {
			showDel(request, response);
		} else if ("del".equals(type)) {
			del(request, response);
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

	public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		String[] ids = request.getParameterValues("id");
		List<Employee> emp = empDao.search(ids);
		request.setAttribute("emp", emp);
		try {
			request.getRequestDispatcher("WEB-INF/update.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showDel(HttpServletRequest request, HttpServletResponse response) {
		String[] ids = request.getParameterValues("id");
		List<Employee> emp = empDao.search(ids);
		request.setAttribute("emp", emp);
		try {
			request.getRequestDispatcher("WEB-INF/del.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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

	public void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Employee> eList = new ArrayList<>();
			String[] ids = request.getParameterValues("id");
			String[] names = request.getParameterValues("name");
			String[] ages = request.getParameterValues("age");
			String[] deps = request.getParameterValues("dep");
			boolean flag = false;
			for (int i = 0; i < ids.length; i++) {
				Employee emp = new Employee();
				Department dep = new Department();
				emp.setId(Integer.parseInt(ids[i]));
				emp.setName(names[i]);
				emp.setAge(Integer.parseInt(ages[i]));
				emp.setSex(request.getParameter("sex"+i+""));
				dep.setId(Integer.parseInt(deps[i]));

				emp.setDep(dep);
				eList.add(emp);
			}
			flag = empDao.update(eList);
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
			List<Integer> eList = new ArrayList<>();
			String[] ids = request.getParameterValues("id");
			for (int i = 0; i < ids.length; i++) {
				int id = Integer.parseInt(ids[i]);
				eList.add(id);
			}
			flag = empDao.del(eList);
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
