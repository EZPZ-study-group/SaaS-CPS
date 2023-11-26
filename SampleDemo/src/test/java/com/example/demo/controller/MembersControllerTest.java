package com.example.demo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/*

  ** SpringBootTest vs WebMvcTest
  @SpringBootTest에는 웹 애플리케이션 테스트를 지원하는 webEnvironment 속성이 있다. 이 속성을 생략하면 기본값으로 WebEnvironment.MOCK이 설정되어 있는데, 이 설정에 의해서 서블릿 컨테이너가 모킹된다.
  @SpringBootTest(webEnvironment=WebEnvironment.MOCK) 설정으로 모킹한 객체를 의존성 주입받으려면 @AutoConfigureMockMvc를 클래스 위에 추가 해야한다.
  //https://elevatingcodingclub.tistory.com/61
 */
@SpringBootTest
@AutoConfigureMockMvc
class MembersControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    @DisplayName("method: Get")
    public void callGetTest() throws Exception {
        this.mvc.perform(get("/members"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }
}