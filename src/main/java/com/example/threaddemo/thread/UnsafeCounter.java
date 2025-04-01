package com.example.threaddemo.thread;

import lombok.Data;

@Data
public class UnsafeCounter {
    private int count = 0;

    public int increment() {
        return ++count;
    }
}
