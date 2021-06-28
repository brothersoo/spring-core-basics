package brothersoo.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

  @Test
  @DisplayName("Prototype Bean scope 테스트")
  void prototypeBeanScopeTest() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        PrototypeBean.class);
    System.out.println("find PrototypeBean1");
    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    System.out.println("find PrototyeBean2");
    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

    assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
  }

  @Scope("prototype")
  static class PrototypeBean {

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init");
    }

    @PreDestroy
    public void close() {
      System.out.println("PrototypeBean.close");
    }
  }
}
