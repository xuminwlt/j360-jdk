package me.j360.jdk7.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Package: me.j360.jdk7.i18n
 * User: min_xu
 * Date: 2016/12/5 上午11:35
 * 说明：
 */
public class Messages {

    private static ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("me.j360.jdk7.i18n");
    }

    public static String getMessage(String key,Object... args){
        String message = bundle.getString(key);
        return String.format(message,args);
    }

    public static String getMessage(Locale locale,String key,Object... args){
        String message = bundle.getString(key);
        return String.format(locale,key,args);
    }

}
