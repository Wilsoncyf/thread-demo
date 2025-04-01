package com.example.threaddemo.thread;

import lombok.Data;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Data
public class LockCounter {
    private int count = 0;
    private final Lock lock = new ReentrantLock();

    public int increment() {
        lock.lock();
        try {
            return ++count;
        } finally {
            lock.unlock();
        }
    }
}
