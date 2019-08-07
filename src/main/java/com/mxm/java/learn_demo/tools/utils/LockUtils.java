package com.mxm.java.learn_demo.tools.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.util.SafeEncoder;


/**
 * 分布式锁的常用类
 * @author maxianming
 * @date 2018/8/13 15:50
 */
@Slf4j
public class LockUtils {

    private JedisConnectionFactory jedisConnectionFactory;
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    public LockUtils(JedisConnectionFactory jedisConnectionFactory) {
        if (jedisConnectionFactory == null) {
          throw new IllegalArgumentException("jedisConnectionFactory not be allowed null");
        }
        this.jedisConnectionFactory = jedisConnectionFactory;
    }
    /**
     * 尝试获取分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间 ms
     * @return 是否获取成功
     */
    public void lock(String lockKey, String requestId, int expireTime) throws InterruptedException {
        RedisConnection redisConnection = getRedisConnection();
        try {
          while (true) {
               Object result = redisConnection.execute("SET", new byte[][]{
                       SafeEncoder.encode(lockKey), SafeEncoder.encode(requestId), SafeEncoder.encode(SET_IF_NOT_EXIST),
                       SafeEncoder.encode(SET_WITH_EXPIRE_TIME), SafeEncoder.encode(String.valueOf(expireTime))});
               if (result != null) {
                   if (LOCK_SUCCESS.equals(new String((byte[])(result)))) {
                       return;
                   }
               }
              try {
                  Thread.sleep(expireTime);
              } catch (InterruptedException e) {
                  log.error("中断异常", e);
                  throw e;
              }
          }
       } finally {
           redisConnection.close();
       }
    }


    /**
     * 释放分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public boolean unLock(String lockKey, String requestId) {
        RedisConnection redisConnection = getRedisConnection();
        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = redisConnection.eval(SafeEncoder.encode(script), ReturnType.INTEGER, 1, getByteParams(new String[]{lockKey, requestId}));
            if (RELEASE_SUCCESS.equals(result)) {
                return true;
            }
            return false;
        } finally {
            redisConnection.close();
        }

    }

    private byte[][] getByteParams(String... params) {
        byte[][] p = new byte[params.length][];
        for (int i = 0; i < params.length; i++)
            p[i] = SafeEncoder.encode(params[i]);

        return p;
    }

    private RedisConnection getRedisConnection() {
        RedisConnection redisConnection = jedisConnectionFactory.getConnection();
        return redisConnection;
    }

    /*public static void main(String[] args) {
       JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setHostName("101.198.190.171");
        jedisConnectionFactory.setPassword("4497bbd5cf94ea99");

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(40);
        poolConfig.setMaxIdle(10);
        poolConfig.setMinIdle(5);
        jedisConnectionFactory.setPoolConfig(poolConfig);
        jedisConnectionFactory.afterPropertiesSet();
        LockUtils lockUtils =new LockUtils(jedisConnectionFactory);
        Thread thread = new Thread(() -> {
            try {
                lockUtils.lock("lock", "123", 20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("加锁结果:" + "成功");

            boolean result = lockUtils.unLock("lock", "123");

            System.out.println("解锁结果:" + result);
        },"jedis-thread");
        thread.start();
    }*/

}
