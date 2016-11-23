package com.wangziqing.guavaDemo;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

public class InvokeableTest {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		// TODO Auto-generated method stub
		
		//JDK方法，是否为public
		Class type=InvokeableTest.class;
		Method[] methods=type.getDeclaredMethods();
		for (Method method : methods) {
			if(Modifier.isPublic(method.getModifiers()))System.out.println(method.getName());
		}
		
		//从method中生成invokable
		Invokable invokable=Invokable.from(methods[1]);
		//是否为public
		System.out.println(invokable.isPublic());
		
		
		
		/*
		 * List的List.get(int)返回类型是什么？Invokable提供了与众不同的类型解决方案：
		 */
		Method getMethod=List.class.getMethod("get", int.class);
		//jdk提供的，由于泛型擦除
		System.out.println(getMethod.getReturnType());//return Object
		Invokable<List<String>, ?> invokable_list = new TypeToken<List<String>>(){
			private static final long serialVersionUID = 1L;}
		.method(getMethod);
		System.out.println(invokable_list.getReturnType());//return String
	}	
	public void publicMethod(){
		
	}
}
