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
	public List<Employee> load() {// ��ȡ�ĵ�
		
		List<Employee> list= new ArrayList<>();
		FileInputStream fis;
		try {
			fis = new FileInputStream("d:/Ա����Ϣ.txt");
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

	public void save(List<Employee> list) {// �����ĵ�
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("d:/Ա����Ϣ.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
