package com.example.threaddemo.thread;

import lombok.Data;

@Data
public class SafeCounter {
    private int count = 0;

    public synchronized int increment() {
        return ++count;
    }
}
