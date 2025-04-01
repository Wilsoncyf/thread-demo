package com.example.threaddemo.strategy;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Service("lock")
public class LockCounterService implements CounterStrategy {
    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;

//    @Override
//    public int increment() {
//        lock.lock();
//        try {
//            return ++counter;
//        } finally {
//            lock.unlock();
//        }
//    }
@Override
public int increment() {
    try {
        // 尝试在 100ms 内获取锁，否则失败降级
        if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
            try {
                return ++counter;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("获取锁超时，执行降级逻辑");
            return -1; // 降级标志
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return -1;
    }
}
}
