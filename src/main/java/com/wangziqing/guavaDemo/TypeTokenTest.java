package com.wangziqing.guavaDemo;
import com.google.common.reflect.TypeToken;

public class TypeTokenTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 示例，取得泛型类型String的具体类型
		 */
		TypeToken<MyTypeTest<String>> typeToken = new TypeToken<MyTypeTest<String>>() {
			private static final long serialVersionUID = 1L;
		};
		//resolveType方法解析出泛型类型
		TypeToken<?> generalToken = typeToken.resolveType(MyTypeTest.class.getTypeParameters()[0]);
		System.err.println(generalToken.getType() + "\n" + generalToken.getRawType());
	}

}

class MyTypeTest<T> {
	T t;

	MyTypeTest(T t) {
		// TODO Auto-generated constructor stub
		this.t = t;
	}

	public T getType() {
		return t;
	}

}