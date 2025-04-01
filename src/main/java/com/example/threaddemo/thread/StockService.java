package com.example.threaddemo.thread;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @Classname StockService
 * @Description 场景模拟：库存扣减服务（并发锁 + 快速降级）
 * @Date 2025/4/1 13:27
 * @Author  Wilson Chen
 */
public class StockService {
    private int stock = 5; // 初始库存
    private final ReentrantLock lock = new ReentrantLock();

    public void reduceStock(String threadName) {
        try {
            System.out.println(threadName + " 正在尝试获取锁...");
            lock.lockInterruptibly(); // 可中断锁
            try {
                System.out.println(threadName + " 获取到锁，准备扣减库存");
                if (stock > 0) {
                    Thread.sleep(1000); // 模拟处理时间
                    stock--;
                    System.out.println(threadName + " 扣减成功，剩余库存：" + stock);
                } else {
                    System.out.println(threadName + " 库存不足！");
                }
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            System.out.println(threadName + " 等待锁过程中被中断，执行降级逻辑 ❗");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StockService service = new StockService();

        // 创建10个线程同时来抢购
        for (int i = 0; i < 10; i++) {
            final String threadName = "线程-" + i;
            Thread t = new Thread(() -> service.reduceStock(threadName));
            t.start();

            // 模拟某些线程被中断（不等太久）
            if (i == 3 || i == 7) {
                Thread.sleep(200); // 确保这两个线程阻塞中
                t.interrupt(); // 中断它，模拟"不等了，我先撤"
            }
        }
    }
}
