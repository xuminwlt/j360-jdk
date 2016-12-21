package me.j360.jdk.jdk8;

/**
 * Package: me.j360.jdk.jdk8
 * User: min_xu
 * Date: 2016/12/21 下午2:16
 * 说明：高级编程,重构api
 *
 * 1、利用新api重写代码
 * 2.默认方法改写
 * 3.optional使用
 * 4.高级并发模型
 */
public class AdvanceApiTest {


    public static void doSomeThing(Runnable runnable){
        runnable.run();
    }


    /**
     * 尽量重构匿名类到lambda
     * this在lambda作用和匿名类不一样
     * 重构lambda,匿名类、log、环绕执行
     * 调试 栈跟踪 、日志和peek
     */
    public static void rewrite(){
        doSomeThing( () -> System.out.println());

    }


    /**
     * java8 允许接口定义静态方法
     *       接口中可以定义默认方法
     * 默认方法引起的冲突
     */
    public static void defaultFunciton(){

    }


    /**
     * null问题,Optional容器,在有返回码的方法上返回容器类
     * 使用map方法获取对象Bean中的值,map person::getName,返回Optional<Name>对象,再次使用返回Optional<Optional<Name>>
     * flatMap方法返回当前对象的链式结果
     */
    public static void optionalTest(){


    }


    /**
     * 1.同步变异步  supplyAsync ComplatableFuture.supplyAsync(),线程池设计 N = Ncpu * Ucpu * (1+W/C); 无IO计算型使用stream,复杂情况的使用Future
     * 结合使用stream()对多个并行的Future进行流式处理,达到最大的tps
     *
     */
    public static void complatableFuture(){

    }



}
