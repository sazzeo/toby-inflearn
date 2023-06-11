package com.example.tobyinflearn.config;

import com.example.tobyinflearn.hello.controller.HelloController;
import com.example.tobyinflearn.hello.service.HelloService;
import com.example.tobyinflearn.hello.service.SimpleHelloService;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

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

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        serverFactory.setPort(8082);
        return serverFactory;
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }
}

