package me.j360.jdk7.concurrent;

import java.util.concurrent.Phaser;

/**
 * Package: me.j360.jdk7.concurrent
 * User: min_xu
 * Date: 2016/12/7 下午5:43
 * 说明：
 */
public class PhaserTest_3 {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3){
            /**
             * registeredParties:线程注册的数量
             * phase:进入该方法的线程数，
             */
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("执行onAdvance方法.....;phase:" + phase + "registeredParties=" + registeredParties);
                return phase == 3;
            }
        };

        for(int i = 0 ; i < 3 ; i++){
            Task_03 task = new Task_03(phaser);
            Thread thread = new Thread(task,"task_" + i);
            thread.start();
        }
        while(!phaser.isTerminated()){
            phaser.arriveAndAwaitAdvance();    //主线程一直等待
        }
        System.out.println("主线程任务已经结束....");
    }

    static class Task_03 implements Runnable{
        private final Phaser phaser;

        public Task_03(Phaser phaser){
            this.phaser = phaser;
        }

        @Override
        public void run() {
            do{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "开始执行任务...");
                phaser.arriveAndAwaitAdvance();
            }while(!phaser.isTerminated());
        }
    }
}
