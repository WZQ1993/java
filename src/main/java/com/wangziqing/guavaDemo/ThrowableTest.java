package com.wangziqing.guavaDemo;
import java.util.EmptyStackException;
import com.google.common.base.Throwables;
public class ThrowableTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			throw new Error();
		}catch(Exception | Error e){
			//java7多重捕获异常
			System.out.println("catch");
//			throw Throwables.propagate(e);
			throw e;
		}
	}
	public void showException() throws Throwable{
	      
	}

}
