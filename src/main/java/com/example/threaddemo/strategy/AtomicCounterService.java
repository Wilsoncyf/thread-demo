package com.example.threaddemo.strategy;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service("atomic")
public class AtomicCounterService implements CounterStrategy {
    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public int increment() {
        return counter.incrementAndGet();
    }
}
