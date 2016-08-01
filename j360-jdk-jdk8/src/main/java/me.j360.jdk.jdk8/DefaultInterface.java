package me.j360.jdk.jdk8;

/**
 * Package: me.j360.jdk.jdk8
 * User: min_xu
 * Date: 16/8/1 下午7:42
 * 说明：
 */
public interface DefaultInterface {

    default public String getName(){
        return "test";
    }

    public static String getInterfaceStaticName(){
        return "test";
    }
}
