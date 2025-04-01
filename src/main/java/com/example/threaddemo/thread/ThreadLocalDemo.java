package com.example.threaddemo.thread;


/**
 * @Classname ThreadLocalDemo
 * @Description ThreadLocal的基本使用
 * @Date 2025/4/1 15:35
 * @Author Wilson Chen
 */
public class ThreadLocalDemo {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        Runnable task = () -> {
            String name = Thread.currentThread().getName();
            threadLocal.set(name);
            System.out.println(name + " 设置了值：" + threadLocal.get());
            threadLocal.remove(); // 最好手动清理
        };

        new Thread(task, "线程A").start();
        new Thread(task, "线程B").start();
        new Thread(task, "线程C").start();
        new Thread(task, "线程D").start();
    }
}
