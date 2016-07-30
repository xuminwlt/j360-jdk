package me.j360.jdk.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Package: me.j360.jdk.jdk8
 * User: min_xu
 * Date: 16/7/30 上午9:58
 * 说明：参数，箭头 —> ，表达式
 */
public class LambdaTest {

    public static void main(String args[]){
        paramTest();
    }

    public static void paramTest(){

        List<String> sss = new ArrayList<>();
        sss.add("1");
        long count = sss.stream().filter(s -> s.length() > 12).count();
        System.out.println(count);

        BiFunction<String,String,Integer> comp = (first,second) -> Integer.compare(first.length(),second.length());

        Integer i = comp.apply("1111","33");
        System.out.println(i);

        BiFunction<String,String,Integer> a = (x,y) -> {
            for (int k = 0; k < 100; k++){
                System.out.println(k);
            }
            return 0;
        };




    }


}
