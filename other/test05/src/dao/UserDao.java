package dao;

import java.sql.*;

public class UserDao {
	public boolean search(String username, String password) {
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?characterEncoding=utf-8", "root",
					"1234");
			stat = conn.createStatement();

			rs = stat.executeQuery(
					"SELECT username from user where username = '" + username + "' and password = '" + password + "'");
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stat.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return flag;
	}

}
