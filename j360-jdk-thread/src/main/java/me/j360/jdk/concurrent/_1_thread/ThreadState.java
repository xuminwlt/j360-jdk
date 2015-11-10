package me.j360.jdk.concurrent._1_thread;

import java.util.concurrent.TimeUnit;

/**
 * Created with j360-jdk -> me.j360.jdk.concurrent._1_thread.
 * User: min_xu
 * Date: 2015/11/10
 * Time: 11:11
 * 说明：
 */
public class ThreadState {
    public static void main(String[] args){
        new Thread(new TimeWaiting(),"TimeWaiting ").start();
        new Thread(new Waiting(),"Waiting").start();
        new Thread(new Blocked(),"Block-1").start();
        new Thread(new Blocked(),"Block-2").start();
    }

    static class TimeWaiting implements Runnable{

        @Override
        public void run() {
            while(true){
                SleepUtils.second(100);
            }
        }
    }

    static class Waiting implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable{

        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    SleepUtils.second(100);
                }
            }
        }
    }

    static class SleepUtils{
        public static final void second(long seconds){
            try {
                TimeUnit.SECONDS.sleep(seconds);
            }catch (InterruptedException e){

            }
        }
    }
}
