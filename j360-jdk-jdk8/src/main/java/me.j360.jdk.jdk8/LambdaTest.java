package me.j360.jdk.jdk8;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * Package: me.j360.jdk.jdk8
 * User: min_xu
 * Date: 16/7/30 上午9:58
 * 说明：参数，箭头 —> ，表达式
 */
public class LambdaTest {

    public static void main(String args[]) {
        paramTest();
    }

    public static void paramTest() {

        List<String> sss = new ArrayList<>();
        sss.add("1");
        long count = sss.stream().filter(s -> s.length() > 12).count();
        System.out.println(count);

        BiFunction<String, String, Integer> comp = (first, second) -> Integer.compare(first.length(), second.length());

        Integer i = comp.apply("1111", "33");
        System.out.println(i);

        BiFunction<String, String, Integer> a = (x, y) -> {
            for (int k = 0; k < 100; k++) {
                System.out.println(k);
            }
            return 0;
        };

        String[] ss = {"", ""};
        Arrays.sort(ss, (first, second) -> Integer.compare(first.length(), second.length()));

        Runnable r = () -> {
            System.out.println("Zzz");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Callable c = () -> {
            Thread.sleep(1);
            return null;
        };


        //方法引用
        Arrays.sort(ss, String::compareToIgnoreCase);



    }

    public void functionTest(){
        /**
         * 类：实例方法：以类开头，并执行该实例的方法
         */
        Person p = new Person();
        final BiFunction<Person, String, String> getName = Person::getName;
        getName.apply(p,"name");

        /**
         * 类：静态方法
         */
        final BiFunction<Integer,Integer,Integer> max = Math::max;
        max.apply(1,2);

        /**
         * 对象：实例方法，比如super，this等对象
         */
        final KeyEventDispatcher equals = this::equals;

    }

    public boolean comp(String x){
        return true;
    }

    public static String getStaticName(String x){
        return "x"+x;
    }

    class Person{
        public String getName(String x){

            return "x:"+x;
        }
    }
}
