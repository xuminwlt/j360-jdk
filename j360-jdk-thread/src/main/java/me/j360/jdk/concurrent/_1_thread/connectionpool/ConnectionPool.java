package me.j360.jdk.concurrent._1_thread.connectionpool;

import java.util.LinkedList;

/**
 * Created with j360-jdk -> me.j360.jdk.concurrent._1_thread.connectionpool.
 * User: min_xu
 * Date: 2015/11/10
 * Time: 14:20
 * 说明：
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<Connection>();

    public ConnectionPool(int initialSize){
        if(initialSize > 0){
            for(int i=1;i<initialSize;i++){
                pool.addLast(ConnectionDrive.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection){
        if(connection != null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool){
            if(mills <= 0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                long future = System.currentTimeMillis() + mills;
                long remainning = mills;
                while(pool.isEmpty() && remainning > 0){
                    pool.wait();
                    remainning = future - System.currentTimeMillis();
                }
                Connection result = null;
                if(! pool.isEmpty()){
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
