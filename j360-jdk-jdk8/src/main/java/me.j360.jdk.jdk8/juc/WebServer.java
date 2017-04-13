package me.j360.jdk.jdk8.juc;

import java.util.concurrent.*;

/**
 * Package: me.j360.jdk.jdk8.juc
 * User: min_xu
 * Date: 2017/4/13 上午10:35
 * 说明：
 */
public class WebServer {


    private ThreadPoolExecutor pool = new ThreadPoolExecutor(4,4,Long.MAX_VALUE, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
    private ThreadPoolExecutor pool2 = new ThreadPoolExecutor(4,4,Long.MAX_VALUE, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
    private ThreadPoolExecutor pool3 = new ThreadPoolExecutor(4,4,Long.MAX_VALUE, TimeUnit.SECONDS,new LinkedBlockingDeque<>());

    private SnsService snsService;

    private UserService userService;


    public static void main(String[] args) throws InterruptedException {
        WebServer server = new WebServer();
        server.setSnsService(new SnsService());
        server.setUserService(new UserService());

        int score = server.getScore(1L);

        System.out.println("score:"+score);

        server.pool.shutdownNow();
        server.pool.awaitTermination(1,TimeUnit.SECONDS);

        server.pool2.shutdownNow();
        server.pool2.awaitTermination(1,TimeUnit.SECONDS);

        server.pool3.shutdownNow();
        server.pool3.awaitTermination(1,TimeUnit.SECONDS);
    }


    private int getAnotherAge(Integer future,Long uid) {
        return future.intValue() + userService.getUserAge(uid);
    }

    public int getScore(Long uid) {

        long start = System.currentTimeMillis();
        CompletableFuture<Integer> ageFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("ageFuture:"+Thread.currentThread().getName());
            return userService.getUserAge(uid);
        },pool);

        CompletableFuture<Integer> snsFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("snsFuture:"+Thread.currentThread().getName());
            return snsService.getFollowCount(uid);},pool);

        int a = 0;
        try {
            //互斥任务
            CompletableFuture<Integer> af = ageFuture.thenCombineAsync(CompletableFuture.supplyAsync(() -> {
                System.out.println("af:"+Thread.currentThread().getName());
                return userService.getUserAge(uid);},pool3),(m,n) -> {
                System.out.println("af2:"+Thread.currentThread().getName());
                return m+n;},pool2);
            a = af.get(6,TimeUnit.SECONDS);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println((end - start)/1000+" s  互斥任务:" + a );

        int c = 0;
        try {
            CompletableFuture<Integer> sf = snsFuture.thenComposeAsync(future -> CompletableFuture.supplyAsync(() -> {
                System.out.println("sf:"+Thread.currentThread().getName());
                return getAnotherAge(future,uid);
            },pool2));
            c = sf.get(6,TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        end = System.currentTimeMillis();
        System.out.println((end - start)/1000+" s  连接任务:" + c );

        return a + c;
    }

    public void setSnsService(SnsService snsService) {
        this.snsService = snsService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
