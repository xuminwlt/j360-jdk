package me.j360.jdk.thread;

/**
 * Created with j360-jdk -> me.j360.jdk.thread.
 * User: min_xu
 * Date: 2015/10/17
 * Time: 11:59
 * ˵����
 */
public class MyThread extends Thread{


    public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Override
    public void run(){

        threadLocal.set("test");

        System.out.println(threadLocal.get() + " MyThread.run()");
    }

}
