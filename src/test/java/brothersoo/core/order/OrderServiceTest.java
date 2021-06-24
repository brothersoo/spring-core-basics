package brothersoo.core.order;

import brothersoo.core.member.Grade;
import brothersoo.core.member.Member;
import brothersoo.core.member.MemberService;
import brothersoo.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

  MemberService memberService = new MemberServiceImpl();
  OrderService orderService = new OrderServiceImpl();

  @Test
  void 주문생성() {
    //given
    Member member = new Member(1L, "Brothersoo", Grade.VIP);
    memberService.join(member);
    //when
    Order order = orderService.createOrder(member.getId(), "Mac book pro M2", 2500000);
    //then
    Assertions.assertThat(order.getDiscountedPrice()).isEqualTo(2500000 - 1000);
  }
}
