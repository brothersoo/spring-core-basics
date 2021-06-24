package brothersoo.core.spring;

import brothersoo.core.discount.DiscountPolicy;
import brothersoo.core.discount.FixDiscountPolicy;
import brothersoo.core.discount.RateDiscountPolicy;
import brothersoo.core.member.MemberRepository;
import brothersoo.core.member.MemberService;
import brothersoo.core.member.MemberServiceImpl;
import brothersoo.core.member.MemoryMemberRepository;
import brothersoo.core.order.OrderService;
import brothersoo.core.order.OrderServiceImpl;

public class AppConfig {

  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  public DiscountPolicy discountPolicy() {
    return new FixDiscountPolicy();
  }

  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }
}
