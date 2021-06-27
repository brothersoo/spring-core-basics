package brothersoo.core.discount;

import brothersoo.core.member.Grade;
import brothersoo.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FixDiscountPolicy implements DiscountPolicy{

  private final static int fixedDiscountAmount = 1000;

  @Override
  public int getDiscountAmount(Member member, int itemPrice) {
    if (member.getGrade() == Grade.VIP) {
      return fixedDiscountAmount;
    } else {
      return 0;
    }
  }
}
