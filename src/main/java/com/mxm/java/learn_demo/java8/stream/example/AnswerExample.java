/**   
* @Title: AnswerExample.java 
* @Package com.mxm.java.learn_demo.java8.stream.example  
* @author maxm@uubee.com  
* @date 2018年1月4日 上午10:27:52 
* @version V1.0   
*/
package com.mxm.java.learn_demo.java8.stream.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**  
 * @author maxm@uubee.com
 * @date 2018年1月4日 上午10:27:52 
 */
public class AnswerExample {
	
	/**
	 * 交易
	 */
	static Trader raoul = new Trader("Raoul", "Cambridge");
	static Trader mario = new Trader("Mario","Milan");
	static Trader alan = new Trader("Alan","Cambridge");
	static Trader brian = new Trader("Brian","Cambridge");
	
	/**
	 * 交易
	 */
	static List<Transaction> transactions = Arrays.asList(
		new Transaction(brian, 2011, 300),
		new Transaction(raoul, 2012, 1000),
		new Transaction(raoul, 2011, 400),
		new Transaction(mario, 2012, 710),
		new Transaction(mario, 2012, 700),
		new Transaction(alan, 2012, 950)
	);
	
	/**
	 * 找出2011年发生的所有交易，并按交易额排序（从低到高）
	 * void
	 * @author maxm@uubee.com
	 * @date 2018年1月4日 上午10:46:17
	 */
	public static void example1() {
		List<Transaction> result = transactions.stream()
											.filter((Transaction transactions) -> transactions.getYear() == 2011)
											.sorted(Comparator.comparing(Transaction::getValue))
	        								.collect(Collectors.toList());
	    System.out.println("result : " + result);
	}
	
	/**
	 * 交易员都在哪些不同的城市工作过？
	 * void
	 * @author maxm@uubee.com
	 * @date 2018年1月4日 上午11:03:02
	 */
	public static void example2() {
		List<String> result = transactions.stream()
		 			 .map(Transaction::getTrader)
		 			 .map(Trader::getCity)
		 			 .distinct()
		 			 .collect(Collectors.toList());
		
		System.out.println("result : " + result);
		 			 
	}
	
	/**
	 * 查找所有来自于剑桥的交易员，并按姓名排序
	 * void
	 * @author maxm@uubee.com
	 * @date 2018年1月4日 上午11:16:35
	 */
	public static void example3() { 
		List<Trader> result = transactions.stream()
										.filter((Transaction transactions) -> "Cambridge".equals(transactions.getTrader().getCity()))
										.map(Transaction::getTrader)
										.sorted(Comparator.comparing(Trader::getName))
										.collect(Collectors.toList());
		System.out.println("result : " + result);
			
										
			
		
		
	}      
	
	/**
	 * 返回所有交易员的姓名字符串，按字母顺序排序。
	 * void
	 * @author maxm@uubee.com
	 * @date 2018年1月4日 上午11:25:58
	 */
	public static void example4() {  
		List<Trader> result = transactions.stream().map(Transaction::getTrader)
							     .sorted(Comparator.comparing(Trader::getName))
							     .collect(Collectors.toList());
		System.out.println("result : " + result);
	}
	
	/**
	 * 有没有交易员是在米兰工作的？
	 * void
	 * @author maxm@uubee.com
	 * @date 2018年1月4日 上午11:30:25
	 */
	public static void example5() {   
		List<Trader> result = transactions.stream()
					            .map(Transaction::getTrader)
					            .filter((Trader trader) -> !"Milan".equals(trader.getCity()))
					            .collect(Collectors.toList());
		System.out.println("result : " +result);
	}
	
	/*
	 * 打印生活在剑桥的交易员的所有交易额。
	 */
	public static void example6() {
		Integer result = transactions.stream()
							             .filter((Transaction transactions) -> "Cambridge".equals(transactions.getTrader().getCity()))
							             .map(Transaction::getValue)
							             .reduce(0,(Integer a, Integer b) -> a + b);
		System.out.println("result : " + result);			
	}
	
	/**
	 * 所有交易中，最高的交易额是多少？
	 * void
	 * @author maxm@uubee.com
	 * @date 2018年1月4日 下午12:54:50
	 */
	public static void example7() {
		Optional<Integer> result = transactions.stream()
									.map((Transaction transaction) -> transaction.getValue())
									.reduce(Integer::max);
	   System.out.println("result : " + result);				
	}
	
	public static void example8() {
		Optional<Integer> result = transactions.stream()
									.map((Transaction transaction) -> transaction.getValue())
									.reduce(Integer::min);
	   System.out.println("result : " + result);				
	}
	
	public static void main(String[] args) {
		example8();
	}
} 	