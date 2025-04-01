package com.example.threaddemo.thread;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from thread: " + Thread.currentThread().getName());
    }
}
