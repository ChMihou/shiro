package com.example.restful.demo.Cache;

import org.glassfish.jersey.internal.guava.ThreadFactoryBuilder;

import java.util.concurrent.*;


/**
 * 妥善使用线程池
 */
public class ThreadPool {
    public static void main(String[] args) {
//    其中各个参数说明：
//
//    corePoolSize : 核心线程数，一旦创建将不会再释放。如果创建的线程数还没有达到指定的核心线程数量，将会继续创建新的核心线程，直到达到最大核心线程数后，核心线程数将不在增加；如果没有空闲的核心线程，同时又未达到最大线程数，则将继续创建非核心线程；如果核心线程数等于最大线程数，则当核心线程都处于激活状态时，任务将被挂起，等待空闲线程来执行。
//    maximumPoolSize : 最大线程数，允许创建的最大线程数量。如果最大线程数等于核心线程数，则无法创建非核心线程；如果非核心线程处于空闲时，超过设置的空闲时间，则将被回收，释放占用的资源。
//    keepAliveTime : 当线程空闲时，所允许保存的最大时间，超过这个时间，线程将被释放销毁，但只针对于非核心线程。
//    unit : 时间单位，TimeUnit.SECONDS等。
//    workQueue : 任务队列，存储暂时无法执行的任务，等待空闲线程来执行任务。
//    threadFactory :  线程工程，用于创建线程。本例用到new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();来为线程创建名称。
//    handler : 当线程边界和队列容量已经达到最大时，用于处理阻塞时的程序
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5),namedThreadFactory);
        for (int i = 0 ;i<20 ;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+":");
                    System.out.println("开始执行线程池里面的任务！");
                }
            });
        }
        System.out.println("线程执行完毕");
    }
}
