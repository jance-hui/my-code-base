package a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelect {
	// 1.在项目上导入对应的数据库jar包
	public static void main(String[] args) {
		try {
			// 2.利用反射，加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.与数据库建立连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?characterEncoding=utf-8","root","1234");
			//注：要导入sql包下。("jdbc:mysql://localhost:3306（端口号）/employee（数据库名）","用户名","密码");?characterEncoding=utf-8(表示使用utf-8编码，不能乱加空格)
			//4.建立statement  sql语句执行器
			Statement stat = conn.createStatement();//注：也要导入sql包下
			//5.执行sql语句，并得到结果
//			ResultSet rs = stat.executeQuery("select * from employee");//查询使用ResultSet rs = stat.executeQuery()
			int rs = stat.executeUpdate("delete from employee where id = 11");//增删改使用int rs = stat.executeUpdate()
			//6.对结果集进行处理
//			while (rs.next()) {
//				System.out.print(rs.getInt("id") + "\t");
//				System.out.print(rs.getString("name") + "\t");
//				System.out.print(rs.getString("sex") + "\t");
//				System.out.println(rs.getInt("age"));
//			}
			if(rs >0){
				System.out.println("保存成功");
			}else {
				System.out.println("保存失败");
			}
			//7.关闭
		//	rs.close();
			stat.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
