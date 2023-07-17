package com.example.tobyinflearn;

import com.example.tobyinflearn.hello.controller.HelloController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HelloControllerTest {
    //helloController는 service에 의존되어있는데 고립시켜서 테스트해야함
    @DisplayName("의존된 service 익명 클래스로 구현해서 만들어보기")
    @Test
    void helloController() {
        //테스트 스텁
        HelloController helloController = new HelloController(name -> "hello " + name); //테스트에서 사용하는 수동 DI
        String res = helloController.hello("jeeyoung");
        assertThat(res).isEqualTo("hello jeeyoung");
    }

    @DisplayName("hello controller 실패 테스트: null이나 공백이 들어왔을때")
    @Test
    void failsHelloController() {
        HelloController helloController = new HelloController(name -> "hello " + name);

        assertThatThrownBy(() ->
                helloController.hello(null)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() ->
                helloController.hello("")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
