package me.j360.guava.utils;

import com.google.common.base.Objects;
import com.google.common.base.Optional;

/**
 * Package: me.j360.guava.utils
 * User: min_xu
 * Date: 2016/11/21 下午4:33
 * 说明：
 *
 * Objects基本上干掉了,要用就用jdk的Objects类
 */
public class OptionalUtil {



    public TestBean optionalNull(TestBean testBean){

        return Optional.fromNullable(testBean).or(new TestBean());
    }


    @Deprecated
    public TestBean nonNull(TestBean testBean){
        return Objects.firstNonNull(testBean,new TestBean());
    }




}
