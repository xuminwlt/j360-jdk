package me.j360.jdk.container;

import java.util.*;

/**
 * Created with j360-jdk -> me.j360.jdk.container.
 * User: min_xu
 * Date: 2015/10/17
 * Time: 9:18
 * ËµÃ÷£º
 */
public class Container {


    public static void main(String[] args){

        stackListAction();

    }

    private static void arrayListAction(){
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("j360-1");
    }

    private static void linkedListAction(){

        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add("j360-1");
    }

    private static void vectorListAction(){
        Vector<String> vectorList = new Vector<String>();
        vectorList.add("j360-1");
    }

    private static void stackListAction(){
        Stack<String> stackList = new Stack<String>();
        stackList.add("j360-1");
        stackList.pop();
        stackList.push("j360-1");
    }

    private static void setAction(){
        HashSet<String> set = new HashSet<String>();
        set.add("j360-set");
    }


    private static void map(){
        HashMap<String,String> map = new HashMap<String, String>();
        Hashtable<String,String> table = new Hashtable<String, String>();
        TreeMap<String,String> treeMap = new TreeMap<String, String>();
        TreeSet<String> treeSet = new TreeSet<String>();
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>();



    }



}
