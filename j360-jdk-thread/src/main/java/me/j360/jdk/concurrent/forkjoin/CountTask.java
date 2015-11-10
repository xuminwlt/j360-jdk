package me.j360.jdk.concurrent.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * Created with j360-jdk -> me.j360.jdk.concurrent.forkjoin.
 * User: min_xu
 * Date: 2015/11/10
 * Time: 17:34
 * 说明：
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2; //阈值
    private int start;
    private int end;

    public CountTask(int start,int end){
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        return null;
    }
}
