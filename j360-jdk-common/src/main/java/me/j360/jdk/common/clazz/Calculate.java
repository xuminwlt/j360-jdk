package me.j360.jdk.common.clazz;

/**
 * Created with j360-jdk -> PACKAGE_NAME.
 * User: min_xu
 * Date: 2015/10/21
 * Time: 9:24
 * 说明：
 */
class Holder{
    public int i = 1;
}
public class Calculate {
    int i = 20;

    public void getHolder(){
        Holder h = new Holder();
        h.i = i;
        setHolder(h);
        System.out.println(h.i);
    }

    public void setHolder(Holder holder){
        Holder h = new Holder();
        h.i = 30;
        holder = h;
        System.out.println(holder.i);

    }

    public static void  main(String[] args){
        Calculate c = new Calculate();
        c.getHolder();
    }

}
