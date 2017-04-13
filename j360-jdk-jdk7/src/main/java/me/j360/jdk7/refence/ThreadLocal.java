package me.j360.jdk7.refence;

public class ThreadLocal {
	
	private ThreadLocalMap tm = new ThreadLocalMap();//模拟ThreadLocalMap。
	
	
	public void set(BigByte bigByte , byte[] value){
		tm.put(bigByte, value);
	}


	public ThreadLocalMap getTm() {
		return tm;
	}
	
	
}
