package b;

public class MyThread extends Thread {
	int begin = 0;

	public void run() {
		for (int i = begin; i < 20; i += 2) {
			System.out.print(i+" ");
		}

	}
}
