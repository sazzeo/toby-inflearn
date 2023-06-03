package com.example.tobyinflearn.servlet;

import com.example.tobyinflearn.hello.HelloController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.PrintWriter;

public class FrontController extends HttpServlet {
    //font-controller 에 page controller 등록하기
    private final HelloController helloController = new HelloController();


    //매핑 : 어떤 코드를 호출할 것인가?
    //바인딩 : 새로운 타입의 형태로 변환해주는 것 , ex) dto 변환
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //인증, 보안, 다국어, 공통 기능
        if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
            String name= req.getParameter("name");
            PrintWriter writer = resp.getWriter();
            writer.println(helloController.hello(name));
        }else if (req.getRequestURI().equals("/user")) {
            //
        }else {
             resp.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }
}
