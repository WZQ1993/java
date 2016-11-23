package com.wangziqing.guavaDemo;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.google.common.hash.PrimitiveSink;

public class HashCodeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stu
		//Hashing提供多种哈希算法,md5是32bit
		HashFunction hf=Hashing.md5();
		
		//hasher是有状态的，添加了数据后，通过hash()获得哈希码
		Hasher her=hf.newHasher().putString("test",Charsets.UTF_8);
		HashCode hashCode=her.hash();
		System.out.println(hashCode.asBytes());
		
		
		//Funnel作为如何将对象转为基本类型
		Funnel<Person> personFunnel = new Funnel<Person>() {
				@Override
				public void funnel(Person person, PrimitiveSink into ){
					// TODO Auto-generated method stub
					into
		            .putInt(person.id)
		            .putString(person.firstName, Charsets.UTF_8)
		            .putString(person.lastName, Charsets.UTF_8)
		            .putInt(person.birthYear);
				}
			};
//			hf.newHasher().putObject(new Person(), personFunnel);

	}
	

}
class Person {
     int id;
     String firstName;
     String lastName;
     int birthYear;
}