package com.wangziqing.currentDemo;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基本的思想是表现为一个同步器，支持下面两个操作：
		获取锁：
		首先判断当前状态是否允许获取锁，如果是就获取锁，否则就阻塞操作或者获取失败，
		也就是说如果是独占锁就可能阻塞，如果是共享锁就可能失败。另外如果是阻塞线程，
		那么线程就需要进入阻塞队列。当状态位允许获取锁时就修改状态，并且如果进了队列就从队列中移除。
		释放锁:
		这个过程就是修改状态位，如果有线程因为状态位阻塞的话就唤醒队列中的一个或者更多线程。
		要支持上面两个操作就必须有下面的条件：
				1.原子性操作同步器的状态位（32位整数状态位）
				2.阻塞和唤醒线程（JDK 5.0以后利用JNI在LockSupport类中实现了此特性。
													当前线程中调用，导致线程阻塞，带参数的Object是挂起的对象）
				3.一个有序的队列
 * @author wzq
 *
 */
public class AbstractQueuedSynchronizer_AQS {
//	AbstractQueuedSynchronizer
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
