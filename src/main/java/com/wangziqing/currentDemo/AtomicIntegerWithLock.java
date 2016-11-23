package com.wangziqing.currentDemo;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * sychronized的效率比显示锁低，有条件的优先使用显示锁
 * @author wzq
 *
 */
public class AtomicIntegerWithLock {
	private int value;
	private Lock lock=new ReentrantLock();
	
	public AtomicIntegerWithLock(){
		super();
	}
	public AtomicIntegerWithLock(int value){
		super();
		this.value=value;
	}
	public final int get(){
		return value;
	}
	public final void set(int newValue){
		this.value=newValue;
	}
	public final int getAndSet(int newValue){
		lock.lock();
		try{
			int oldValue=this.value;
			this.value=newValue;
			return oldValue;
		}finally{
			lock.unlock();
		}
	}
	public final boolean CompareAndSet(int oldValue,int newValue){
		lock.lock();
		try{
			if(value==oldValue){
				this.value=newValue;
				return true;
			}else{
				return false;
			}
		}finally{
			lock.unlock();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
