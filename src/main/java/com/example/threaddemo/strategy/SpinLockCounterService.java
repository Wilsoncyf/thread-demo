package com.example.threaddemo.strategy;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service("spin")
public class SpinLockCounterService implements CounterStrategy {
    private final AtomicBoolean lock = new AtomicBoolean(false);
    private int counter = 0;

    @Override
    public int increment() {
        while (!lock.compareAndSet(false, true)) {
            // 自旋等待
        }
        try {
            return ++counter;
        } finally {
            lock.set(false);
        }
    }
}
