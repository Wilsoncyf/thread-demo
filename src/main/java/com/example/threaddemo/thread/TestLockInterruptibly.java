package com.example.threaddemo.thread;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname TestLockInterruptibly
 * @Description 测试可中断锁机制
 * @Date 2025/4/1 12:16
 * @Author Wilson Chen
 */
public class TestLockInterruptibly {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("线程A 获得锁，开始睡觉");
                Thread.sleep(10000); // 模拟长时间持有锁
            } catch (InterruptedException e) {
                System.out.println("线程A 被中断");
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("线程B 尝试获取锁");
                lock.lockInterruptibly(); // 可中断地等待锁
                System.out.println("线程B 成功获得锁");
            } catch (InterruptedException e) {
                System.out.println("线程B 在等待锁时被中断 ❌");
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        });

        t1.start();
        Thread.sleep(100); // 确保 t1 拿到锁
        t2.start();
        Thread.sleep(1000); // t2 正在等待锁
        t2.interrupt(); // 中断 t2

    }
}
