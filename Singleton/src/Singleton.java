
public class Singleton {
	//单例类内部创建一个本身的对象,外部不可访问
	private static Singleton s;
	//构造器私有化
	private Singleton() {
		
	}
	//定义静态方法前加static，获得单例的对象。
	public static Singleton getSingleton() {
		if(s == null) {
			s = new Singleton();
		}
		return s;
	}
}
