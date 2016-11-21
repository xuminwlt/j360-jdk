package me.j360.guava.range;

import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

/**
 * Package: me.j360.guava.range
 * User: min_xu
 * Date: 2016/11/21 下午5:20
 * 说明：
 */
public class Rangesss {

    public static void range(){
        Range.closed(1, 3).contains(2);//return true

        Range.closed(1, 3).contains(4);//return false

        Range.lessThan(5).contains(5); //return false

        Range.closed(1, 4).containsAll(Ints.asList(1, 2, 3));
    }
}
