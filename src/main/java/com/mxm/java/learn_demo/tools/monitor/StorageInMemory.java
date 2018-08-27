package com.mxm.java.learn_demo.tools.monitor;

import com.mxm.java.learn_demo.tools.helper.NameThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author maxianming
 * @date 2018/8/15 14:56
 */
@Slf4j
public class StorageInMemory implements ExceptionReport {

    private final String THREAD_POOL_NAME = "exception_store";

    private final Integer DEGAULT_CAPCITY = 100;

    private Integer capacity;

    private LinkedBlockingQueue<ExceptionMesssage> linkedBlockingQueue;

    public ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(60), new NameThreadFactory(THREAD_POOL_NAME));

    public StorageInMemory(Integer capacity) {
        if (capacity == null) {
            this.capacity = DEGAULT_CAPCITY;
        } else {
            this.capacity = capacity;
        }
        linkedBlockingQueue = new LinkedBlockingQueue(this.capacity);
    }


    @Override
    public void pushMessage(ExceptionMesssage messsage) {
        threadPoolExecutor.submit(() -> {
            try {
                linkedBlockingQueue.add(messsage);
            } catch (Exception e) {
                log.error("队列满",e);
                throw e;
            }
        });
    }

    @Override
    public ExceptionMesssage pullMessage() {
        return linkedBlockingQueue.poll();
    }
}
