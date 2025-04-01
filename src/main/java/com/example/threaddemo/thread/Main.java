package com.example.threaddemo.thread;

public class Main {
    public static void main(String[] args) {
//        MyThread t1 = new MyThread();
//        t1.start(); // 启动线程（不是 run()）

//        Thread t1 = new Thread(new MyRunnable());
//        t1.start();

//        Thread t1 = new Thread(() -> {
//            System.out.println("Hello from Lambda thread: " + Thread.currentThread().getName());
//        });
//        t1.start();

        Thread t1 = createPrinterThread(1, 5);
        Thread t2 = createPrinterThread(6, 10);
        Thread t3 = createPrinterThread(11, 15);
        t1.start();
        t2.start();
        t3.start();
    }

    private static Thread createPrinterThread(int start, int end) {
        return new Thread(() -> {
            System.out.println("Hello from Lambda thread: " + Thread.currentThread().getName());
            for (int i = start; i <= end; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
