package b;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
//	list
	List<Employee> list= new ArrayList<Employee>();
	public void EmployeeDao() {
		
	}
	public List<Employee> load() {// 读取文档
		
		List<Employee> list= new ArrayList<>();
		FileInputStream fis;
		try {
			fis = new FileInputStream("d:/员工信息.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (ArrayList<Employee>) ois.readObject();
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void save(List<Employee> list) {// 保存文档
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("d:/员工信息.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
