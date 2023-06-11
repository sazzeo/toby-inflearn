package com.example.tobyinflearn;

import com.example.tobyinflearn.ac.AnnotationConfigWebApplicationContextExample;
import com.example.tobyinflearn.ac.GenericWebApplicationContextExample;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class TobyInflearnApplication {
    public static void main(String[] args) {
//        new GenericWebApplicationContextExample().startServer();
        new AnnotationConfigWebApplicationContextExample().run(TobyInflearnApplication.class);
    }

}

//서블릿 : 자바의 표준 기술
//가장 대표적인 서블릿 : tomcat ,netty 등등 ;  tomcat도 자바로 만들어졌음.
//내장 톰캣 라이브러리도 제공함.