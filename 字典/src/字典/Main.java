package �ֵ�;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//���һ������޸� ������ArrayList.contains(Object)�������ж���Ŀ�Ƿ����
public class Main {
	Scanner sc = new Scanner(System.in);
	List<Dictionaries> dic = new ArrayList<>();

	public static void main(String[] args) {
		Main m = new Main();
		m.menu();
	}

	public void menu() {// ���˵�
		while (true) {
			System.out.println("��ӭʹ�ôʵ��ѯ��");
			System.out.println("==============================");
			System.out.println("��ѡ��");
			System.out.println("1.���");
			System.out.println("2.����");
			System.out.println("3.�޸�");
			System.out.println("4.ɾ��");
			System.out.println("5.�˳�");
			System.out.println("==============================");
			look();
			System.out.println("�ѱ���" + dic.size() + "������");
			int type = sc.nextInt();
			if (type == 5)
				break;
			switch (type) {
			case 1:
				input();
				break;
			case 2:
				find();
				break;
			case 3:
				modify();
				break;
			case 4:
				delete();
				break;
			default:
				break;
			}
		}
		System.out.println("ллʹ��");
	}

	public void input() {// ��ӵ���
		while (true) {
			Dictionaries dics = new Dictionaries();
			System.out.println("�������" + (dic.size() + 1) + "�����ʣ�");
			String word = sc.next();
			boolean flag = true;
			for (int i = 0; i < dic.size(); i++) {// ����Ƿ�����ͬ�ĵ����ѱ����
				Dictionaries dicc = new Dictionaries();
				dicc = dic.get(i);
				if (word.equals(dicc.getName())) {
					System.out.println("���д˵��ʣ����������롣");
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println("�����뺬�壺");
				String mean = sc.next();
				dics.setName(word);
				dics.setMean(mean);
				dic.add(dics);
				save();
				System.out.println("==============================");
				System.out.println("��ӳɹ�");
				System.out.println("�Ƿ������y/n");
				String s = sc.next();
				if (!s.equals("y"))
					break;
			}
		}

	}

	public void save() {// �����ļ�
		try {
			FileOutputStream fos = new FileOutputStream("d:/�ֵ�.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dic);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void look() {// ��ȡ�ļ�
		try {
			FileInputStream fis = new FileInputStream("d:/�ֵ�.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			dic = (ArrayList<Dictionaries>) ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void find() {// ���ҵ���
		while (true) {
			System.out.println("==============================");
			System.out.println("������Ҫ���ҵĵ��ʣ�");
			String word = sc.next();
			System.out.println("==============================");
			Dictionaries dic1 = findMean(word);
			if (dic1 != null) {
				System.out.println();
				System.out.println(dic1.getMean());
				System.out.println();
			} else {
				System.out.println("û���ҵ�");
			}
			System.out.println("==============================");
			System.out.println("�Ƿ������y/n");
			String flag = sc.next();
			if (!flag.equals("y"))
				break;
		}
	}

	public Dictionaries findMean(String word) {// ������ҵĵ���
		for (int i = 0; i < dic.size(); i++) {
			Dictionaries dics = new Dictionaries();
			dics = dic.get(i);
			if (word.equals(dics.getName())) {
				return dics;
			}
		}
		return null;
	}

	public void modify() {// �޸���Ϣ
		while (true) {
			System.out.println("==============================");
			System.out.println("������Ҫ�޸ĵĵ��ʣ�");
			String word = sc.next();
			System.out.println("==============================");
			findMean(word);
			Dictionaries dic2 = findMean(word);
			if (dic2 != null) {
				System.out.println();
				System.out.println(dic2.getMean());
				System.out.println();
				System.out.println("������Ҫ�޸ĵ�ѡ�");
				System.out.println("1.�޸ĵ���");
				System.out.println("2.�޸ĺ���");
				int ii = sc.nextInt();
				if (ii == 1) {
					System.out.println("�������µĵ��ʣ�");
					String str2 = sc.next();
					dic2.setName(str2);
				} else {
					System.out.println("�������µĴ��壺");
					String str3 = sc.next();
					dic2.setMean(str3);
				}
				save();
				System.out.println("�Ѿ�����");
			} else {
				System.out.println("û���ҵ�");
			}
			System.out.println("==============================");
			System.out.println("�Ƿ������y/n");
			String flag = sc.next();
			if (!flag.equals("y"))
				break;
		}
	}

	public void delete() {// ɾ����Ϣ
		while (true) {
			System.out.println("������Ҫɾ���ĵ��ʣ�");
			String str4 = sc.next();
			Dictionaries dic4 = new Dictionaries();
			dic4 = findMean(str4);
			if (str4 != null) {
				System.out.println(dic4.getName());
				System.out.println(dic4.getMean());
				System.out.println("�Ƿ�ɾ����y/n");
				String strr = sc.next();
				if (strr.equals("y")) {
					dic.remove(dic4);
					save();
					System.out.println("��ɾ��");
				}
			} else {
				System.out.println("û���ҵ��õ���");
			}
			System.out.println("�Ƿ������y/n");
			String str5 = sc.next();
			if (!str5.equals("y"))
				break;
		}
	}
}
