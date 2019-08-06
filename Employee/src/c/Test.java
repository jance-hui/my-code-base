package c;

public class Test extends Thread {
	public void run() { 
		System.out.println(Singleton.getInstance());
	}
	
	public static void main(String[] args) { 
		
		Test[] mts = new Test[10];
		for(int i = 0 ; i < mts.length ; i++){
			mts[i] = new Test();
		}
		
		for (int j = 0; j < mts.length; j++) {
			mts[j].start();
		}
	}
}
