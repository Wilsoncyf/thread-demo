package com.example.threaddemo.strategy;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service("spin")
public class SpinLockCounterService implements CounterStrategy {
//    private final AtomicBoolean lock = new AtomicBoolean(false);
//    private int counter = 0;
//
//    @Override
//    public int increment() {
//        while (!lock.compareAndSet(false, true)) {
//            // 自旋等待
//        }
//        try {
//            return ++counter;
//        } finally {
//            lock.set(false);
//        }
//    }

    private final AtomicBoolean lock = new AtomicBoolean(false);
    private int counter = 0;
    private static final int MAX_SPIN = 2; // 最多尝试100次

    @Override
    public int increment() {
        int attempts = 0;
        while (!lock.compareAndSet(false, true)) {
            attempts++;
            if (attempts >= MAX_SPIN) {
                System.out.println("自旋失败，执行降级逻辑");
                return -1;
            }
            Thread.yield(); // 避免死循环
        }

        try {
            return ++counter;
        } finally {
            lock.set(false);
        }
    }
}
