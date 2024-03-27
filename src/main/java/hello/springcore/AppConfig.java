package hello.springcore;

import hello.springcore.discount.DiscountPolicy;
import hello.springcore.discount.FixDiscountPolicy;
import hello.springcore.discount.RateDiscountPolicy;
import hello.springcore.member.MemberRepository;
import hello.springcore.member.MemberService;
import hello.springcore.member.MemberServiceImpl;
import hello.springcore.member.MemoryMemberRepository;
import hello.springcore.order.OrderService;
import hello.springcore.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(),
                discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository () {
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy () {
        return new FixDiscountPolicy();
//        return new RateDiscountPolicy(); // 할인정책을 변경
    }

}

/*
- AppConfig에 설정을 구성한다는 뜻의 @Configuration
- 각 method에 @Bean을 붙여주면 스프링 컨테이너에 스프링 빈으로 등록한다는 의미.
애플리케이션의 전체 동작 방식을 구성(Config)하기 위해, 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정 클래스
AppConfig 는 생성한 객체 인스턴의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
- 각 클래스에 생성자가 없어 컴파일 오류발생하므로 생성자를 만들어줌.
- MemberServiceImpl 은 이제부터 의존관계에 대한 고민은 외부 클래스(AppConfig)에 맡기고 실행에만 집중하면 된다.
- appConfig 객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달한다.
- 클라이언트인 memberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다하여 DI(Deqendency Injection) 의존관계 주입 또는 의존성 주입이라 한다.
- AppConfig 를 보면 역할(인터페이스)과 구현 클래스가 한 눈에 들어온다. 그래서 앱 전체구성이 어떻게 되어 있는지 빠르게 파악할 수 있다.
 */