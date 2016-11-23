package com.wangziqing.common.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * * 对象锁对象<br/>
 * 
 * <pre>
 * 排序顺序说明:<br/>
 * 1.非实体在前，实体{@link IEntity}在后<br/>
 * 2.不同类型的锁对象，用完整类名做字符串排序<br/>
 * 3.类型相同时，根据<code>排序依据</code>进行排序
 * 4.<code>排序依据</code>对于非实体而言，为<code>System.identityHashCode(instance)</code>
 * 5.<code>排序依据</code>对于实体而言，为{@link IEntity#getIdentity()}
 * 
 * @author 张冠华
 *
 */
public class ObjectLock extends ReentrantLock implements Comparable<ObjectLock> {

	private static final long serialVersionUID = -4497893659305801327L;

	private static final Class<IEntity> IENTITY_CLASS = IEntity.class;

	/** 锁定对象的类型 */
	@SuppressWarnings("rawtypes")
	private final Class clz;
	/** 锁的排序依据 */
	private final Long value;
	/** 该对象锁所锁定的是否实体 */
	private final boolean entity;

	/**
	 * 构造指定对象的对象锁
	 * 
	 * @param object
	 *            获取锁的对象实例
	 */
	public ObjectLock(Object object) {
		this(object, false);
	}

	/**
	 * 构造指定对象的对象锁
	 * 
	 * @param object
	 *            获取锁的对象实例
	 * @param fair
	 *            {@link ReentrantLock#isFair()}
	 */
	public ObjectLock(Object object, boolean fair) {
		super(fair);
		clz = object.getClass();
		if (object instanceof IEntity) {
			value = ((IEntity) object).getIdentity();
		} else {
			value = new Long(System.identityHashCode(object));
		}
		if (IENTITY_CLASS.isAssignableFrom(clz)) {
			entity = true;
		} else {
			entity = false;
		}
	}

	/**
	 * 检查该对象锁所锁定的是否实体
	 * 
	 * @return
	 */
	public boolean isEntity() {
		return entity;
	}

	public int compareTo(ObjectLock o) {
		// 实体和非实体间的排序
		if (this.isEntity() && !o.isEntity()) {
			return 1;
		} else if (!this.isEntity() && o.isEntity()) {
			return -1;
		}

		if (this.clz != o.clz) {
			// 类型不同的排序
			return this.clz.getName().compareTo(o.clz.getName());
		} else {
			// 类型相同的处理
			return this.value.compareTo(o.value);
		}
	}

}
