package c;

public class Customer implements Runnable {

	Pack pack;

	public Customer(Pack pack) {
		this.pack = pack;
	}

	public void run() {
		synchronized (pack) {

			while (true) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (pack.num > 0) {
					pack.num--;
					System.out.println("顾客正在吃包子，还有" + pack.num + "个包子");
//					if (pack.num == 0) {//当包子数为0时，通知厨师做包子
//
//					}
				} else {
					try {
						pack.notify();
						pack.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}
