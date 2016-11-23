package com.wangziqing.common.lock;

import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap.Builder;

/**
 * 
 * @author 张冠华
 * 
 *         created at 2016年10月26日 下午17:40:57
 */
@SuppressWarnings("rawtypes")
public class ObjectLockHolder {
	/** 持有者集合 **/
	private static final Builder<Class, Holder> BUILDER = new Builder<Class, Holder>();
	private static final ConcurrentLinkedHashMap<Class, Holder> HOLDERS = BUILDER.maximumWeightedCapacity(800).build();

	/**
	 * 单一类的锁持有者
	 */
	public class Holder {
		/** 对象实例与其对应的锁缓存 **/
		private final WeakHashMap<Object, ObjectLock> locks = new WeakHashMap<Object, ObjectLock>();

		private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

		/**
		 * 创建一个持有者实例
		 * 
		 * @param clz
		 */
		public Holder() {
		}

		/**
		 * 获取对象锁
		 * 
		 * @param object
		 * @return
		 */
		public ObjectLock getLock(Object object) {
			Lock lock = this.lock.readLock();
			try {
				lock.lock();
				ObjectLock result = locks.get(object);
				if (result != null) {
					return result;
				}
			} finally {
				lock.unlock();
			}
			return createLock(object);
		}

		/**
		 * 创建对象锁
		 * 
		 * @param object
		 * @return
		 */
		private ObjectLock createLock(Object object) {
			Lock lock = this.lock.writeLock();
			try {
				lock.lock();
				ObjectLock result = locks.get(object);
				if (result != null) {
					return result;
				}
				result = new ObjectLock(object);
				locks.put(object, result);
				return result;
			} finally {
				lock.unlock();
			}
		}
	}

	/**
	 * 获取指定对象实例的对象锁
	 * 
	 * @param object
	 *            要获取锁的对象实例
	 * @return
	 */
	public ObjectLock getLock(Object object) {
		Holder holder = getHolder(object.getClass());
		ObjectLock lock = holder.getLock(object);
		return lock;
	}

	/**
	 * 获取某类实例的锁持有者
	 * 
	 * @param clz
	 *            指定类型
	 * @return
	 */
	private Holder getHolder(Class<?> clz) {
		Holder holder = HOLDERS.get(clz);
		if (holder != null) {
			return holder;
		}
		HOLDERS.putIfAbsent(clz, new Holder());
		return HOLDERS.get(clz);
	}
}
