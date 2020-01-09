package pac5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainClass {

    public static List<String> list = 
            Collections.synchronizedList(new ArrayList<String>());
    
    public static void main(String[] args) {

        //2 ������
        //1�� ����� "�������� extends Thread"
        //2�� ����� "������ extends Thread"
        
        //Main ������� 4 ������ "��������"
        //Main ������� 1 ����� "������"
        
        //��������� ��������� � List ���� ���������� �����
        //������ ���� ���� ��������� ��� ��������
        //������ ���� �� ���������
        
        //*
        //����� ������ �������� �������, 
        //�� �������� ����� �������� ���������� LIST
        //� ��
        
        new Worker().start();
        new Worker().start();
        new Worker().start();
        new Worker().start();
        
        new Car().start();
    }

}

class Worker extends Thread{
    static int num2 = 0;
    int num = 0;
    
    public Worker(){
        num2++;
        num = num2;
    }
    
    public void run(){

        System.out.println(MainClass.list.size());
        System.out.println("Worker " + num + " start working...");
        int x = (int) (Math.random() * 500) + 1; 
        try {Thread.sleep(x);} catch (InterruptedException e) {}
        MainClass.list.add("Worker " + num);
        System.out.println("Worker " + num + " stop working...");
        System.out.println(MainClass.list.size());
        if(MainClass.list.size() == 4){
            System.out.println("Work is done...");
            synchronized (MainClass.list) {
                System.out.println("asd");
                MainClass.list.notifyAll();
            }
        }
    }
}

class Car extends Thread{
    public void run(){
        System.out.println("Car is here...");
        synchronized (MainClass.list) {
            System.out.println("Car now is waiting...");
            try {MainClass.list.wait();} catch (InterruptedException e) {}
            System.out.println("����� �� ���������...");
        }
    }
}