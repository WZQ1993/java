package com.wangziqing.guavaDemo;
import java.util.WeakHashMap;

public class WeakHashMapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void weakHashMap(){
		WeakHashMap<Integer, String> weakHashMap=new WeakHashMap<>();
		/*
		 * new Integer(value)对象存在与堆中，只有one强引用指向它，输出null
		 */
		Integer one=new Integer(1);
		/*
		 * Integer one=1;	
		 * 输出one，因为此时one指向的对象是存在静态integer缓存池中的，
		 * 即使one强引用不指向它，可能还有其他强引用指向它
		 */
		weakHashMap.put(one,"one");
		one=null;
		System.gc();
		System.out.println(weakHashMap.get(1));
		
	}
}
