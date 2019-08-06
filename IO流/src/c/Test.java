package c;

public class Test {
	public static void main(String[] args) {
		Pack pack = new Pack();
		pack.num = 20;
		Cooker cooker = new Cooker(pack);
		Thread thread1= new Thread(cooker);
		Customer customer = new Customer(pack);
		Thread thread2 = new Thread(customer);
		
		thread1.start();
		thread2.start();
	}
}
