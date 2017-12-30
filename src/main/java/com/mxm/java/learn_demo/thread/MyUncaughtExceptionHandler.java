/**   
* @Title: MyUncaughtExceptionHandler.java 
* @Package com.mxm.java.learn_demo.thread  
* @author maxm@uubee.com  
* @date 2017年12月30日 上午11:48:21 
* @version V1.0   
*/
package com.mxm.java.learn_demo.thread;


/**  
 * @author maxm@uubee.com
 * @date 2017年12月30日 上午11:48:21 
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

	public void uncaughtException(Thread t, Throwable e) {
		System.out.println("发生异常的线程:" + t.getName());
		System.out.println("线程状态:" +  t.isAlive());
		e.printStackTrace();
	}

}
