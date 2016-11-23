package com.wangziqing.common.lock;

/**
 * 实体标识接口，用于告知锁创建器具体类实例是实体对象
 * 
 * @author 张冠华
 * 
 *         created at 2016年10月26日 下午17:40:57
 */
public abstract class IEntity {

	protected long id;

	/**
	 * 获取实体标识
	 * 
	 * @return
	 */
	public long getIdentity() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
