package me.j360.jdk.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with j360-jdk -> me.j360.jdk.concurrent.
 * User: min_xu
 * Date: 2015/10/18
 * Time: 15:36
 * 说明：
 */
public class MyExecutor  extends Thread {
    private int index;
    public MyExecutor(int i){
        this.index=i;
    }
    public void run(){
        try{
            System.out.println("["+this.index+"] start....");
            Thread.sleep((int)(Math.random()*1000));
            System.out.println("["+this.index+"] end.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        ExecutorService service = Executors.newFixedThreadPool(4);
        for(int i=0;i<10;i++){
            service.execute(new MyExecutor(i));
        }
        System.out.println("submit finish");
        service.shutdown();
    }
}
