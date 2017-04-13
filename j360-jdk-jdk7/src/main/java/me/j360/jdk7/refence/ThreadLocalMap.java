package me.j360.jdk7.refence;


public class ThreadLocalMap {
	
	/**
	 * entry的Key用weakReference封装
	 * value为entry只有的强引用。
	 */
	private Entry[] table;
	/**
	 * 记录添加的值，方便编列内容 
	 */
	private int modCount=0;
	/**
	 * 模拟ThreadLocal.ThreadLocalMap的set动作。
	 */
	public void put(BigByte bigByte , byte[] value){
		if( table == null ){
			table = new Entry[10];
		}
		Entry entry = new Entry( bigByte , value);
		table[modCount] = entry;
		modCount++;
	}

	public void printAll(){
		for( int i = 0 ; i< modCount ; i++ ){
			System.out.println( "--------------------------------" );
			System.out.println( "entry:" + table[i] );
			System.out.println( "key:" + table[i].getKey() );
			System.out.println( "value:" + table[i].getValue() );
			System.out.println( "--------------------------------" );
		}
	}
}
