package b;

public class Test {
	public static void main(String[] args) {
		MyThread ji = new MyThread();
		ji.begin = 1;
		ji.start();
		MyThread ou = new MyThread();
		ou.begin = 0;
		ou.start();
	}
	
}
