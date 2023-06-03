package com.example.tobyinflearn.servlet;

import com.example.tobyinflearn.hello.controller.HelloController;
import com.example.tobyinflearn.hello.service.HelloService;
import com.example.tobyinflearn.hello.service.SimpleHelloService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;

public class FrontController extends HttpServlet {


    //매핑 : 어떤 코드를 호출할 것인가?
    //바인딩 : 새로운 타입의 형태로 변환해주는 것 , ex) 파라미터 dto 변환
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //스프링 컨테이너 객체 만들기
        GenericApplicationContext ac = new GenericApplicationContext();
        //빈 등록하기
        ac.registerBean(HelloController.class);
        ac.registerBean(SimpleHelloService.class);
        //컨테이너 초기화
        ac.refresh();
        HelloController helloController = ac.getBean(HelloController.class);

        //인증, 보안, 다국어, 공통 기능... 넣기
        if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
            String name = req.getParameter("name");
            resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
            PrintWriter writer = resp.getWriter();
            writer.println(helloController.hello(name));
        } else if (req.getRequestURI().equals("/user")) {
            //
        } else {
            resp.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }
}
