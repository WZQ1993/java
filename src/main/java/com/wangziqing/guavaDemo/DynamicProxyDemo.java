package com.wangziqing.guavaDemo;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.google.common.reflect.Reflection;

public class DynamicProxyDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//新建handler时，指定代理对象	
		InvocationHandler invocationHandler = new MyInvocationHandler(new IFooImp());

        // Guava Dynamic Proxy implement
        IFoo foo = Reflection.newProxy(IFoo.class, invocationHandler);
        foo.doSomething();
        
        //jdk Dynamic proxy implement
        IFoo jdkFoo = (IFoo) Proxy.newProxyInstance(
                IFoo.class.getClassLoader(),
                new Class<?>[]{IFoo.class},
                invocationHandler);
        jdkFoo.doSomething();
	}

	public static class MyInvocationHandler implements InvocationHandler {
		//真正的代理对象
		private Object realProxy;
		public MyInvocationHandler(Object realProxy) {
			// TODO Auto-generated constructor stub
			this.realProxy=realProxy;
		}
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			//在执行方法前，代理可以执行一些额外处理，譬如记录日志等
			System.out.println("代理方法前执行一些处理");
			//利用反射，realProxy执行方法
			return method.invoke(realProxy, args);
			//在转调具体目标对象之后，可以执行一些功能处理
		}
	}
	
}
interface IFoo {
	void doSomething();
}

class IFooImp implements IFoo{

	@Override
	public void doSomething() {
		// TODO Auto-generated method stub
		System.out.println("IFooImp is doing something");
	}
	
}