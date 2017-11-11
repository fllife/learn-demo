/**   
* @Title: TwinsLock.java 
* @Package com.mxm.java.learn_demo.concurrent  
* @author maxm@uubee.com  
* @date 2017年11月11日 上午9:26:36 
* @version V1.0   
*/
package com.mxm.java.learn_demo.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;



/**  
 * 两个线程可以获取共享锁
 * @author maxm@uubee.com 
 * @date 2017年11月11日 上午9:26:36 
 */
public class TwinsLock implements Lock {
	/**
	 * 获取共享锁的数目
	 */
	private final Sync sync = new Sync(2);
	
	public static class Sync extends AbstractQueuedSynchronizer {
		
		/**
		 * 设置同时获取的线程个数
		 * @param count
		 */
		public Sync(int count) {
			if (count <= 0) {
				throw new IllegalArgumentException("count must large 0");
			}
			setState(count);
		}
		
		/**
		 * 获取共享锁
		 */
		@Override
		public int tryAcquireShared(int reduceCount) {
			for (;;) {
				int current = getState();
				int newCount = current - reduceCount;
				System.out.println("获取 current:" +current +"newCount:" +newCount );
				if(newCount < 0 || compareAndSetState(current, newCount)) {
					return newCount;
				}
			}
		}
		
		/**
		 * 释放共享锁
		 */
		@Override
		public boolean tryReleaseShared(int returnCount) {
			for(;;) {
				int current = getState();
				int newCount = current + returnCount;
				System.out.println("释放 current:" +current +"newCount:" +newCount );
				if (compareAndSetState(current, newCount)) {
					return true;
				}
			}
		}
		
	}

	public void lock() {
		while (sync.tryAcquireShared(1) < 0) {
			System.out.println("lock...");
		}
	}
    
	public void unlock() {
        sync.tryReleaseShared(1);
	}
	
	public void lockInterruptibly() throws InterruptedException {

	}
	public Condition newCondition() {
		return null;
	}

	public boolean tryLock() {
		return false;
	}

	public boolean tryLock(long arg0, TimeUnit arg1) throws InterruptedException {
		return false;
	}
	
	public static void main(String[] args) {
		final TwinsLock lock = new TwinsLock();
		/**线程池所有任务结束后 ，阻塞10分钟*/
		ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 600L, TimeUnit.SECONDS,
                                                                    new SynchronousQueue<Runnable>());
	    for (int i = 0 ;  i < 100; i++) {
	        final int number = i;
	        executorService.execute(new Runnable() {
				
				public void run() {
				    lock.lock();
					System.out.println("线程-" +number + "获取锁");
					lock.unlock();
					System.out.println("线程-" +number + "释放锁");
				}
			});
	    }
	}


}
