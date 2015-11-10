package me.j360.jdk.concurrent._1_thread.connectionpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * Created with j360-jdk -> me.j360.jdk.concurrent._1_thread.connectionpool.
 * User: min_xu
 * Date: 2015/11/10
 * Time: 14:22
 * 说明：
 */
public class ConnectionDrive {
    static class ConnectionHandler implements InvocationHandler{

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("commit")){
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }

    public static final Connection createConnection(){
        return (Connection) Proxy.newProxyInstance(ConnectionDrive.class.getClassLoader(),new Class[]{ Connection.class} ,new ConnectionHandler());
    }


}
