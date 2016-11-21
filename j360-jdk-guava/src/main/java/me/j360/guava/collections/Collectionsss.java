package me.j360.guava.collections;

import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;

/**
 * Package: me.j360.guava.collections
 * User: min_xu
 * Date: 2016/11/21 下午5:14
 * 说明：
 */
public class Collectionsss {


    public static void cosss(){
        //Collections2.transform()

        Iterable<Integer> concatenated = Iterables.concat(
                Ints.asList(1, 2, 3),
                Ints.asList(4, 5, 6)); // concatenated包括元素 1, 2, 3, 4, 5, 6
        //String lastAdded = Iterables.getLast(myLinkedHashSet);
        //String theElement = Iterables.getOnlyElement(thisSetIsDefinitelyASingleton);
//如果set不是单元素集，就会出错了！



    }
}
