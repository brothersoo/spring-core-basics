package brothersoo.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import brothersoo.core.member.MemberRepository;
import brothersoo.core.member.MemberService;
import brothersoo.core.member.MemberServiceImpl;
import brothersoo.core.order.OrderServiceImpl;
import brothersoo.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

  @Test
  @DisplayName("순수 자바 DI를 사용하였을 때 동일한 객체의 Bean을 반복 생성하는지 확인")
  void pureJavaDIBeanTest() {
    AppConfig appConfig = new AppConfig();
    MemberService memberService1 = appConfig.memberService();
    MemberService memberService2 = appConfig.memberService();

    assertThat(memberService1).isNotEqualTo(memberService2);
  }

  @Test
  @DisplayName("싱글톤 패턴을 사용한 객체 생성")
  void singletonPatternTest() {
    SingletonService singletonService1 = SingletonService.getInstance();
    SingletonService singletonService2 = SingletonService.getInstance();

    assertThat(singletonService1).isSameAs(singletonService2);
  }

  @Test
  @DisplayName("Spring 내장 싱글톤 Bean 객체 생성")
  void springSingletonPatternTest() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberService memberService1 = ac.getBean("memberService", MemberService.class);
    MemberService memberService2 = ac.getBean("memberService", MemberService.class);

    assertThat(memberService1).isSameAs(memberService2);
  }

  @Test
  @DisplayName("Spring configuration이 singleton을 보장하는지 확인")
  void assertSpringConfigurationGuaranteesSingleton() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

    assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
    assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
  }
}
