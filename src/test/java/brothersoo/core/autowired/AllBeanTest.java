package brothersoo.core.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import brothersoo.core.AutoAppConfig;
import brothersoo.core.discount.DiscountPolicy;
import brothersoo.core.member.Grade;
import brothersoo.core.member.Member;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.webservices.client.WebServiceClientTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {

  @Test
  void findAllBean() {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        AutoAppConfig.class, DiscountService.class);

    DiscountService discountService = ac.getBean(DiscountService.class);
    Member member = new Member(1L, "brothersoo", Grade.VIP);
    assertThat(discountService.discount(member, 10000, "fixDiscountPolicy")).isEqualTo(1000);
    assertThat(discountService.discount(member, 20000, "rateDiscountPolicy")).isEqualTo(2000);
  }

  @RequiredArgsConstructor
  static class DiscountService {

    private final Map<String, DiscountPolicy> discountPolicyMap;

    int discount(Member member, int price, String discountPolicyName) {
      DiscountPolicy discountPolicy = discountPolicyMap.get(discountPolicyName);
      return discountPolicy.getDiscountAmount(member, price);
    }
  }

}
