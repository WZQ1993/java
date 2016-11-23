package com.wangziqing.guavaDemo;
import java.math.RoundingMode;

import com.google.common.math.IntMath;

public class MathTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//2^3
		IntMath.pow(2, 3);
		System.out.println(
				IntMath.log2(8, RoundingMode.DOWN)
				);
		
	}

}
