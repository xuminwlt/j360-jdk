package me.j360.guava.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

/**
 * Package: me.j360.guava.utils
 * User: min_xu
 * Date: 2016/11/21 下午4:54
 * 说明：
 */
public class OrderingUtil {


    public void orderings(){

        Ordering<String> byLengthOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };

        //获取前面最大的几个值, 用于合并后再排序的过程,但是拿不到合并的分支的某个的最大值
        byLengthOrdering.greatestOf(Lists.newArrayList(),1);



    }
}
