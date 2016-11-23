package com.wangziqing.guavaDemo;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CollectionsTest {
	public static void main(String[] args){
		Set set=Collections.singleton(new String[]{"one","two"});
		System.out.println(		set.size());
		String [] strArray={"one","two","one","two","one","two","one","two"};
		List<String> strList=Arrays.asList(strArray);
//		strList.add("f");不可变
		
	}
}
