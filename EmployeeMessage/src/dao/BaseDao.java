package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
	Connection conn;
	Statement stat;
	public void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/employee?characterEncoding=utf-8", "root", "1234");
			stat = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void closeAll(Connection conn, Statement stat, ResultSet rs) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
