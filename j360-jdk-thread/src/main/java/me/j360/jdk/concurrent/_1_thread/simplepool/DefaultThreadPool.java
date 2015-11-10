package me.j360.jdk.concurrent._1_thread.simplepool;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with j360-jdk -> me.j360.jdk.concurrent._1_thread.simplepool.
 * User: min_xu
 * Date: 2015/11/10
 * Time: 13:45
 * 说明：
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    private static final int MAX_WORKER_NUMBERS = 10;
    private static final int DEFAULT_WORRER_NUMBERS = 5;
    private static final int MIN_WORKER_NUMBERS = 1;
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    private int workerNum = DEFAULT_WORRER_NUMBERS;
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool(){
        initializerWorkers(DEFAULT_WORRER_NUMBERS);
    }
    public DefaultThreadPool(int num){
        workerNum = num  > MAX_WORKER_NUMBERS?MAX_WORKER_NUMBERS:num < MIN_WORKER_NUMBERS?MIN_WORKER_NUMBERS:num;
        initializerWorkers(workerNum);
    }
    @Override
    public void execute(Job job) {
        if(job != null){
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for(Worker worker:workers){
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs){
            if(num+this.workerNum >MAX_WORKER_NUMBERS){
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializerWorkers(num);
            this.workerNum = num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized(jobs){
            if(num>this.workerNum){
                throw new IllegalArgumentException("beyond worknum");
            }
            int count = 0;
            while(count < num){
                Worker worker = workers.get(count);
                if(workers.remove(worker)){
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    //初始化线程工作
    private void initializerWorkers(int num){
        for(int i = 0;i<num;i++){
            Worker worker = new Worker();
            Thread thread = new Thread(worker,"ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    class Worker implements Runnable{

        private volatile boolean running = true;
        @Override
        public void run() {
            while (running){
                Job job = null;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        }catch (InterruptedException e){
                            Thread.currentThread().interrupt();
                            return;
                        }

                    }
                    job = jobs.removeFirst();
                }
                if(job != null){
                    try {
                        job.run();
                    }catch (Exception ex){

                    }
                }
            }
        }

        public void shutdown(){
            running = false;
        }
    }

    static class JobJob implements Runnable{

        @Override
        public void run() {
            System.out.println("JobJob");
        }
    }
    public static void main(String[] args){
        DefaultThreadPool<JobJob> defaultThreadPool = new DefaultThreadPool<JobJob>(4);
        for(int i=0;i<40;i++){
            JobJob jobJob = new JobJob();
            defaultThreadPool.execute(jobJob);

            System.out.println(defaultThreadPool.getJobSize());
        }

        defaultThreadPool.shutdown();
    }
}
