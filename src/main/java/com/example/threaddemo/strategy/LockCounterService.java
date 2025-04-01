package com.example.threaddemo.strategy;

import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

@Service("lock")
public class LockCounterService implements CounterStrategy {
    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;

    @Override
    public int increment() {
        lock.lock();
        try {
            return ++counter;
        } finally {
            lock.unlock();
        }
    }
}
