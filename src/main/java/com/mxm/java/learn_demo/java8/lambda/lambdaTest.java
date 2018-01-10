/**   
* @Title: lambdaTest.java 
* @Package com.mxm.java.learn_demo.java8.lambda  
* @author maxm@uubee.com  
* @date 2018年1月3日 上午9:59:31 
* @version V1.0   
*/
package com.mxm.java.learn_demo.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.sun.javafx.collections.MappingChange.Map;

import sun.net.www.content.text.plain;

/**  
 * @author maxm@uubee.com
 * @date 2018年1月3日 上午9:59:31 
 */
public class lambdaTest {
	public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList<Apple>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		// 1、过滤list
		List<Apple> inventory = new ArrayList<Apple>();
		inventory.add(new Apple("green", 100));
		inventory.add(new Apple("red", 150));
		List<Apple> result = filterApples(inventory, (Apple apple) -> "green".equals(apple.getColor()));
		System.out.println(result);
		System.out.println("-------------");
		
		// 2、线程
		
		Thread thread = new Thread(() -> System.out.println("hello world"));
		thread.start();
		System.out.println("-------------");
		
		// 3、consumer
		forEach(Arrays.asList(1,2,3,4,5), (s) -> {System.out.println(s);});
		System.out.println("-------------");
		
		// 4、function
		List<Integer> resultInt = map(Arrays.asList("lambdas", "hello", "world"), (String s) -> {return s.length();});
		System.out.println(resultInt);
		
		// 5、方法引用
		Supplier<Apple> apple1 = Apple::new;
	}
	
	public static<T> void forEach(List<T> list, Consumer<T> c) {
		for (T t: list) {
			c.accept(t);
		}
	}
	
	public static<T, R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> result = new ArrayList<R>();
		for(T t : list) {
			result.add(f.apply(t));
		}
		return result;
	}
}
