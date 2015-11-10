package me.j360.jdk.concurrent._1_thread.simplepool;

/**
 * Created with j360-jdk -> me.j360.jdk.concurrent._1_thread.simplepool.
 * User: min_xu
 * Date: 2015/11/10
 * Time: 13:44
 * 说明：
 */
public interface ThreadPool<Job extends Runnable> {
    void execute(Job job);
    void shutdown();
    void addWorkers(int num);
    void removeWorker(int num);
    int getJobSize();
}
