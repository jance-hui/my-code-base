package c;

public class Singleton{
	//静态私有化
	private static Singleton s;
	//构造方法私有化
	private Singleton() {
	}
	//静态方法，可以通过类名访问
	public static Singleton getInstance() {
		if(s== null) {
			s = new Singleton();
		}
		return s;
	}


}
