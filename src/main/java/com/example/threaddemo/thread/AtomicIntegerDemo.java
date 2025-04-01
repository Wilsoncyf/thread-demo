package com.example.threaddemo.thread;


/**
 * @Classname AtomicIntegerDemo
 * @Description 测试AtomicInteger
 * @Date 2025/4/1 14:24
 * @Author Wilson Chen
 */
public class AtomicIntegerDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicCounter counter = new AtomicCounter();
        int threadCount = 100000;
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(counter::increment);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("最终计数：" + counter.getCount()); // 预期：100
    }
}
//AtomicInteger 是底层是怎么实现原子性的？
//
