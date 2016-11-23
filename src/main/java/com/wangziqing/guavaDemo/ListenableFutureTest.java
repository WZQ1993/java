package com.wangziqing.guavaDemo;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.common.base.Function;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class ListenableFutureTest {
	static ExecutorService pool = Executors.newFixedThreadPool(10);
	static ListeningExecutorService service = MoreExecutors.listeningDecorator(pool);

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListenableFuture<Integer> queryFuture = service.submit(new MyCallable("selceasfsdfdfsadsffds"));
		ListenableFuture<String> result = Futures.transform(queryFuture, new MyAsynFunction());
		// 组合多个ListenableFuture,返回多个future成功时的返回值组成的list
		Futures.allAsList(queryFuture, queryFuture);

		try {
			System.out.println(result.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public void CheckFutureTest(){
//		// 执行逻辑中抛出检查异常
//		Futures.makeChecked(service.submit(new MyCallableException()), new Function<Exception, String>(){
//
//			@Override
//			public String apply(Exception e) {
//				// TODO Auto-generated method stub
//				return e.getMessage();
//			}
//			
//		});
//	}
}

class MyAsynFunction implements AsyncFunction<Integer, String> {
	@Override
	public ListenableFuture<String> apply(Integer ID) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("第二步：对查询结果的ID进行处理");
		return Futures.immediateFuture("我的ID是" + ID);
	}

}

class MyCallable implements Callable<Integer> {
	String query = "";

	public MyCallable(String query) {
		// TODO Auto-generated constructor stub
		this.query = query;
	}

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("第一步：从数据库查询出ID");
		System.out.println("查询语句" + query);
		Thread.sleep(5000);
		return 100;
	}
}
class MyFunctionDealWithException implements Function<Exception,String>{

	@Override
	public String apply(Exception e) {
		// TODO Auto-generated method stub
		return e.getMessage();
	}
	
}
class MyCallableException implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		throw new FileNotFoundException();
	}
}
