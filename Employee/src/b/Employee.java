package b;

import java.io.Serializable;

public class Employee implements Serializable {// implements ��ʾһ����ʵ����ĳ���ӿ�
	private String name;// �����Ա���� name��sex��age
	private String sex;
	private String  age;
	private String  no;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSex(String sex) {
	//	if (sex.equals("��") || sex.equals("Ů")) {
			this.sex = sex;
//		} else {
//			this.sex = "��";// �Ա����������Ĭ��Ϊ��
//		}
	}

	public String getSex() {
		return sex;
	}

	public void setAge(String age) {
			this.age = age;
	}

	public String  getAge() {
		return age;
	}
}
