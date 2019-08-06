package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.User;

public class UserServlet extends HttpServlet {
	UserDao userDao = new UserDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String type = request.getParameter("type");
		if (type ==null) {
			showLogin(request, response);
		} else if ("doLogin".equals(type)){
			doLogin(request, response);
		} else if ("showAdd".equals(type)){
			showAdd(request, response);
		} else if ("add".equals(type)){
			add(request, response);
		} else if ("showUpdate".equals(type)){
			showUpdate(request, response);
		} else if ("update".equals(type)){
			update(request, response);
		}

	}
	public void showLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<User> users = userDao.search();
			request.setAttribute("users",users);
			request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void doLogin(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		boolean flag = userDao.search(user);
		try {
		if (flag) {
			request.setAttribute("user", user);
			request.getRequestDispatcher("WEB-INF/user/success.jsp").forward(request, response);
			
		} else {
			request.getRequestDispatcher("WEB-INF/user/fail.jsp").forward(request, response);
		}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/user/add.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void add(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("name");
		String password = request.getParameter("pwd");

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		boolean flag = userDao.add(user);
		try {
		if (flag) {
			request.setAttribute("username", username);
			request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(request, response);		
		} else {
			request.getRequestDispatcher("WEB-INF/user/fail.jsp").forward(request, response);
		}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name");
			User user = userDao.search(name);
			request.setAttribute("user", user);
			request.getRequestDispatcher("WEB-INF/user/update.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void update(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		User user  = new User();
		user.setId(id);
		user.setUsername(name);
		user.setPassword(pwd);

		boolean flag = userDao.update(user);
		try {
		if (flag) {
			request.setAttribute("user", user);
			request.getRequestDispatcher("WEB-INF/user/success.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/user/fail.jsp").forward(request, response);
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
