package entity;


public class Employee {// implements ��ʾһ����ʵ����ĳ���ӿ�
	private String name;// �����Ա���� name��sex��age
	private String sex;
	private int  age;
	private int  id;
	private Department dep;

	public Department getDep() {
		return dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSex(String sex) {
			this.sex = sex;
	}

	public String getSex() {
		return sex;
	}

	public void setAge(int age) {
			this.age = age;
	}

	public int  getAge() {
		return age;
	}
}
