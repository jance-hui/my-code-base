package 字典;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//查找还可以修改 ，利用ArrayList.contains(Object)方法，判断项目是否存在
public class Main {
	Scanner sc = new Scanner(System.in);
	List<Dictionaries> dic = new ArrayList<>();

	public static void main(String[] args) {
		Main m = new Main();
		m.menu();
	}

	public void menu() {// 主菜单
		while (true) {
			System.out.println("欢迎使用词典查询：");
			System.out.println("==============================");
			System.out.println("请选择：");
			System.out.println("1.添加");
			System.out.println("2.查找");
			System.out.println("3.修改");
			System.out.println("4.删除");
			System.out.println("5.退出");
			System.out.println("==============================");
			look();
			System.out.println("已保存" + dic.size() + "个单词");
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
		System.out.println("谢谢使用");
	}

	public void input() {// 添加单词
		while (true) {
			Dictionaries dics = new Dictionaries();
			System.out.println("请输入第" + (dic.size() + 1) + "个单词：");
			String word = sc.next();
			boolean flag = true;
			for (int i = 0; i < dic.size(); i++) {// 检测是否有相同的单词已被添加
				Dictionaries dicc = new Dictionaries();
				dicc = dic.get(i);
				if (word.equals(dicc.getName())) {
					System.out.println("已有此单词，请重新输入。");
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println("请输入含义：");
				String mean = sc.next();
				dics.setName(word);
				dics.setMean(mean);
				dic.add(dics);
				save();
				System.out.println("==============================");
				System.out.println("添加成功");
				System.out.println("是否继续：y/n");
				String s = sc.next();
				if (!s.equals("y"))
					break;
			}
		}

	}

	public void save() {// 保存文件
		try {
			FileOutputStream fos = new FileOutputStream("d:/字典.txt");
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

	public void look() {// 读取文件
		try {
			FileInputStream fis = new FileInputStream("d:/字典.txt");
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

	public void find() {// 查找单词
		while (true) {
			System.out.println("==============================");
			System.out.println("请输入要查找的单词：");
			String word = sc.next();
			System.out.println("==============================");
			Dictionaries dic1 = findMean(word);
			if (dic1 != null) {
				System.out.println();
				System.out.println(dic1.getMean());
				System.out.println();
			} else {
				System.out.println("没有找到");
			}
			System.out.println("==============================");
			System.out.println("是否继续：y/n");
			String flag = sc.next();
			if (!flag.equals("y"))
				break;
		}
	}

	public Dictionaries findMean(String word) {// 输出查找的单词
		for (int i = 0; i < dic.size(); i++) {
			Dictionaries dics = new Dictionaries();
			dics = dic.get(i);
			if (word.equals(dics.getName())) {
				return dics;
			}
		}
		return null;
	}

	public void modify() {// 修改信息
		while (true) {
			System.out.println("==============================");
			System.out.println("请输入要修改的单词：");
			String word = sc.next();
			System.out.println("==============================");
			findMean(word);
			Dictionaries dic2 = findMean(word);
			if (dic2 != null) {
				System.out.println();
				System.out.println(dic2.getMean());
				System.out.println();
				System.out.println("请输入要修改的选项：");
				System.out.println("1.修改单词");
				System.out.println("2.修改含义");
				int ii = sc.nextInt();
				if (ii == 1) {
					System.out.println("请输入新的单词：");
					String str2 = sc.next();
					dic2.setName(str2);
				} else {
					System.out.println("请输入新的词义：");
					String str3 = sc.next();
					dic2.setMean(str3);
				}
				save();
				System.out.println("已经保存");
			} else {
				System.out.println("没有找到");
			}
			System.out.println("==============================");
			System.out.println("是否继续：y/n");
			String flag = sc.next();
			if (!flag.equals("y"))
				break;
		}
	}

	public void delete() {// 删除信息
		while (true) {
			System.out.println("请输入要删除的单词：");
			String str4 = sc.next();
			Dictionaries dic4 = new Dictionaries();
			dic4 = findMean(str4);
			if (str4 != null) {
				System.out.println(dic4.getName());
				System.out.println(dic4.getMean());
				System.out.println("是否删除：y/n");
				String strr = sc.next();
				if (strr.equals("y")) {
					dic.remove(dic4);
					save();
					System.out.println("已删除");
				}
			} else {
				System.out.println("没有找到该单词");
			}
			System.out.println("是否继续：y/n");
			String str5 = sc.next();
			if (!str5.equals("y"))
				break;
		}
	}
}
