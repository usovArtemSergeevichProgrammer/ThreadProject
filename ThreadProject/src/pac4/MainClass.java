package pac4;

import com.sun.swing.internal.plaf.synth.resources.synth;

public class MainClass {

    public static void main(String[] args) throws Exception{

        for (int i = 0; i < 100000; i++) {
            Resource res = new Resource(); // i = 5
            MyThread t1 = new MyThread(res);
            MyThread2 t2 = new MyThread2(res);
            MyThread3 t3 = new MyThread3(res);
            
            t1.start(); // 5++ = 6
            t2.start(); // 6++ = 7
            t3.start(); // 7++ = 8
            
            t1.join();
            t2.join();
            t3.join();
            
            if(res.i != 8) System.out.println(res.i);
        }
    }

}

class Resource{
    
    volatile int i = 5;
    public void changeValue(){
        i++;
    }
    
}

class MyThread extends Thread{
    private Resource res; 
    
    public MyThread(Resource res){
        this.res = res;
    }
    
    public void run(){
        for (int i = 0; i < 10; i++) {
            try {sleep(500);} catch (InterruptedException e) {}
            res.changeValue(); // 5++ ... 6++ ... 7++
            System.out.println("Changed...");
        }
    }
}

class MyThread2 extends Thread{
    private Resource res; 
    
    public MyThread2(Resource res){
        
        this.res = res;
    }
    
    public void run(){
    	int value = res.i;//5
        while (true) {
        	
            
            if(value != res.i){System.out.println("t2 -> " + res.i);}
            
        }
    }
}

class MyThread3 extends Thread{
    private Resource res; 
    
    public MyThread3(Resource res){
        this.res = res;
    }
    
    public void run(){
    	int value = res.i;//5
        while (true) {
        	
           
            if(value != res.i){System.out.println("t3 -> " + res.i);}
            
        }
    }
    
}
