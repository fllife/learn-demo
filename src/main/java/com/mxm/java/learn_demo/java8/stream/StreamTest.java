package com.mxm.java.learn_demo.java8.stream;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
	
	public static void main(String[] args) {
		List<Dish> menu = Arrays.asList(
				new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH) );
		
		// 1、filter
		List<Dish> vegetarians = menu.stream().filter((Dish dish) -> dish.isVegetarian() == true)
				                              .collect(Collectors.toList());
	    System.out.println("vegetarians:" + vegetarians);
	    
	    // 2、sorted
	    System.out.println("--------------------------");
		List<Dish> sorted = menu.stream().filter((Dish dish) -> dish.isVegetarian() == true)
                                         .sorted((Dish dish1, Dish dish2) -> dish2.getCalories() - dish1.getCalories())
				                         .collect(Collectors.toList());
		System.out.println("sorted:" + sorted);
		
		// 3、扁平流
		List<String> list = new ArrayList<String>(){{
			add("hello");
			add("world");
		}};
		
		List<String> result = list.stream()
		.map(word -> word.split(""))
		.flatMap(Arrays::stream)
		.distinct()
		.collect(Collectors.toList());
		System.out.println("flat:" + result);
		System.out.println("---------------");
		
		// 4、查找元素
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);
		Optional<Integer> firstNumber = numbers.stream()
											.map((Integer i) -> i + i)
											.findFirst();
		System.out.println("optional : " + firstNumber.isPresent() + ",result :" + firstNumber.get());
		System.out.println("---------------");
		
		// 5、reduce
		List<Integer> reduceNumbers = Arrays.asList(4,5,3,9);
		Integer sum = reduceNumbers.stream().reduce(0, (Integer a, Integer b) -> a + b);
	    System.out.println("reduce sum : " + sum);
	    System.out.println("---------------");
	    
	    // 6、生成流
	    Stream.generate(Math::random)
	    	  .limit(10)
	    	  .forEach(System.out::println);
	    System.out.println("---------------");
        // 7、操作顺序
		List<String> names =
				menu.parallelStream()
						.filter(d -> {
							System.out.println("filtering " + d.getName() + ":" + d.getCalories());
							return d.getCalories() > 300;
						})
						.map(d -> {
							System.out.println("mapping" + d.getName());
							return d.getName();
						})
						.limit(3)
						.collect(Collectors.toList());
		System.out.println(JSON.toJSON(names));
		System.out.println("---------------");
	    
	}
}
