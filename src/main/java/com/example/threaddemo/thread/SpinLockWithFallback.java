package com.example.threaddemo.thread;


import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Classname SpinLockWithFallback
 * @Description 模拟自旋锁，两个线程竞争一个“自旋锁”，
 * 每个线程最多自旋尝试 5 次，如果 5 次都失败，
 * 就放弃抢锁，走降级逻辑
 * @Date 2025/4/1 15:22
 * @Author Wilson Chen
 */
public class SpinLockWithFallback {
    private final AtomicBoolean lock = new AtomicBoolean(false);

    // 自旋 + 放弃
    public boolean tryLockWithSpin(String threadName, int maxSpin) {
        for (int i = 1; i <= maxSpin; i++) {
            if (lock.compareAndSet(false, true)) {
                System.out.println(threadName + " ✅ 成功获取锁（第 " + i + " 次尝试）");
                return true;
            } else {
                System.out.println(threadName + " ❌ 第 " + i + " 次自旋失败，继续尝试...");
                // 小小退让一下，模拟 backoff
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.println(threadName + " 🚨 自旋 " + maxSpin + " 次失败，放弃抢锁，执行降级处理");
        return false;
    }

    public void unlock(String threadName) {
        lock.set(false);
        System.out.println(threadName + " 🔓 释放锁");
    }

    public static void main(String[] args) {
        SpinLockWithFallback spinLock = new SpinLockWithFallback();

        Thread t1 = new Thread(() -> {
            if (spinLock.tryLockWithSpin("线程-A", 5)) {
                try {
                    Thread.sleep(500); // 模拟业务执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    spinLock.unlock("线程-A");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            if (spinLock.tryLockWithSpin("线程-B", 5)) {
                try {
                    Thread.sleep(200); // 模拟业务执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    spinLock.unlock("线程-B");
                }
            }
        });

        t1.start();
        try {
            Thread.sleep(10); // 保证 t1 先抢到锁
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
