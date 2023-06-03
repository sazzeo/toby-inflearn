package com.example.tobyinflearn;

import com.example.tobyinflearn.hello.HelloController;
import com.example.tobyinflearn.servlet.FrontController;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.support.GenericApplicationContext;

public class TobyInflearnApplication {

    public static void main(String[] args) {
        startServer();
    }

    /**
     * 1. 내장 톰캣 실행하기
     */
    private static void startServer() {
        //톰캣 서버를 만들어주는 도우미 클래스
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        serverFactory.setPort(8082);

        // WebServer : tomcat , netty 등 서버를 받기 위한 추상화 클래스
        //매개변수로 서블릿들 전달
        WebServer webServer = serverFactory.getWebServer(servletContext ->
                servletContext.addServlet("hello", new FrontController())
                        .addMapping("/*")
        );
        webServer.start();
    }
}

//서블릿 : 자바의 표준 기술
//가장 대표적인 서블릿 : tomcat ,netty 등등 ;  tomcat도 자바로 만들어졌음.
//내장 톰캣 라이브러리도 제공함.