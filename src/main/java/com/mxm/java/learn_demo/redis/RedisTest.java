/**   
* @Title: RedisTest.java 
* @Package com.mxm.java.learn_demo.redis  
* @author maxm@uubee.com  
* @date 2017年10月4日 上午10:32:40 
* @version V1.0   
*/
package com.mxm.java.learn_demo.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**  
 * @author maxm@uubee.com
 * @date 2017年10月4日 上午10:32:40 
 */
public class RedisTest {
	private static Jedis jedis;
	private static JedisPool jedisPool;
	private static JedisPoolConfig jedisPoolConfig;
	static {
		jedisPoolConfig = new JedisPoolConfig();
	    jedisPool = new JedisPool(jedisPoolConfig,"127.0.0.1",6379);
	    jedis = jedisPool.getResource();
	}
	
	public static void main(String[] args) {
	   jedis.flushDB();
       Set<String> keys = jedis.keys("*");
       for (String key : keys) {
    	   System.out.println(jedis.get(key));
       }
       jedisPool.close();
	}
}
