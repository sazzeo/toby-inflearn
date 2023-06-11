package com.example.tobyinflearn.hello.service;

import org.springframework.stereotype.Service;

public class SimpleHelloService implements HelloService {
    public String sayHello(String name) {
        return "hello " + name;
    }
}
