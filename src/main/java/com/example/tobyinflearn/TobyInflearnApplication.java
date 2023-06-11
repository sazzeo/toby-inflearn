package com.example.tobyinflearn;

import com.example.tobyinflearn.hello.controller.HelloController;
import com.example.tobyinflearn.hello.service.SimpleHelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class TobyInflearnApplication {
    public static void main(String[] args) {
//        SpringApplication.run(TobyInflearnApplication.class, args);
        startServer();
    }

    /**
     * 1. 내장 톰캣 실행하기
     */
    private static void startServer() {
        //DispatcherServlet 이 받는 ac
        GenericWebApplicationContext applicationContext = getGenericWebApplicationContext();
        //application Context 에 bean 등록 (@Component 붙여준거랑 동일)
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

    }

    //익명 클래스로 정의 : onRefresh 재정의
    //onRefresh : refresh() 과정 중에 실행되게 만드는 코드 => 템플릿 메소드 패턴으로 onRefresh() 가 hook 메소드로 정의 되어있음.
    private static GenericWebApplicationContext getGenericWebApplicationContext() {
        return new GenericWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();        //톰캣 서버를 만들어주는 도우미 클래스
                TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                serverFactory.setPort(8082);
                WebServer webServer = serverFactory.getWebServer(servletContext ->
                        servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this)) // dispatcher servlet이 ac를 인자로 받아 빈들을 다 뒤져서 controller 정보를 찾음 => 웹 요청마다 매핑
                                .addMapping("/*")
                );
                webServer.start();
            }
        };
    }
}

//서블릿 : 자바의 표준 기술
//가장 대표적인 서블릿 : tomcat ,netty 등등 ;  tomcat도 자바로 만들어졌음.
//내장 톰캣 라이브러리도 제공함.