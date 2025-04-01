package com.example.threaddemo.service;


import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Classname CounterService
 * @Description TODO
 * @Date 2025/4/1 15:55
 * @Author Wilson Chen
 */
@Service
public class CounterService {

//    private final AtomicInteger counter = new AtomicInteger(0);
//
//    public int incrementAndGet() {
//        return counter.incrementAndGet(); // ✅ 线程安全
//    }
    private int counter = 0;

    public int incrementAndGet() {
        int i = ++counter;// ❌ 非原子操作，线程不安全
        System.out.println("当前线程：" + Thread.currentThread().getName() + "，当前计数：" + counter);
        return i;
    }


}
