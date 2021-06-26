package brothersoo.core.order;

import brothersoo.core.member.Grade;
import brothersoo.core.member.Member;
import brothersoo.core.member.MemberService;
import brothersoo.core.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

  public static void main(String[] args) {
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
    OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

    Long memberId = 1L;
    Member member = new Member(1L, "Brothersoo", Grade.VIP);
    memberService.join(member);

    String itemName = "mac book pro";
    int itemPrice = 2500000;
    Order order = orderService.createOrder(memberId, itemName, itemPrice);

    System.out.println("order = " + order);
  }
}
