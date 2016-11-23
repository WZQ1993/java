package com.wangziqing.guavaDemo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.google.common.base.Function;
import com.google.common.collect.BiMap;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.collect.SortedMultiset;

public class CollectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//简化集合创建
		List<String> strList=Lists.newArrayList();
		//不可变集合
		//创建
		ImmutableList<String> immutableStrList=ImmutableList.copyOf(strList);
		immutableStrList=ImmutableList.of("one","two");
		immutableStrList=ImmutableList.<String>builder().add("one").add("one").build();
		//Multiset-可存取重复元素，用于计数
		HashMultiset<String> multiset=HashMultiset.create();
		String one="one";
		multiset.add(one);
		multiset.add(one);
		System.out.println(multiset.count("one"));
		//MutilMap
		HashMultimap<String, Integer> hashMutilmap=HashMultimap.create();
		hashMutilmap.put(one, 2);
		hashMutilmap.put("one", 3);
		System.out.println(hashMutilmap.get("one").toString());
		//集合工具类-Iterables
		System.out.println(
//				Iterables.frequency(multiset, one)+
				Iterables.frequency(Iterables.concat(multiset,immutableStrList)
						, "one")
		);
		Iterable<String> multisetIterable=Iterables.limit(multiset, 1);
		System.out.println(multisetIterable.toString());
		String [] multisetArray=new String[3];
		multiset.toArray(multisetArray);
		//链式迭代器
		FluentIterable<String> fi=FluentIterable.from(strList);
		//Sets工具类
						Set<String> set_1 = Sets.newHashSet("one", "two", "three", "six", "seven", "eight");
				//				ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
						Set<String> set_2 = Sets.newHashSet("two", "three", "five", "seven");
				//				ImmutableSet.of("two", "three", "five", "seven");
						//返回set_1和set_2不是两个都有的
						SetView<String> setView_1=
								Sets.symmetricDifference(set_1, set_2);
						System.out.println(setView_1.immutableCopy());
						//返会set_1中没有在set_2中出现的
						SetView<String> setView_2=
								Sets.difference(set_1, set_2);
						System.out.println(setView_2.immutableCopy());
						
						Set<String> animals = ImmutableSet.of("gerbil", "hamster");
						Set<String> fruits = ImmutableSet.of("apple", "orange", "banana");
						Set<List<String>> product = Sets.cartesianProduct(animals, fruits);
						/**
						 * {{"gerbil", "apple"}, {"gerbil", "orange"},
						 *  {"gerbil", "banana"},{"hamster", "apple"},
						 *   {"hamster", "orange"}, {"hamster", "banana"}}
						 */
						Set<Set<String>> animalSets = Sets.powerSet(animals);
						// {{}, {"gerbil"}, {"hamster"}, {"gerbil", "hamster"}}
		 //Maps.uniqueIndex()
						/**
						 * 根据字符串的长度获取元素，长度唯一
						 */
						List<String> strings=Lists.newArrayList("1","11","111");
						ImmutableMap<Integer, String> stringsByIndex = 
								Maps.uniqueIndex(strings,new Function<String, Integer> () {
								        public Integer apply(String string) {
								            return string.length();
								        }
								    });
						System.out.println(stringsByIndex.get(1));
			
						
						CollectionTest test=new CollectionTest();
	}
	public void mapDiffenceTest(){
		Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
		Map<String, Integer> right = ImmutableMap.of("a", 1, "b", 2, "c", 3);
		MapDifference<String, Integer> diff = Maps.difference(left, right);
		diff.entriesInCommon(); // {"b" => 2}
		diff.entriesInCommon(); // {"b" => 2}
		diff.entriesOnlyOnLeft(); // {"a" => 1}
		diff.entriesOnlyOnRight(); // {"d" => 5}
		diff.entriesDiffering();
	}
	public void concurrentMap(){
		ConcurrentMap<Integer, String> concurrentMap=Maps.newConcurrentMap();
		Integer one=1,two=2;
		concurrentMap.put(one, "1");
		concurrentMap.put(two, "2");
		one=null;
		System.out.println(concurrentMap.get(1));
	}
	
	

}
