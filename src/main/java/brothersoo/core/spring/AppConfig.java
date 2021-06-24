package brothersoo.core.spring;

import brothersoo.core.discount.RateDiscountPolicy;
import brothersoo.core.member.MemberService;
import brothersoo.core.member.MemberServiceImpl;
import brothersoo.core.member.MemoryMemberRepository;
import brothersoo.core.order.OrderService;
import brothersoo.core.order.OrderServiceImpl;

public class AppConfig {

  public MemberService memberService() {
    return new MemberServiceImpl(new MemoryMemberRepository());
  }

  public OrderService orderService() {
    return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
  }
}
