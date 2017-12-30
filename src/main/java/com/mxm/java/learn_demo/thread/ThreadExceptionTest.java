/**   
* @Title: ThreadException.java 
* @Package com.mxm.java.learn_demo.thread  
* @author maxm@uubee.com  
* @date 2017年12月30日 上午11:44:37 
* @version V1.0   
*/
package com.mxm.java.learn_demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**  
 * @author maxm@uubee.com
 * @date 2017年12月30日 上午11:44:37 
 */
public class ThreadExceptionTest {
	public static void main(String[] args){
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread());
	}
}
