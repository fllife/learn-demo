package com.mxm.java.learn_demo.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 优点：每个节点只需要watch序号比自己小的节点
 * 时序性独占锁
 * Create by maxianming 2018/1/31 14:48
 */
public class SequenceDistributedLock {
    private static String ZK_IP = "127.0.0.1";
    private static Integer ZK_PORT = 2181;
    private static int TIME_OUT = 3000;
    private static String ROOT_PATH = "/lock";
    private static String LOCK_PATH = ROOT_PATH + "/locked_";
    private LockObject lock;
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

   public SequenceDistributedLock() {
        this.currentThread = Thread.currentThread();
        this.lock = new LockObject();
   }

    /**
     * 锁对象
     */
   private class LockObject {
        private boolean notify = false;

        public boolean isNotify() {
            return notify;
        }
        public void setNotify(boolean notify) {
            this.notify = notify;
        }
   }

   public void lock() throws KeeperException, InterruptedException {
       String path = zooKeeper.create(LOCK_PATH, String.valueOf(this.currentThread.getId()).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
       path = path.substring(path.lastIndexOf("/") + 1);
       String lastWatchPath = "";
       boolean isNotify = false;
       while (true) {
           List<String> children = zooKeeper.getChildren(ROOT_PATH,false);
           if (StringUtils.isEmpty(children)) {
               System.out.println(this.currentThread.getName() + "获得锁");
               return;
           } else {
               Collections.sort(children);
               String minPath = children.get(0);
               if (minPath != null && path.equals(minPath)) {
                   System.out.println(this.currentThread.getName() + "获得锁");
                   return;
               } else {
                   // 1、获取应该监控的节点
                   String watchPath = getWatchPath(path, children);

                   // 2、监控指定的节点。节点删除则会收到通知
                   watchPath(ROOT_PATH + "/" + watchPath);

                   // 3、线程阻塞
                   if (!lock.isNotify()) {
                       synchronized (lock) {
                           lock.wait();
                       }
                   }
               }

           }
       }

    }

    /**
     * watch指定path
     * @param watchPath
     * @throws KeeperException
     * @throws InterruptedException
     */
    private String watchPath(String watchPath) throws KeeperException, InterruptedException {
        Stat result = zooKeeper.exists(watchPath, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                 if(event.getType() == Event.EventType.NodeDeleted) {
                     synchronized (lock) {
                         System.out.println(event.getPath() + ":路径被删除,通知线程:" + currentThread.getName());
                         lock.notify();
                         lock.setNotify(true);
                     }

                 }
            }
        });
        if (result == null) {
            synchronized (lock) {
                lock.notify();
            }
        }
        return watchPath;
    }

    /**
     * 获取小于当前path的顺序最大的子节点路径
     * @param path
     * @param children
     * @return
     */
    private String getWatchPath(String path, List<String> children) {
        for (int i = children.size() - 1 ; i > 0; i--) {
            String childPath = children.get(i);
            if(childPath.compareTo(path) < 0) {
               return childPath;
            }
        }
        return children.get(0);
    }


    public void unLock() throws KeeperException, InterruptedException {
       List<String> children = zooKeeper.getChildren(ROOT_PATH,false);
       Collections.sort(children);
       String minPath = children.get(0);
       if (!StringUtils.isEmpty(minPath)) {
           byte[] data = zooKeeper.getData(ROOT_PATH + "/" + minPath,false, null);
           long threadId = Long.valueOf(new String(data));
           if (threadId == this.currentThread.getId()) {
               zooKeeper.delete(ROOT_PATH + "/" + minPath,-1);
               System.out.println(this.currentThread.getName() + "释放锁");
           }
       }
   }


    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                SequenceDistributedLock lock = new SequenceDistributedLock();
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
        }
    }

}
