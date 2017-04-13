package me.j360.jdk.jdk8.juc;

import java.util.concurrent.*;

/**
 * Package: me.j360.jdk.jdk8.juc
 * User: min_xu
 * Date: 2017/4/13 上午10:35
 * 说明：
 */
public class Server {


    private ThreadPoolExecutor pool = new ThreadPoolExecutor(4,4,Long.MAX_VALUE, TimeUnit.SECONDS,new LinkedBlockingDeque<>());


    private SnsService snsService;

    private UserService userService;


    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        server.setSnsService(new SnsService());
        server.setUserService(new UserService());

        int score = server.getScore(1L);

        System.out.println("score:"+score);

        server.pool.shutdownNow();
        server.pool.awaitTermination(1,TimeUnit.SECONDS);
    }


    public int getScore(Long uid) {

        long start = System.currentTimeMillis();

        Future<Integer> ageFuture = pool.submit(new Callable<Integer>() {
            public Integer call() {
               return userService.getUserAge(uid);
            }
        });

        Future<Integer> snsFuture = pool.submit(new Callable<Integer>() {
            public Integer call() {
                return snsService.getFollowCount(uid);
            }
        });

        int a = 0,c = 0;
        try {
            a = ageFuture.get(6,TimeUnit.SECONDS);
            c = snsFuture.get(6,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println((end - start)/1000+" s  " + a + "  " + c );

        return a + c;
    }

    public void setSnsService(SnsService snsService) {
        this.snsService = snsService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
