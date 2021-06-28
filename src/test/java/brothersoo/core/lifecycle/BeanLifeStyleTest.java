package brothersoo.core.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class BeanLifeStyleTest {

  @Test
  @DisplayName("Bean 생명주기 테스트")
  void lifeStyleTest() {
    ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(
        LifeStyleConfig.class);
    NetworkClient networkClient = ac.getBean("networkClient", NetworkClient.class);
    ac.close();
  }

  @Configuration
  static class LifeStyleConfig {

    @Bean
    NetworkClient networkClient() {
      NetworkClient networkClient = new NetworkClient();
      networkClient.setUrl("brothersoo.github.io");
      return networkClient;
    }
  }
}
