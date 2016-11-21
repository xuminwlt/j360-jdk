package me.j360.guava.collections;

import com.google.common.collect.ImmutableSet;

/**
 * Package: me.j360.guava.collections
 * User: min_xu
 * Date: 2016/11/21 下午5:08
 * 说明：不可变集合
 *
 不可变对象有很多优点，包括：

 当对象被不可信的库调用时，不可变形式是安全的；
 不可变对象被多个线程调用时，不存在竞态条件问题
 不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
 不可变对象因为有固定不变，可以作为常量来安全使用。

 */
public class ImmutableSetUtil {

    public static final ImmutableSet<String> GOOGLE_COLORS =
    ImmutableSet.<String>builder()
            .add("")
            .build();


    public static void sampleSet(){

    }
}
