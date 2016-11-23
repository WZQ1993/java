package com.wangziqing.guavaDemo;
import com.google.common.base.CharMatcher;

public class PredicateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PredicateTest().CharMatcherTest();
	}
	public void CharMatcherTest(){
		//字符类型有自己特定版本的Predicate——CharMatcher
		CharMatcher c=new CharMatcher() {
			
			@Override
			public boolean matches(char arg0) {
				// TODO Auto-generated method stub
				if(arg0=='a')
				return true;
				else return false;
			}
		};
	}
}
