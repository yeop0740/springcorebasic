package com.example.demo.order;

import com.example.demo.discount.RateDiscountPolicy;
import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import com.example.demo.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
//        OrderServiceImpl orderService = new OrderServiceImpl();
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new RateDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        // -> java.lang.NullPointerException
        // orderService에 주입해줘야 하는 memberRepository를 참조하기 때문임.
        // 수정자 메서드에 autowired 하면 스프링이 관리할 때에는 자동으로 주입을 해주지만
        // 테스트를 수동으로 하는 입장에선 orderService 만 생성하고 주입을 하지 않은 것이므로
        // 해당 메서드를 사용하기 위해서는 setter 로 두가지 객체를 만들어서 수동으로 주입한 뒤 테스트를 진행해야 익셉션이 터지지 않는다.
        // 내가 해당 객체를 설계하거나 잘 들여다 보지 않은 입장이라면 수정자에 autowirted 로 인해 테스트를 진행할 때 직접 주입해야한다는 것을 알기 힘들다.
        // 해서 애초에 테스트 할 객체를 생성할 때부터 의존성이 있는 것은 생성되지 않도록 제한해버리면 테스트 할 때 빼먹지 않고 주입할 수 있기 때문에
        // 생성자에 autowired를 사용하는 것을 강하게 주장한다.
    }
}