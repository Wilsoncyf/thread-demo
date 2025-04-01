package com.example.threaddemo.strategy;

import org.springframework.stereotype.Service;

@Service("threadLocal")
public class ThreadLocalCounterService implements CounterStrategy {
    private final ThreadLocal<Integer> localCounter = ThreadLocal.withInitial(() -> 0);

    @Override
    public int increment() {
        int count = localCounter.get() + 1;
        localCounter.set(count);
        return count;
    }
}
