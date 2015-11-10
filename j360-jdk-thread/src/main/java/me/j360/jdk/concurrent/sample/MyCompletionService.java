package me.j360.jdk.concurrent.sample;

import java.util.concurrent.*;

/**
 * Created with j360-jdk -> me.j360.jdk.concurrent.
 * User: min_xu
 * Date: 2015/10/18
 * Time: 16:06
 * 说明：
 */
public class MyCompletionService implements Callable<String> {
    private int id;

    public MyCompletionService(int i){
        this.id=i;
    }
    public static void main(String[] args) throws Exception{
        ExecutorService service= Executors.newCachedThreadPool();
        CompletionService<String> completion=new ExecutorCompletionService<String>(service);
        for(int i=0;i<10;i++){
            completion.submit(new MyCompletionService(i));
        }
        for(int i=0;i<10;i++){
            System.out.println(completion.take().get());
        }
        service.shutdown();
    }
    public String call() throws Exception {
        Integer time=(int)(Math.random()*1000);
        try{
            System.out.println(this.id+" start");
            Thread.sleep(time);
            System.out.println(this.id+" end");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return this.id+":"+time;
    }
}
