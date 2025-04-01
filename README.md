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
thread-demo/
├── src/
│   ├── main/
│   │   ├── java/com/example/threaddemo/
│   │   │   ├── controller/           ← 对外接口
│   │   │   ├── service/              ← 业务逻辑
│   │   │   ├── lock/                 ← 锁实现模块（Lock、CAS、自旋）
│   │   │   ├── context/              ← ThreadLocal 上下文隔离（可选）
│   │   │   └── ThreadDemoApplication.java
│   │   └── resources/application.properties
│   └── test/...
├── stress-test/                      ← Python 并发压测脚本
├── README.md
├── pom.xml
          
```

---

## 线程安全策略测试接口

GET /api/count/increment?type={strategy}

| type 值         | 实现方式        |
|-----------------|----------------|
| atomic          | AtomicInteger |
| synchronized    | synchronized 关键字 |
| lock            | ReentrantLock |
| spin            | 自旋锁（CAS） |
| threadLocal     | 每线程独立计数（非共享） |

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

