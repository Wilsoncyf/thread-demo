package com.example.threaddemo.controller;

import com.example.threaddemo.strategy.CounterStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/api/count")
public class CounterController {
//    // 通过@Qualifier 指定具体的实现
//    private final CounterStrategy counter;
//
//    public CounterController(@Qualifier("threadLocal") CounterStrategy counter) {
//        this.counter = counter;
//    }
//
//    @GetMapping("/increment")
//    public int increment() {
//        return counter.increment();
//    }

    private final ApplicationContext applicationContext;
    private final Set<String> supportedStrategies = Set.of(
            "atomic", "lock", "synchronized", "spin", "threadLocal"
    );

    @Autowired
    public CounterController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping("/increment")
    public Object increment(@RequestParam(defaultValue = "atomic") String type) {
        if (!supportedStrategies.contains(type)) {
            return Map.of("error", "不支持的策略类型：" + type);
        }

        CounterStrategy strategy = applicationContext.getBean(type, CounterStrategy.class);
        int result = strategy.increment();

        if (result == -1) {
            return Map.of(
                    "type", type,
                    "status", "fail",
                    "message", "系统繁忙，请稍后重试"
            );
        }

        return Map.of(
                "type", type,
                "result", result
        );
    }
}
