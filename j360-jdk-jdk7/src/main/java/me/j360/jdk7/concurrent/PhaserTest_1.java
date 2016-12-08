package me.j360.jdk7.concurrent;

import java.util.concurrent.Phaser;

/**
 * Package: me.j360.jdk7.concurrent
 * User: min_xu
 * Date: 2016/12/7 下午5:42
 * 说明：
 */
public class PhaserTest_1 {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);

        for(int i = 0 ; i < 5 ; i++){
            Task_01 task_01 = new Task_01(phaser);
            Thread thread = new Thread(task_01, "PhaseTest_" + i);
            thread.start();
        }
    }

    static class Task_01 implements Runnable{
        private final Phaser phaser;

        public Task_01(Phaser phaser){
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "执行任务完成，等待其他任务执行......");
            //等待其他任务执行完成
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName() + "继续执行任务...");
        }
    }
}
