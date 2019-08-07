package com.mxm.java.learn_demo.thread;

/**
 * @author maxianming
 * @date 2019/3/14 10:40
 */
public class ThreadInterruptTest {

    public static void main(String[] args) throws InterruptedException {
      Thread thread = new Thread(() -> {
          int i = 0;
          while (++i < 50000 && !Thread.currentThread().isInterrupted()) {
              System.out.println("interrupted:" + Thread.currentThread().isInterrupted() + ":i:" + i);
          }
      },"thread");

      thread.start();
      Thread.sleep(100);
      thread.interrupt();
      Thread.sleep(3000);
    }
}
