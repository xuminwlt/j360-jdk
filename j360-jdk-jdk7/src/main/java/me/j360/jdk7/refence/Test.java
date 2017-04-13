package me.j360.jdk7.refence;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class Test {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap map = new HashMap();
		map.put(null, "12312312");
		System.out.println(map.get(null));
		WeakReference<Test> weak = new WeakReference<Test>(null); 
	}

}
