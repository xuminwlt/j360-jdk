package me.j360.jdk.concurrent._1_thread.connectionpool;

/**
 * Created with j360-jdk -> me.j360.jdk.concurrent._1_thread.connectionpool.
 * User: min_xu
 * Date: 2015/11/10
 * Time: 14:24
 * 说明：
 */
public interface Connection {

    public void createStatement();
    public void commit();
}
