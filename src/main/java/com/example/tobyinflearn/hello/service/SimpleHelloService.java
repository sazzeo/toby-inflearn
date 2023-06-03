package com.example.tobyinflearn.hello.service;

public class SimpleHelloService implements HelloService {
    public String sayHello(String name) {
        return "hello " + name;
    }
}
