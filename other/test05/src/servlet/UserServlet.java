package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		String type = request.getParameter("type");
		if (type ==null) {
			showLogin(request, response);
		} else if ("doLogin".equals(type)){
			doLogin(request, response);
		}

	}
	public void showLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void doLogin(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDao userDao = new UserDao();
		boolean flag = userDao.search(username, password);
		try {
		if (flag) {
			request.setAttribute("name", username);
				request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
			
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
