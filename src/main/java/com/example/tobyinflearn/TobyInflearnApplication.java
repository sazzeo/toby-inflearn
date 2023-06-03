package com.example.tobyinflearn;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;

public class TobyInflearnApplication {

    public static void main(String[] args) {
        System.out.println("hello Containerless Standalone Application");

        startServer();
    }

    /** 1. 내장 톰캣 실행하기 */
    private static void startServer() {
        //톰캣 서버를 만들어주는 도우미 클래스
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        serverFactory.setPort(8082);

        // WebServer : tomcat , netty 등 서버를 받기 위한 추상화 클래스
        //매개변수로 서블릿들 전달
        WebServer webServer = serverFactory.getWebServer(servletContext ->
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                    resp.setStatus(HttpStatus.OK.value());
                    resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                    PrintWriter writer = resp.getWriter();
                    writer.println("hello servlet");
                }
            }).addMapping("/hello" )
        );
        webServer.start();
    }
}

//서블릿 : 자바의 표준 기술
//가장 대표적인 서블릿 : tomcat ,netty 등등 ;  tomcat도 자바로 만들어졌음.
//내장 톰캣 라이브러리도 제공함.