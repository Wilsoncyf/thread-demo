package com.example.threaddemo.controller;

import com.example.threaddemo.service.CounterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {

    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/api/count/increment")
    public int increment() {
        return counterService.incrementAndGet();
    }
}
