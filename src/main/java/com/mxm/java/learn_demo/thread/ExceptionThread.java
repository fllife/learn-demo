/**   
* @Title: ExceptionThread.java 
* @Package com.mxm.java.learn_demo.thread  
* @author maxm@uubee.com  
* @date 2017年12月30日 上午11:50:27 
* @version V1.0   
*/
package com.mxm.java.learn_demo.thread;

/**  
 * @author maxm@uubee.com
 * @date 2017年12月30日 上午11:50:27 
 */
public class ExceptionThread implements Runnable{

	public void run() {
	  System.out.println("线程开始异常...");
      throw new RuntimeException();
	}

}
