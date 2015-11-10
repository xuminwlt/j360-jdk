package me.j360.jdk.concurrent._1_thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created with j360-jdk -> me.j360.jdk.concurrent._1_thread.
 * User: min_xu
 * Date: 2015/11/10
 * Time: 10:56
 * 说明：
 */
public class MultThread {
    public static void main(String[] args){
        //获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        for(ThreadInfo threadInfo:threadInfos){
            System.out.println("[" + threadInfo.getThreadId() + " ] " + threadInfo.getThreadName());
        }
    }
}
