package c;

public class Singleton{
	//��̬˽�л�
	private static Singleton s;
	//���췽��˽�л�
	private Singleton() {
	}
	//��̬����������ͨ����������
	public static Singleton getInstance() {
		if(s== null) {
			s = new Singleton();
		}
		return s;
	}


}
