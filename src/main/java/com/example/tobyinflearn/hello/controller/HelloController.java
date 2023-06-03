package com.example.tobyinflearn.hello.controller;

import com.example.tobyinflearn.hello.service.HelloService;
import com.example.tobyinflearn.hello.service.SimpleHelloService;

import java.util.Objects;

public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
