package com.wangziqing.guavaDemo;
import java.awt.Label;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

public class GuavaCacheTest {
	private static final Integer[] keys={1,2,3};
	private static final String[] values={"one","two","three"};
	private static final ExecutorService pool=Executors.newCachedThreadPool();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GuavaCacheTest().cacheTest();
	}
	
	public void cacheTest(){
		LoadingCache<Integer, String> cache=
				CacheBuilder.newBuilder().maximumSize(2)
				//移除监听器
				.removalListener(new RemovalListener<Integer, String>() {

					@Override
					public void onRemoval(RemovalNotification<Integer, String> item) {
						// TODO Auto-generated method stub
						System.out.println("移除"+item.getValue());
					}
				})
				//缓存加载器
				.build(new CacheLoader<Integer,String>(){

					@Override
					public String load(Integer key) throws Exception {
						// TODO Auto-generated method stub
						return values[key-1];
					}
					
					//刷新(异步刷新)
					@Override
					public ListenableFuture<String> reload(final Integer key, String value) {
						ListenableFutureTask<String> task=ListenableFutureTask.create(
										new Callable<String>() {

											@Override
											public String call() throws Exception {
												// TODO Auto-generated method stub
												Thread.sleep(5000);
												System.out.println("刷新"+key);
												return values[key-1];
											}
										
						});
						//使用excutor异步执行task
						pool.execute(task);
						return task;
					}
				});
		try {
			// callable如果缓存不存在的加载方法
			System.out.println(cache.get(1, new Callable<String>() {

				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					return values[1];
				}
			}));
//			cache.invalidate(1);//移除缓存
			cache.refresh(1);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

