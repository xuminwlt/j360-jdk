package me.j360.jdk.jdk8;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Package: me.j360.jdk.jdk8
 * User: min_xu
 * Date: 16/7/30 上午9:58
 * 说明：参数，箭头 —> ，表达式
 */
public class LambdaTest implements DefaultInterface {

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

    /**
     * 3种引用方法 ::的使用，作用上等同于 -> 的对用关系
     */
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


        //构造器引用 new出一个对象(使用Bifunction模拟该方法，带上两个参数并返回Person)
        BiFunction<String,String, Person> ppp = Person::new;
        Person pppp = ppp.apply("a","c");


    }

    public boolean comp(String x){
        return true;
    }

    public static String getStaticName(String x){
        return "x"+x;
    }


    /**
     * 变量的作用域：闭包中，被引用的自由变量名，不可以被更改，final
     */
    public static void repeat(){

    }

    /**
     * 默认方法，写在interface中的方法
     */
    public void defaultFunctionTest(){
        getName();
    }


    /**
     * 接口类的静态方法
     */
    public void staticFunctionTest(){
        
    }


    class Person{

        private int id;
        private int age;

        public Person(){

        }
        public Person(String a,String b){

        }

        public String getName(String x){

            return "x:"+x;
        }

        public int getId(){
            return this.getId();
        }
        public int getAge(){
            return this.age;
        }


    }




    public static void listFilterTest(){
        List<Person> list = new ArrayList<Person>();


        //过滤
        list.stream().sorted(Comparator.comparingInt(Person::getAge)).forEach(p -> System.out.println(p.getAge()));

        //工具类过滤
        filter(list,(Person p) -> p.getAge() > 10);

        //循环列举
        list.stream().filter((Person p) -> p.getAge() > 10).sorted((p1,p2) -> p1.getAge() - p2.getAge()).collect(toList()).forEach(
                p -> System.out.println(p.getAge()));


        //按年龄归并
        Map<Integer,List<Person>> map = list.stream().collect(groupingBy(Person::getAge));

        //列出所有人的年龄
        List<Integer> list1 = list.stream().map(Person::getAge).collect(toList());

        //计算大于10岁的人的数量
        long count  = list.stream().map(Person::getAge).filter(age -> age > 10).count();

        //flatMap 把一个流中的每个值都换成另外一个流,然后把所有的流连接起来成为一个流


        //查找匹配 match终端操作,用于判断流中是否存在,返回布尔值 find,返回流中的找到的元素,并且是Optional容器


        //规约 reduce 0初始值,a,b 每次都拿上一次计算出来的值作为a,b则是流中的下一个元素,如果没有初始值的重载方法,则返回一个Optional容器类
        int ageSum = list.stream().map(Person::getAge).reduce(0,(a,b) -> a + b);

        Optional<Integer> ageSum2 = list.stream().map(Person::getAge).reduce((a,b) -> a+b);

        //reduce max Integer.max(a,b); min avg等
        //Stream变种,LongStream,IntStream,一级 mapToLong,maoToInt

        //创建流:数组创建、文件创建、文件生成流、函数生成流:无限流





    }


    static <T> Collection<T> filter(Collection<T> c, Predicate<T> p){
        return c;
    }
}
