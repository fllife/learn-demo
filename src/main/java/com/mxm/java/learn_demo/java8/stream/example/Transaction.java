/**   
* @Title: Transaction.java 
* @Package com.mxm.java.learn_demo.java8.stream.example  
* @author maxm@uubee.com  
* @date 2018年1月4日 上午10:23:09 
* @version V1.0   
*/
package com.mxm.java.learn_demo.java8.stream.example;

/**
 * 交易员
 * @author maxm@uubee.com
 * @date 2018年1月4日 上午10:23:09
 */
public class Transaction {
	
	private final Trader trader;
	
	private final int year;
	
	private final int value;

	public Transaction(Trader trader, int year, int value) {
		this.trader = trader;
		this.year = year;
		this.value = value;
	}

	public Trader getTrader() {
		return this.trader;
	}

	public int getYear() {
		return this.year;
	}

	public int getValue() {
		return this.value;
	}

	public String toString() {
		return "{" + this.trader + ", " + "year: " + this.year + ", " + "value:" + this.value + "}";
	}
}
