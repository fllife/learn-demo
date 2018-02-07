package com.mxm.java.learn_demo.zookeeper.queue;

import org.apache.zookeeper.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 提供分布式先进先出队列实现生产者消费者模式
 * Create by maxianming 2018/2/7 11:37
 */
public class FIFODistributedQueue {
    private static String ZK_IP = "127.0.0.1";
    private static Integer ZK_PORT = 2181;
    private static int TIME_OUT = 3000;
    private static ZooKeeper zooKeeper;
    private static String ROOT_PATH = "/fifo_queue";
    private static String QUEUE_PATH = ROOT_PATH + "/queue_";
    // 锁
    Object lock = new Object();

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

    /**
     * 初始化方法
     */
    public static synchronized void init() {
        try {
            if (zooKeeper.exists(ROOT_PATH,false) == null) {
                zooKeeper.create(ROOT_PATH, ROOT_PATH.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   public FIFODistributedQueue() {
       init();
    }

    /**
     * 入队操作。不会阻塞
     * @param object
     */
    public void offer(Object object) throws Exception {
        zooKeeper.create(QUEUE_PATH, objectToBytes(object), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    /**
     * 出队操作，队列为空时候阻塞
     * @return
     */
    public Object take() throws Exception {
        while (true) {
            List<String> children = zooKeeper.getChildren(ROOT_PATH, (WatchedEvent event) -> {
               if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                   synchronized (lock) {
                       lock.notify();
                   }
               }
            });
            if (children != null) {
                if (children.size() <= 0) {
                    synchronized (lock) {
                        lock.wait();
                    }
                } else {
                    Collections.sort(children);
                    String minPath = children.get(0);
                    String path = ROOT_PATH + "/" + minPath;
                    byte[] data = null;
                    try {
                        data = zooKeeper.getData(path, false, null );
                        zooKeeper.delete(path, -1);

                    } catch (KeeperException exception) {
                        if (KeeperException.Code.NONODE == exception.code()) {
                            continue;
                        }
                        throw exception;
                    }
                    Object result = bytesToObject(data);
                    return result;
                }
            }
        }
    }

    /**
     * 对象转化为字节数组
     * @param obj
     * @return
     * @throws Exception
     */
    private byte[] objectToBytes(Object obj) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream sOut = new ObjectOutputStream(out);
        sOut.writeObject(obj);
        sOut.flush();
        byte[] bytes = out.toByteArray();
        return bytes;
    }

    /**
     * 字节数组转化为对象
     * @param bytes
     * @return
     * @throws Exception
     */
    private Object bytesToObject(byte[] bytes) throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream sIn = new ObjectInputStream(in);
        return sIn.readObject();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 12; i++) {
            final int j = i;
            executorService.execute(() -> {
                FIFODistributedQueue queue = new FIFODistributedQueue();
                try {
                    System.out.println("生产者-" + j + "-生产数据+++");
                    queue.offer(j);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        for (int i = 0; i < 10 ; i++) {
            final int k = i;
            executorService.execute(() -> {
                FIFODistributedQueue queue = new FIFODistributedQueue();
                System.out.println("消费者-" + k + "-消费数据-");
                try {
                    Object object = queue.take();
                    System.out.println("消费者-" + k + "-获取数据:" + object);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
