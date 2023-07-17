package com.example.tobyinflearn;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class HelloApiTest {

    @DisplayName("TestRestTemplate 사용해서 요청 테스트 해보기")
    @Test
    void helloApiTest() {
        //test 용 restTemplate 클래스
        TestRestTemplate rest = new TestRestTemplate();
        ResponseEntity<String> res = rest.getForEntity("http://localhost:8082/hello?name={name}", String.class, "spring");
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        assertThat(res.getBody()).isEqualTo("*hello spring*");
    }

    @DisplayName("api 실패 테스트 : name가 null일 때")
    @Test
    void failsHelloApiTest() {
        //test 용 restTemplate 클래스
        TestRestTemplate rest = new TestRestTemplate();
        ResponseEntity<String> res = rest.getForEntity("http://localhost:8082/hello?name={name}", String.class ,"");

        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}