package pac2;

public class MainClass {

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < 100000; i++) {
			Resource res = new Resource();
			MyThread t1 = new MyThread(res);
			MyThread2 t2 = new MyThread2(res);

			t1.start();
			t2.start();

			t1.join();
			t2.join();

			if (res.getI() != 7)
				System.out.println(res.getI());
		}

	}

}

class Resource  {
	private int i = 5;

	public synchronized int getI() {
		return i;
	}

	public synchronized void setI(int i) {
		this.i = i;
	}

	public synchronized void changeValue() {
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
		;
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
