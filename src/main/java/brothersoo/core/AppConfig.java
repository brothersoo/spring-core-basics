package brothersoo.core;

import brothersoo.core.discount.DiscountPolicy;
import brothersoo.core.discount.FixDiscountPolicy;
import brothersoo.core.discount.RateDiscountPolicy;
import brothersoo.core.member.MemberRepository;
import brothersoo.core.member.MemberService;
import brothersoo.core.member.MemberServiceImpl;
import brothersoo.core.member.MemoryMemberRepository;
import brothersoo.core.order.OrderService;
import brothersoo.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    return new FixDiscountPolicy();
  }

  @Bean
  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }
}
