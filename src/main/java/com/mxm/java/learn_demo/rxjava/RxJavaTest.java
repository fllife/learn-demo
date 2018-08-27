package com.mxm.java.learn_demo.rxjava;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

/**
 * @author maxianming
 * @date 2018/8/14 16:46
 */
@Slf4j
public class RxJavaTest {

    public static void test1() {
        // 第一步：初始化Observable
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            log.info("Observable emit 1" + "\n");
            e.onNext(1);
            log.info("Observable emit 2" + "\n");
            e.onNext(2);
            log.info("Observable emit 3" + "\n");
            e.onNext(3);
            log.info("Observable emit 4" + "\n");
            e.onNext(4);
        }).subscribe(new Observer<Integer>() { // 第三步：订阅

            // 第二步：初始化Observer
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                log.info("onSubscribe收到消息:" + d);
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                i++;
                log.info("onNext收到信息:" + integer);
                if (i == 6) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                log.info("onError : value : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                log.info("onComplete" + "\n");
            }
        });

    }

    public static void test2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                log.info( "Observable thread is : " + Thread.currentThread().getName());
                e.onNext(1);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        log.info("After observeOn(newThread)，value is {} Current thread is {}", integer, Thread.currentThread().getName());
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        log.info("After observeOn(io)，value is {} Current thread is {}", integer, Thread.currentThread().getName());
                    }
                });



    }

    public static void main(String[] args) {
        test2();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
