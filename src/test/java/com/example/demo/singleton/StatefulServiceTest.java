package com.example.demo.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    @DisplayName("상태 유지 싱글톤 사용시 발생하는 문제")
    void statefuleServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // Thread1: 사용자 A 주문
//        statefulService1.order("userA", 10000);
        int userAPrice = statefulService1.order("userA", 10000);
        // Thread2: 사용자 B 주문
//        statefulService2.order("userB", 20000);
        int userBPrice = statefulService2.order("userB", 20000);

        // Thread1: 사용자 A 주문 금액 조회
//        int price = statefulService1.getPrice();
        // Thread1: 사용자 A 는 10000원을 기대했지만, 기대와 다르게 20000원 출력
//        System.out.println("price = " + price);
        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);

        Assertions.assertThat(userAPrice).isNotEqualTo(userBPrice);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}