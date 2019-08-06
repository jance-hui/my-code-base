package c;

public class Cooker implements Runnable {
	Pack pack;

	public Cooker(Pack pack) {
		this.pack = pack;
	}

	public void run() {
		synchronized (pack) {
			while (true) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (pack.num < 20) {
					pack.num++;
					System.out.println("厨师正在做包子，还有" + pack.num + "个包子");
					if (pack.num == 10) {
						pack.notify();
					}
				}else {
					try {
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
