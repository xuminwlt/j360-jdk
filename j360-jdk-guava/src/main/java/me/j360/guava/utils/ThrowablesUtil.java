package me.j360.guava.utils;

/**
 * Package: me.j360.guava.utils
 * User: min_xu
 * Date: 2016/11/21 下午4:59
 * 说明：
 * jdk7的多重异常抛出机制
 *


 * 异常原因链
 Guava提供了如下三个有用的方法，让研究异常的原因链变得稍微简便了，这三个方法的签名是不言自明的：
 Throwable   getRootCause(Throwable)
 List<Throwable>   getCausalChain(Throwable)
 String   getStackTraceAsString(Throwable)
 */
public class ThrowablesUtil {


    public void throwabless(){

        //jdk7
        RuntimeException exception = new RuntimeException();

        Throwable throwable = new Throwable();
        throwable.addSuppressed(exception);


        //
    }


}
