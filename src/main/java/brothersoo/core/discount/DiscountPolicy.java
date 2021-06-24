package brothersoo.core.discount;

import brothersoo.core.member.Member;

public interface DiscountPolicy {

  int getDiscountAmount(Member member);
}
