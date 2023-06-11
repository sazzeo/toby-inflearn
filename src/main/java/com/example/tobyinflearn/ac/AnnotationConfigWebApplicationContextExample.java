package com.example.tobyinflearn.ac;


import com.example.tobyinflearn.config.BeanConfig;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AnnotationConfigWebApplicationContextExample {

    public void startServer() {
        //DispatcherServlet 이 받는 ac
        AnnotationConfigWebApplicationContext applicationContext = getApplicationContext();
        //application Context 에 bean 등록 (@Component 붙여준거랑 동일)
        registerBeans(applicationContext);
        applicationContext.refresh();
    }



    //익명 클래스로 정의 : onRefresh 재정의
    //onRefresh : refresh() 과정 중에 실행되게 만드는 코드 => 템플릿 메소드 패턴으로 onRefresh() 가 hook 메소드로 정의 되어있음.
    private AnnotationConfigWebApplicationContext getApplicationContext() {
        return new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();   //완전 비어있는 메소드 아니라 super 필요함
                TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();     //톰캣 서버를 만들어주는 도우미 클래스
                serverFactory.setPort(8082);
                WebServer webServer = serverFactory.getWebServer(servletContext ->
                        servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this)) // dispatcher servlet이 ac를 인자로 받아 빈들을 다 뒤져서 controller 정보를 찾음 => 웹 요청마다 매핑
                                .addMapping("/*")
                );
                webServer.start();
            }
        };
    }


    //빈 등록하기
    private void registerBeans(AnnotationConfigWebApplicationContext applicationContext) {
        applicationContext.register(BeanConfig.class); //@Configuration 이 붙은 클래스 넣어준다.
    }




}
