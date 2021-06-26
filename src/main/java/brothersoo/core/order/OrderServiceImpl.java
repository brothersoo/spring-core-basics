package brothersoo.core.order;

import brothersoo.core.discount.DiscountPolicy;
import brothersoo.core.member.Member;
import brothersoo.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;

  @Autowired
  public OrderServiceImpl(MemberRepository memberRepository,
      DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountAmount = discountPolicy.getDiscountAmount(member, 10000);

    return new Order(memberId, itemName, itemPrice, discountAmount);
  }

  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
