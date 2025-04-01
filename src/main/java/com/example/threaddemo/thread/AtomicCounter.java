package com.example.threaddemo.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private AtomicInteger count = new AtomicInteger(0);

    public int increment() {
        return count.incrementAndGet(); // ++count，线程安全！
    }

    public int getCount() {
        return count.get();
    }
}
