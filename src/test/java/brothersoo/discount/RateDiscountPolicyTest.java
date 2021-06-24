package brothersoo.discount;

import brothersoo.core.discount.DiscountPolicy;
import brothersoo.core.discount.RateDiscountPolicy;
import brothersoo.core.member.Grade;
import brothersoo.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateDiscountPolicyTest {

  private DiscountPolicy discountPolicy = new RateDiscountPolicy();

  @Test
  @DisplayName("VIP 회원 할인율 확인")
  void VIP_할인율_확인() {
    Member member = new Member(1L, "Brothersoo", Grade.VIP);

    int discountAmount = discountPolicy.getDiscountAmount(member, 10000);
    Assertions.assertThat(discountAmount).isEqualTo((int) (10000 * 0.1));
  }

  @Test
  @DisplayName("BASIC 회원 할인율 확인")
  void BASIC_할인율_확인() {
    Member member = new Member(2L, "moistybro", Grade.BASIC);
    int discountAmount = discountPolicy.getDiscountAmount(member, 10000);
    Assertions.assertThat(discountAmount).isZero();
  }
}
