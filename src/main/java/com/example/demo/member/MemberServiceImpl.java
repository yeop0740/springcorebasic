package com.example.demo.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // @Component 어노테이션만으로는 의존성을 나타낼 방법이 없다.
    // Appconfig 에서는 명시적으로 어떠한 것을 넣을지 적어줬지만 여기에선 알 수 없으므로
    // @Autowired 를 이용하여 동일 클래스로 등록된 빈을 주입하도록 할 수 있다.
    @Autowired // ac.getBean(MemberRepository.class); 와 동일 기능을 한다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
