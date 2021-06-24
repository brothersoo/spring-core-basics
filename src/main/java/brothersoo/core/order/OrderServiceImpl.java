package brothersoo.core.order;

import brothersoo.core.discount.DiscountPolicy;
import brothersoo.core.discount.FixDiscountPolicy;
import brothersoo.core.member.Member;
import brothersoo.core.member.MemberRepository;
import brothersoo.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

  private final MemberRepository memberRepository = new MemoryMemberRepository();
  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountAmount = discountPolicy.getDiscountAmount(member, 10000);

    return new Order(memberId, itemName, itemPrice, discountAmount);
  }
}
