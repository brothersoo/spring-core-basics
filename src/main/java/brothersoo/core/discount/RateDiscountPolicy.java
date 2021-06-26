package brothersoo.core.discount;

import brothersoo.core.member.Grade;
import brothersoo.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

  private final static int discountRate = 10;


  @Override
  public int getDiscountAmount(Member member, int itemPrice) {
    if (member.getGrade() == Grade.VIP) {
      return (int) (itemPrice * discountRate * 0.01);
    } else {
      return 0;
    }
  }
}
