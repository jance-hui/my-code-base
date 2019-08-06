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
					System.out.println("�˿����ڳ԰��ӣ�����" + pack.num + "������");
//					if (pack.num == 0) {//��������Ϊ0ʱ��֪ͨ��ʦ������
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
