package entity;

public class Employee {// implements 表示一个类实现了某个接口
	private String name;// 定义成员变量 name、sex、age
	private String sex;
	private Integer age;
	private Integer id;
	private Department dep;
	private String img;

	public Department getDep() {
		return dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public void setAge(Integer age) {
			this.age = age;
	}

	public Integer  getAge() {
		return age;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}
