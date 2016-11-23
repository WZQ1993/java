package com.wangziqing.guavaDemo;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Ordering;

public class OrderingTest {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numbers = new ArrayList<Integer>();
	      numbers.add(new Integer(5));
	      numbers.add(new Integer(2));
	      numbers.add(new Integer(15));
	      numbers.add(new Integer(51));
	      numbers.add(new Integer(53));
	      numbers.add(new Integer(35));
	      numbers.add(new Integer(45));
	      numbers.add(new Integer(32));
	      numbers.add(new Integer(43));
	      numbers.add(new Integer(16));
	      List<Integer> numbers_1=Ordering.natural().sortedCopy(numbers);
	      numbers.remove(2);
	      System.out.println(numbers);
	      System.out.println(numbers_1);
	}
}
