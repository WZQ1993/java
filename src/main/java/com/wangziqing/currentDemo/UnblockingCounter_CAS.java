package com.wangziqing.currentDemo;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger非阻塞计算器
 * CAS的语义是“我认为V的值应该为A，如果是，那么将V的值更新为B，否则不修改并告诉V的值实际为多少” CAS完成非阻塞计算器
 * 
 * @author wzq
 *
 */
public class UnblockingCounter_CAS {
	private volatile AtomicInteger value;

	public AtomicInteger getValue() {
		return value;
	}

	public int increment() {
		int i;
		do {
			i = value.get();
		} while (value.compareAndSet(i, i + 1));
		return i + 1;
	}

}
