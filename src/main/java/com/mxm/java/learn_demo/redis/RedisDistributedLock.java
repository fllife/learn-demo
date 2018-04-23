/**   
* @Title: RedisDistributedLock.java
* @Package com.mxm.java.learn_demo.redis  
* @author maxm@uubee.com  
* @date 2017年10月4日 上午10:32:40 
* @version V1.0   
*/
package com.mxm.java.learn_demo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**  
 * @author maxm@uubee.com
 * @date 2017年10月4日 上午10:32:40 
 */
public class RedisDistributedLock {
	private static JedisPool jedisPool;
	private static JedisPoolConfig jedisPoolConfig;
	private static final String REDIS_LOCK = "redis_lock";
	private static final int LOCK_EXPIRE_SENCONDS = 2;
	private volatile boolean isLock = false;

	static {
		jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(1000);
	    jedisPool = new JedisPool(jedisPoolConfig,"118.190.204.179",6380, 2000, "redis-mxm");
	}

	public Jedis getJedis() {
		Jedis jedis = jedisPool.getResource();
		return  jedis;
	}

	public boolean lock() {
		Jedis jedis = getJedis();
	    while(true) {
			long start = System.currentTimeMillis();
	    	String expireTime = String.valueOf(start + LOCK_EXPIRE_SENCONDS * 1000);
	        if (jedis.setnx(REDIS_LOCK, expireTime) == 1) {
				jedis.expire(REDIS_LOCK, LOCK_EXPIRE_SENCONDS);
				isLock = true;
				jedis.close();
	        	return true;
			}
			String currentValue = jedis.get(REDIS_LOCK);
	        if (Long.valueOf(currentValue) < System.currentTimeMillis()) {
	        	String oldValue = getJedis().getSet(REDIS_LOCK, expireTime);
	        	if (oldValue.equals(currentValue)) {
	        		isLock = true;
					jedis.close();
	        		return true;
				}
			}

        }
	}

	public synchronized void unLock() {
		Jedis jedis = getJedis();
		String expireTime = jedis.get(REDIS_LOCK);
		if (isLock && Long.valueOf(expireTime) < System.currentTimeMillis()) {
			jedis.del(REDIS_LOCK);
			isLock = false;
		}
		jedis.close();
	}
	public static void main(String[] args) {
		RedisDistributedLock lock = new RedisDistributedLock();
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			service.execute(() -> {
				lock.lock();
				System.out.println(Thread.currentThread().getName() + "获取锁");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.unLock();
				System.out.println(Thread.currentThread().getName() + "释放锁");
			});
		}
	}
}
