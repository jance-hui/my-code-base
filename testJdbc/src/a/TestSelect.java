package a;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSelect {
	// 1.����Ŀ�ϵ����Ӧ�����ݿ�jar��
	public static void main(String[] args) {
		try {
			// 2.���÷��䣬�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			// 3.�����ݿ⽨������
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?characterEncoding=utf-8","root","1234");
			//ע��Ҫ����sql���¡�("jdbc:mysql://localhost:3306���˿ںţ�/employee�����ݿ�����","�û���","����");?characterEncoding=utf-8(��ʾʹ��utf-8���룬�����Ҽӿո�)
			//4.����statement  sql���ִ����
			Statement stat = conn.createStatement();//ע��ҲҪ����sql����
			//5.ִ��sql��䣬���õ����
//			ResultSet rs = stat.executeQuery("select * from employee");//��ѯʹ��ResultSet rs = stat.executeQuery()
			int rs = stat.executeUpdate("delete from employee where id = 11");//��ɾ��ʹ��int rs = stat.executeUpdate()
			//6.�Խ�������д���
//			while (rs.next()) {
//				System.out.print(rs.getInt("id") + "\t");
//				System.out.print(rs.getString("name") + "\t");
//				System.out.print(rs.getString("sex") + "\t");
//				System.out.println(rs.getInt("age"));
//			}
			if(rs >0){
				System.out.println("����ɹ�");
			}else {
				System.out.println("����ʧ��");
			}
			//7.�ر�
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
