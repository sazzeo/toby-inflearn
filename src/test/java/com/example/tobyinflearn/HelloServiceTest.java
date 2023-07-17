package com.example.tobyinflearn;

import com.example.tobyinflearn.hello.service.SimpleHelloService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    @DisplayName("서비스 테스트 해보기")
    @Test
    void simpleHelloService() {
        //굳이 스프링 안돌리고 테스트 가능
        SimpleHelloService simpleHelloService = new SimpleHelloService();
        final String res = simpleHelloService.sayHello("jeeyoung");
        Assertions.assertThat(res).isEqualTo("hello jeeyoung");
    }
    
}
