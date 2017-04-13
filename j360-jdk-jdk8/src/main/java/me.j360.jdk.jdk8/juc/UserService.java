package me.j360.jdk.jdk8.juc;

import java.util.concurrent.TimeUnit;

/**
 * Package: me.j360.jdk.jdk8.juc
 * User: min_xu
 * Date: 2017/4/13 上午10:40
 * 说明：
 */
public class UserService {

    public int getUserAge(Long uid) {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }



    public String getUserName(Long uid) {
        return String.valueOf(Math.random());
    }
}
