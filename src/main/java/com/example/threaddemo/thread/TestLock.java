package com.example.threaddemo.thread;


/**
 * @Classname TestLock
 * @Description TODO
 * @Date 2025/4/1 11:28
 * @Author Wilson Chen
 */
public class TestLock {
    public static void main(String[] args) throws InterruptedException {
        SafeCounter counter = new SafeCounter(); // 或 LockCounter

        int threadCount = 10000;
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                counter.increment();
            });
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].join(); // 等待所有线程执行完
        }

        System.out.println("Final count: " + counter.getCount());
    }
}
