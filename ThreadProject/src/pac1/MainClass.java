package pac1;

public class MainClass {

	public static void main(String[] args) {
		
		System.out.println(Thread.currentThread().getName());
		
		MyThread1 t1 = new MyThread1();
		
		Thread t2 = new Thread(new MyThread2());
//		t1.setPriority(Thread.MAX_PRIORITY);
//		t2.setPriority(Thread.MIN_PRIORITY);
		
		t1.setDaemon(true);
		t2.setDaemon(true);
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("main is dead");
	
	}

}


class MyThread1 extends Thread{//наследование
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		
		for (int i = 0; i < 1000; i++) {
			System.out.println("Thread 1");
		}
	}
	
}

class MyThread2 implements Runnable{

	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName());
		for (int i = 0; i < 1000; i++) {
			System.out.println("Thread 2");
		}
		
	}
	
}