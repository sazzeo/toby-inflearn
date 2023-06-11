package com.example.tobyinflearn.config;

import com.example.tobyinflearn.hello.controller.HelloController;
import com.example.tobyinflearn.hello.service.HelloService;
import com.example.tobyinflearn.hello.service.SimpleHelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Configuration 이 붙은 빈들을 읽는 applicationContext는 AnnotationConfigWebApplicationContext 임
@Configuration
public class BeanConfig {

    
    //팩토리 메소드
    @Bean
    public HelloController helloController(HelloService helloService) {
        return new HelloController(helloService);
    }

    @Bean
    public HelloService helloService() {
        return new SimpleHelloService();
    }
}
