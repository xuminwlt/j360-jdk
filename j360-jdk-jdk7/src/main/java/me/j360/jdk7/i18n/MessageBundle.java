package me.j360.jdk7.i18n;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Package: me.j360.jdk7.i18n
 * User: min_xu
 * Date: 2016/12/5 上午11:44
 * 说明：
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface MessageBundle {

    String name();

}
