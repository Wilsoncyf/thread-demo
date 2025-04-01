package com.example.threaddemo.thread;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello from Runnable thread: " + Thread.currentThread().getName());
    }
}
