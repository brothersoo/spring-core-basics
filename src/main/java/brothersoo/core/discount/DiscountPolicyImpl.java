package brothersoo.core.discount;

import brothersoo.core.member.Grade;
import brothersoo.core.member.Member;

public class DiscountPolicyImpl implements DiscountPolicy{

  private final static int fixedDiscountAmount = 1000;

  @Override
  public int getDiscountAmount(Member member) {
    if (member.getGrade() == Grade.VIP) {
      return fixedDiscountAmount;
    } else {
      return 0;
    }
  }
}
