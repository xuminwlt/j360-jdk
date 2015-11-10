package me.j360.jdk.concurrent._1_thread;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created with j360-jdk -> me.j360.jdk.concurrent._1_thread.
 * User: min_xu
 * Date: 2015/11/10
 * Time: 11:30
 * 说明：
 */
public class Shutdown {
    public static void main(String[] args) throws InterruptedException {
        Runner one  = new Runner();
        Thread thread1 = new Thread(one,"CountThread");
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread1.interrupt();
        Runner two = new Runner();
        Thread thread2 = new Thread(two,"CountThread");
        thread2.start();
        TimeUnit.SECONDS.sleep(1);
        thread2.interrupt();
        two.cancel();
    }

    private static class Runner implements Runnable{

        private long i;
        private volatile boolean on = true;
        @Override
        public void run() {
            while(on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel(){
            on = false;
        }
    }
}
