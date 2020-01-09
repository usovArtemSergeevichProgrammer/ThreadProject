package pac3;

import pac3.MyThread;
import pac3.MyThread2;
import pac3.Resource;

public class MainClass {

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < 100000; i++) {
			Resource res = new Resource();
			MyThread t1 = new MyThread(res);
			MyThread2 t2 = new MyThread2(res);
			MyThread3 t3 = new MyThread3(res);

			t1.start();// 5++ = 6
			t2.start();// 6++ = 7
			t3.start();// 7++ = 8

			t1.join();
			t2.join();
			t3.join();

			if (res.getI() != 8)
				System.out.println(res.getI());
		}

	}

}

class Resource {
	private static int i = 5;

	public Resource(){
		i = 5;
	}
	public synchronized int getI() {
		return i;
	}

	public synchronized void setI(int i) {
		this.i = i;
	}

	public synchronized void changeValue() {
		i++;
	}
	
	public static synchronized void changeStaticValue() {
		i++;
	}
}

class MyThread extends Thread {
	private Resource res;

	public MyThread(Resource res) {
		this.res = res;
	}

	public void run() {
		res.changeValue();
		
	}
}

class MyThread2 extends Thread {
	private Resource res;

	public MyThread2(Resource res) {
		this.res = res;
	}

	public void run() {
		synchronized (res) {
			res.setI(res.getI() + 1);
		}
		
	}
}

class MyThread3 extends Thread {
	private Resource res;

	public MyThread3(Resource res) {
		this.res = res;
	}

	public void run() {
		Resource.changeStaticValue();
		
	}
}
