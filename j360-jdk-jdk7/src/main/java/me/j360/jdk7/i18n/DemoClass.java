package me.j360.jdk7.i18n;

/**
 * Package: me.j360.jdk7.i18n
 * User: min_xu
 * Date: 2016/12/5 上午11:40
 * 说明：
 */

@MessageBundle(name="Messages")
public class DemoClass {


    public void output(){
        System.out.println(getTestMessage("hello"));
    }

    @Message(key="1",value="%1$s")
    public String getTestMessage(Object... args){
        return "";
    }


    public static void main(String[] args){
        DemoClass demoClass = new DemoClass();

        demoClass.output();
    }

}

