package brothersoo.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulServiceTest {

  @Test
  @DisplayName("Stateful한 Bean 테스트")
  void statefulServiceBeanTest() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        ConfigTest.class);
    StatefulService statefulTest1 = ac.getBean("statefulTest", StatefulService.class);
    StatefulService statefulTest2 = ac.getBean("statefulTest", StatefulService.class);

    statefulTest1.order(10000);
    statefulTest2.order(20000);

    assertThat(statefulTest1.getPrice()).isEqualTo(statefulTest2.getPrice());
  }

  @Test
  @DisplayName("Stateless 않도록 수정한 Bean 테스트")
  void statelessServiceBeanTest() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        ConfigTest.class);
    StatelessService statelessService1 = ac.getBean("statelessService", StatelessService.class);
    StatelessService statelessService2 = ac.getBean("statelessService", StatelessService.class);

    assertThat(statelessService1.order(10000)).isNotEqualTo(statelessService2.order(20000));
  }

  @Configuration
  static class ConfigTest {

    @Bean
    StatefulService statefulTest() {
      return new StatefulService();
    }

    @Bean
    StatelessService statelessService() {
      return new StatelessService();
    }
  }
}
