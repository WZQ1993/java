package com.wangziqing.guavaDemo;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedInteger;
import com.google.common.primitives.UnsignedInts;

public class PrimitivesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ints.asList(1,2);
		System.out.println(
				Ints.BYTES
				);
		//无符号类型转换
		UnsignedInteger unint=UnsignedInteger.valueOf("2222222222");
		System.out.println(
				UnsignedInts.toString(22, 11)
				);
		
	}

}
