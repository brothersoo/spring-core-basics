package brothersoo.core.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonTest {

  @Test
  @DisplayName("싱글톤 Bean scope 테스트")
  void singletonBeanScopeTest() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        SingletonBean.class);

    SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
    SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
    Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

    ac.close();
  }

  @Scope("singleton")
  static class SingletonBean {

    @PostConstruct
    public void init() {
      System.out.println("SingletonBean.init");
    }

    @PreDestroy
    public void close() {
      System.out.println("SingletonBean.close");
    }
  }
}
