/**   
* @Title: RedisTest.java 
* @Package com.mxm.java.learn_demo  
* @author maxm@uubee.com  
* @date 2017年11月1日 下午4:24:43 
* @version V1.0   
*/
package com.mxm.java.learn_demo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**  
 * @author maxm@uubee.com
 * @date 2017年11月1日 下午4:24:43 
 */
public class RedisTest extends BaseTest {
	@Autowired
	private ShardedJedisPool shardedJedisPool;
	
	@Test
	public void testSet() {
		ShardedJedis shardedJedis = shardedJedisPool.getResource();
		String result = shardedJedis.set("test", "mxm");
		String value = shardedJedis.get("test");
		System.out.println("result: " + result + "value :" + value);
	}
}
