
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String username = "";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/employee?characterEncoding=utf-8", "root", "1234");
		Statement stat = conn.createStatement();
		
		String name = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		
		ResultSet rs = stat.executeQuery("SELECT username from user where username = '"+name+"' and password = '"+pwd+"'");
		while(rs.next()){
			username = rs.getString("username");
		}
		if(!"".equals(username)){
		//	out.print("登录成功");
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}else{
		//	out.print("登录失败");
			request.getRequestDispatcher("fail.jsp").forward(request, response);
		}
		rs.close();
		stat.close();
		conn.close();
	%>
</body>
</html>