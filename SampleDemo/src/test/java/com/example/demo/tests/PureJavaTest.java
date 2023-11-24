package com.example.demo.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class PureJavaTest {
    @Test
    @DisplayName("기본 덧셈")
    void case1(){
        PureJava purejava = new PureJava();
        purejava.add(1,2,3,4,5);
    }

    @Test
    @DisplayName("매개변수 없이 덧셈")
    void case2(){
        PureJava purejava = new PureJava();
        purejava.add();
    }

    @Test
    @DisplayName("하지만 오브젝트가 널이라면?")
    void case3(){
        PureJava purejava = null;
        purejava.add();
    }

/*
    결과 값이 생각한 값과 일치하는지 확인: assertEquals(expected, actual[, message])
    null이 아닌지 확인:                assertNotNull(actual[, message])
    참인지 확인       :                assertTrue(boolean[, message])
    모든 확인 구문 확인:                assertAll(executables...[, message])
    예외 발생 확인     :               assertThrows(expectedType, executable[, message])
    특정 시간 안에 실행이 되는지 확인:    assertTimeout(duration, executable[, message])
 */

    @Test
    @DisplayName("하지만 오브젝트가 널이라면2?")
    void case3_2(){
        PureJava purejava = null;
        purejava.add();

        assertNotNull(purejava);
    }

    @Test
    @DisplayName("하지만 오브젝트가 널이라면3?")
    void case3_3(){
        PureJava purejava = null;
        assertNotNull(purejava, "이렇게도 된다.");
    }

    @Test
    @DisplayName("예상값과 맞는지를 확인 해야할 때")
    void case4(){
        PureJava purejava = new PureJava();
        int data = purejava.add(1,2,3,4,5);

        assertEquals(data, 1);
        //assertEquals(data, 15);
    }

    @Test
    @DisplayName("여러조건들을 전부 만족시키고싶어")
    void case5(){
        PureJava purejava = new PureJava();
        assertAll(
                () -> assertNotNull(purejava, "1번조건"),
                () -> assertEquals(purejava.add(1,2,4,5), 12, "2번조건")
        );
    }

    @Test
    @DisplayName("Timeout 테스트")
    void case6(){
        //기본적인 로직 수행시간 테스트
        assertTimeout(Duration.ofMillis(100), () -> {
            Thread.sleep(1000);
        }, "시간초과");

        /*
        //얼추 시간 맞춰서 테스트를 종료할 때
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            Thread.sleep(1000);
        }, "시간초과~");
        해당 메소드는 사용에 주의 필요.
        테스트 로직이 ThreadLocal한 데이터를 보유하고있다면 로직 중간에 테스트가 종료되어 트랜잭션 롤백이 안되는등의 문제가 발생할 수 있음
         */
    }
    
    @Test
    @DisplayName("조건에 따라 수행하고싶을 때")
    void case7(){
        String server = "LOCAL";
        assumingThat("real".equalsIgnoreCase(server), () -> System.out.println(server));
        assumingThat("stg".equalsIgnoreCase(server), () -> System.out.println(server));
        assumingThat("LoCal".equalsIgnoreCase(server), () -> System.out.println(server));
    }

    /*위에처럼 코드로 하는거 너무 지저분해 보일 때는 아래 어노테이션 조합으로 가능
    @Enabled[OnOs / OnJre / EnvironmentVariable]  // ex) @EnabledOnOs({OS.LINUX, OS.WINDOWS})
                                                         @EnabledEnvironmentVariable(named = "SERVER", matches = "LOCAL")
    @Disabled[OnOs / OnJre / EnvironmentVariable] // ex) @DisabledOnJre({JRE.JAVA_8})
    */

    /*Tag를 달아서 그룹단위로 테스트를 진행할 수 있다.
      매번 @Tag("case1") 다는것도 귀찮으니 커스텀 어노테이션으로 만들어서 조금 덜 귀찮게 처리할 수 있다.
     */

    // 테스트를 케이스별로 한번만 할 수는 없잖음~
    @RepeatedTest(3)
    @DisplayName("테스트 반복수행!")
    void repeatedTestCase1(){
        System.out.println("테스트1");
    }

    @RepeatedTest(value = 100, name = "{displayName}, {currentRepetition} / {totalRepetitions}")
    @DisplayName("테스트 반복수행! 조금 더 디테일하게")
    void repeatedTestCase2(RepetitionInfo repetitionInfo){
        System.out.println(repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    // 매개변수가 다른 값을 테스트 해야 할 때
    @DisplayName("매개변수테스트")
    @ParameterizedTest(name="{displayName}{index} message={0}")
    @ValueSource(strings = {"다음", "스터디는", "언제로", "할까요?"})
    @EmptySource
    @NullSource
    @NullAndEmptySource
    void parameterizedTest1(String msg){
        System.out.println(msg);
    }

    /*ParameterizedTest의 경우, 여러 소스들을 지원하는데
    @ValueSource: 배열 정도로 생각하면 됨. ( 같은 자료형 )
    @NullSource:  null이 넘어갔을 때 테스트
    @EmptySource: "" String 이 넘어갔을 때
    @CsvSource:   여러타입의 인자들을 한번에 받을 수 있음
    @CsvFileSource: 이런것도 있음
    */

    @ParameterizedTest(name="{displayName}{index} message={0}")
    @CsvSource({"1, '스터디요'", "2, '12월에는 거하게 먹죠'"})
    void parameterizedTest2(int idx, String msg){
        System.out.println(idx + " " + msg);
    }

    /*
    @BeforeAll
    @AfterAll
    @BeforeEach
    @AfterEach
     */

    // 근데 여기까지는 그저 단순한 pure java 테스트고...
    // Spring Web이나 MVC 이런 것들은 테스트를 어떻게....
}