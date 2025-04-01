# Java 并发线程安全实战示例

这是一个用于学习和演示 Java 并发编程与线程安全设计的 Spring Boot 项目，涵盖多种并发控制手段，包括：

- synchronized & ReentrantLock
- 可中断锁机制（lockInterruptibly）
- 原子变量（AtomicInteger）
- 并发集合（ConcurrentHashMap）
- 线程隔离（ThreadLocal）
- 多线程并发测试

---

## 📁 项目结构

```
src/main/java/com/example/threaddemo/thread/
├── Main.java                    // 线程创建演示（Lambda/Runnable/Thread）
├── UnsafeCounter.java           // 非线程安全计数器
├── SafeCounter.java             // synchronized 线程安全计数器
├── LockCounter.java             // ReentrantLock 示例
├── AtomicCounter.java           // AtomicInteger 示例
├── AtomicIntegerDemo.java       // 原子操作测试
├── TestLock.java                // 基础 Lock 用法
├── TestLockInterruptibly.java   // 可中断锁 + 降级机制演示
├── StockService.java            // 秒杀库存服务，带降级处理
```

---

## 🚀 快速开始

确保你已安装：

- JDK 8 或以上
- Maven

```bash
# 构建项目
./mvnw clean install

# 启动项目
./mvnw spring-boot:run
```

---

## 💡 学习建议

> 如果你正在学习 Java 并发、Spring Boot 中的线程安全问题，这个项目能给你很多帮助，适合系统化练习和改造。

---

## 🔗 作者

Wilson Chen  
GitHub: [@Wilsoncyf](https://github.com/Wilsoncyf)

---