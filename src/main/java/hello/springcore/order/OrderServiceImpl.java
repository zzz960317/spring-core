package hello.springcore.order;

import hello.springcore.discount.DiscountPolicy;
import hello.springcore.discount.FixDiscountPolicy;
import hello.springcore.discount.RateDiscountPolicy;
import hello.springcore.member.Member;
import hello.springcore.member.MemberRepository;
import hello.springcore.member.MemoryMemberRepository;

import hello.springcore.discount.DiscountPolicy;
import hello.springcore.member.Member;
import hello.springcore.member.MemberRepository;


public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemoryMemberRepository memoryMemberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memoryMemberRepository;
        this.discountPolicy = discountPolicy;
    }



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

/*
- 회원을 메모리에서 조회하고, 정액(고정금액) 할인 정책을 지원해도 주문 서비스를 변경하지 않아도 된다.
1. 주문 생성( 회원ID, 상품명, 상품가격) -> 주문 서비스 역할(인터페이스)
2. 회원 조회 -> 회원 저장소 역할 (인터페이스) -> 메모리 회원 저장소
3. 할인 적용 -> 할인 정책 역할 (인터페이스) -> 정액할인 정책(구현), 정률할인 정책(구현)
4. 클라이언트에게 주문결과 반환
 */
