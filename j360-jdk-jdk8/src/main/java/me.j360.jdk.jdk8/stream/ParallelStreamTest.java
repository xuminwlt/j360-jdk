package me.j360.jdk.jdk8.stream;

/**
 * Package: me.j360.jdk.jdk8.stream
 * User: min_xu
 * Date: 2016/12/21 下午5:31
 * 说明：
 */
public class ParallelStreamTest {


    /**
     * 自动装箱拆箱将极大影响性能,尽量使用LongStream,IntStream
     * 共享可变状态的并行流是错误的,线程不安全
     * 并行流使用forkjoin框架,默认线程池为cpu数量
     */
    public static void main(){

    }
}
