/**   
* @Title: Trader.java 
* @Package com.mxm.java.learn_demo.java8.stream.example  
* @author maxm@uubee.com  
* @date 2018年1月4日 上午10:24:25 
* @version V1.0   
*/
package com.mxm.java.learn_demo.java8.stream.example;

/** 
 * 交易员
 * @author maxm@uubee.com
 * @date 2018年1月4日 上午10:24:25 
 */
public class Trader {
	
	private final String name;
	
	private final String city;
	
	public Trader(String n, String c){
		this.name = n;
		this.city = c;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getCity(){
		return this.city;
	}
	
	public String toString(){
		return "Trader:"+this.name + " in " + this.city;
	}
}
