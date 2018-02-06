package com.mxm.java.learn_demo.zookeeper.queue;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create by maxianming 2018/2/6 15:16
 */
public class SyncDistributedQueue {
    private static String ZK_IP = "127.0.0.1";
    private static Integer ZK_PORT = 2181;
    private static int TIME_OUT = 3000;
    private static ZooKeeper zooKeeper;
    // 根节点
    private static String SYNC_PATH = "/synchronized";
    // 队列元素
    private static String MEMBER_PATH = SYNC_PATH + "/member_";
    // 队列节点
    private static String START_PATH = "/start";
    // 同步队列大小
    private volatile int size = 0;
    // 锁对象
    private Object lock = new Object();

    static {
        try {
            zooKeeper = new ZooKeeper(ZK_IP + ":" + ZK_PORT, TIME_OUT, (WatchedEvent event) -> {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    System.out.println("connection is establish...");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void init() {
        try {
            if (zooKeeper.exists(SYNC_PATH,false) == null) {
                zooKeeper.create(SYNC_PATH, SYNC_PATH.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            if (zooKeeper.exists(START_PATH, false) != null) {
                zooKeeper.delete(START_PATH, -1);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public SyncDistributedQueue(int size) {
        this.size = size;
        init();
    }

    public void await() throws KeeperException, InterruptedException {
       Stat znodeStat = zooKeeper.exists(START_PATH, (WatchedEvent event) -> {
           if (event.getType() == Watcher.Event.EventType.NodeCreated) {
               System.out.println("start path create");
               synchronized (lock) {
                   lock.notify();
               }
           }
       });

       if (znodeStat != null) {
           return;
       }
       zooKeeper.create(MEMBER_PATH, MEMBER_PATH.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
       List<String> children = zooKeeper.getChildren(SYNC_PATH, false);
       if (children.size() >= size ) {
           if (zooKeeper.exists(START_PATH, false) == null) {
               zooKeeper.create(START_PATH, START_PATH.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
           }
       } else {
           synchronized (lock) {
               lock.wait();
           }
       }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 5; i++) {
            executorService.execute(()->{
                SyncDistributedQueue syncQueue = new SyncDistributedQueue(5);
                System.out.println(Thread.currentThread().getName() + "开始执行");
                try {
                    Thread.sleep(new Random().nextInt(3000));
                    syncQueue.await();
                    System.out.println(Thread.currentThread().getName() + "结束执行");
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
    }
}
