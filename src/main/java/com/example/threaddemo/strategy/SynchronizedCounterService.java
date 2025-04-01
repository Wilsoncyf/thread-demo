package com.example.threaddemo.strategy;

import org.springframework.stereotype.Service;

@Service("synchronized")
public class SynchronizedCounterService implements CounterStrategy {
    private int counter = 0;

    @Override
    public synchronized int increment() {
        return ++counter;
    }
}
