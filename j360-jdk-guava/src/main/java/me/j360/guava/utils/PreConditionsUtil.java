package me.j360.guava.utils;

import com.google.common.base.Preconditions;

import java.util.Objects;

/**
 * Package: me.j360.guava.utils
 * User: min_xu
 * Date: 2016/11/21 下午4:45
 * 说明：
 *
 *
 * 相比Apache Commons提供的类似方法，我们把Guava中的Preconditions作为首选。Piotr Jagielski在他的博客中简要地列举了一些理由：

 在静态导入后，Guava方法非常清楚明晰。checkNotNull清楚地描述做了什么，会抛出什么异常；
 checkNotNull直接返回检查的参数，让你可以在构造函数中保持字段的单行赋值风格：this.field = checkNotNull(field)
 简单的、参数可变的printf风格异常信息。鉴于这个优点，在JDK7已经引入Objects.requireNonNull的情况下，我们仍然建议你使用checkNotNull。


 方法声明（不包括额外参数）	描述	检查失败时抛出的异常
 checkArgument(boolean)	检查boolean是否为true，用来检查传递给方法的参数。	IllegalArgumentException
 checkNotNull(T)	检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull。	NullPointerException
 checkState(boolean)	用来检查对象的某些状态。	IllegalStateException

 */
public class PreConditionsUtil {

    public boolean check(){
        Preconditions.checkArgument(1==1,"condition not 1");
        int i = 0,j=1;
        Preconditions.checkArgument(i < j, "Expected i < j, but %s > %s", i, j);
        return false;
    }


    //toString and null from jdk objects
    public void objectsCompare(){
        Objects.toString(new TestBean(),"");
    }
}
