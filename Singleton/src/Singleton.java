
public class Singleton {
	//�������ڲ�����һ������Ķ���,�ⲿ���ɷ���
	private static Singleton s;
	//������˽�л�
	private Singleton() {
		
	}
	//���徲̬����ǰ��static����õ����Ķ���
	public static Singleton getSingleton() {
		if(s == null) {
			s = new Singleton();
		}
		return s;
	}
}
