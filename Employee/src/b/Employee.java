package b;

import java.io.Serializable;

public class Employee implements Serializable {// implements 表示一个类实现了某个接口
	private String name;// 定义成员变量 name、sex、age
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
	//	if (sex.equals("男") || sex.equals("女")) {
			this.sex = sex;
//		} else {
//			this.sex = "男";// 性别若输入错误，默认为男
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
