package com.mxm.java.learn_demo.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * 优点：实现比较简单，有通知机制，能提供较快的响应，有点类似reentrantlock的思想，对于节点删除失败的场景由Session超时保证节点能够删除掉。
 * 缺点：重量级，同时在大量锁的情况下会有“惊群”的问题。
 * Create by maxianming 2018/1/30 20:37
 */
public class UniqueDistributedLock {

    private static String ZK_IP = "127.0.0.1";
    private static Integer ZK_PORT = 2181;
    private static int TIME_OUT = 3000;
    private static String ROOT_PATH = "/lock";
    private static String LOCK_PATH = ROOT_PATH + "/locked";
    private Object lock;
    private final Thread currentThread;
    private static ZooKeeper zooKeeper;

    static {
        try {
            zooKeeper = new ZooKeeper(ZK_IP + ":" + ZK_PORT, TIME_OUT, (WatchedEvent event) -> {
                 if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                     System.out.println("connection is establish...");
                 }
            });
            if (zooKeeper.exists(ROOT_PATH,false) == null) {
                zooKeeper.create(ROOT_PATH, ROOT_PATH.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public UniqueDistributedLock () {
        this.currentThread = Thread.currentThread();
        this.lock = new Object();
    }

    private Boolean isLocked(String path) throws KeeperException, InterruptedException {
        Stat result = zooKeeper.exists(path, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.NodeDeleted) {
                    synchronized (lock) {
                        lock.notify();
                    }
                }
            }
        });
        if (result != null) {
            return true;
        }
        return false;
    }

    /**
     * 获得锁操作
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void lock() throws KeeperException, InterruptedException {
        while (true) {
           while (isLocked(LOCK_PATH)) {
               synchronized (lock) {
                   lock.wait(1000);
               }
            }
            try {
                zooKeeper.create(LOCK_PATH, String.valueOf(currentThread.getId()).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                System.out.println(Thread.currentThread().getName() + "获取分布式锁");
                return;
            } catch (KeeperException ex) {
                if (KeeperException.Code.NODEEXISTS == ex.code()) {
                    System.out.println(this.currentThread.getName() + "获取分布式锁锁失败");
                    continue;
                }
                throw ex;
            }
        }
    }

    /**
     * 释放锁操作
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void unLock() throws KeeperException, InterruptedException{
        byte[] data = zooKeeper.getData(LOCK_PATH,false,null);
        long id = Long.valueOf(new String(data));
        if (id == this.currentThread.getId()) {
            zooKeeper.delete(LOCK_PATH,-1);
            System.out.println(this.currentThread.getName() + "释放锁");
        } else {
            System.out.println(this.currentThread.getName() + "未持有锁");
        }
    }

    public static void main(String[] args) throws Exception {
        // 1、获取分布式锁
       /* ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            service.execute(() -> {
                DistributedLock lock = new DistributedLock();
                try {
                    lock.lock();
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        lock.unLock();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
        }*/

       // 2、持有锁的线程才能释放锁了，用线程ID标识持有锁的线程，更可靠的可以使用UUID
        new Thread(() -> {
            try {
                new UniqueDistributedLock().lock();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            try {
                new UniqueDistributedLock().unLock();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
