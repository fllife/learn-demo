package com.mxm.java.learn_demo.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁，读锁也叫共享锁，写锁也叫独占锁
 * 1、读写互斥
 * 2、写写互斥
 * 对于读多写少的场景，读写锁比synchronized的吞吐量更好。例如用读写锁实现cache
 * @author maxm@uubee.com
 * @date 2017年11月11日 上午11:56:00
 */
public class ReadWiteLock {
	/**缓存*/
	private Map<String, Object> cache = new HashMap<String, Object>();
	/**读写锁*/
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	/**读锁*/
	private Lock readLock = readWriteLock.readLock();
	
	/**写锁*/
	private Lock witeLock = readWriteLock.writeLock();
	
	/**
	 * 读内存
	 * @param key
	 * @return Object
	 * @author maxm@uubee.com
	 * @date 2017年11月11日 下午12:10:11
	 */
	public Object get(String key) {
		readLock.lock();
		try {
			return cache.get(key);
		} finally {
			readLock.unlock();
		}
	}
	
	/**
	 * 写缓存
	 * @param key
	 * @param object void
	 * @author maxm@uubee.com
	 * @date 2017年11月11日 下午12:11:50
	 */
	public void put(String key, Object object) {
		witeLock.lock();
		try {
			cache.put(key, object);
		} finally {
			witeLock.unlock();
		}
	}

}
