package com.wangziqing.guavaDemo;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

public class JDKTypeTest {
	private Map<String, Integer> map = new HashMap<String, Integer>();

	public static void main(String[] args) throws SecurityException, NoSuchFieldException {

		// Type[] types=getParameterizedTypes(new ChaildFoo());
		// for (Type type : types) {
		// System.out.println(type);
		// }
//		GetMapTType(TestType.class);
	}
	/*
	 * class1为包含增加泛型限制对象的类；如包含了List对象的类类型
	 * 输出其中泛型限制对象的泛型类型；如List对象的泛型参数
	 */
	public static void GetMapTType(Class class1) throws SecurityException, NoSuchFieldException {
		// 获取Class实例
		Field[] fields = class1.getDeclaredFields();
		for (Field field : fields) {
			// 示范第一个方法：直接通过getType()取出Field的类型，只对普通类型的Field有效
			Class<?> fieldClass = field.getType();
			// 输出查看
			System.out.println("属性名为"+field.getName()+"的属性类型为：" + fieldClass);
			if (fieldClass.isPrimitive())
				continue; // 判断是否为基本类型
			if (fieldClass.getName().startsWith("java.lang"))
				continue; // getName()返回field的类型全路径；

			// 示范第二种方法：
			Type MainType = field.getGenericType();
			// 为了确保安全转换，使用instanceof判断是否为被参数化的类型
			// ParameterizedType代表被参数化的类型，也就是增加了泛型限制的类型
			if (MainType instanceof ParameterizedType) {
				// 执行强制类型转换
				ParameterizedType parameterizedType = (ParameterizedType) MainType;
				// 获取基本类型信息，即Map
				// getRawType();返回被泛型限制的类型；
				Type basicType = parameterizedType.getRawType();
				System.out.println("基本类型为：" + basicType);
				// 获取泛型类型的泛型参数
				// getActualTypeArguments();返回泛型参数类型。
				Type[] types = parameterizedType.getActualTypeArguments();
				for (int i = 0; i < types.length; i++) {
					System.out.println("第" + (i + 1) + "个泛型类型是：" + types[i]);
				}
			}
		}
	}

	/*
	 * 获取object所继承的父类的泛型参数
	 */
	public static Type[] getParameterizedTypes(Object object) {
		Type superclassType = object.getClass().getGenericSuperclass();
		if (!ParameterizedType.class.isAssignableFrom(superclassType.getClass())) {
			return null;
		}
		return ((ParameterizedType) superclassType).getActualTypeArguments();
	}
}

/*
 * 需求，如何获得map和list的泛型参数
 */
class TestType {
	private Map<String, Integer> map = new HashMap<String, Integer>();
	private List<Long> list = Lists.newArrayList();
}

abstract class Foo<T> {

}

class ChaildFoo extends Foo<String> {

}
