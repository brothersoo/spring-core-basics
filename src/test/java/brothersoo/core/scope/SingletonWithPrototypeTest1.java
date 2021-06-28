package brothersoo.core.scope;

import static org.assertj.core.api.Assertions.assertThat;

import ch.qos.logback.core.net.server.Client;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

class SingletonWithPrototypeTest1 {

  @Test
  void prototypeFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        PrototypeBean.class);

    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

    prototypeBean1.addCount();
    prototypeBean2.addCount();
    assertThat(prototypeBean1.getCount()).isEqualTo(1);
    assertThat(prototypeBean2.getCount()).isEqualTo(1);
  }

  @Test
  @DisplayName("Singleton 안에 Prototype Bean이 있는 경우")
  void singletonClientUsePrototype() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,
        PrototypeBean.class);

    ClientBean clientBean1 = ac.getBean(ClientBean.class);
    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    assertThat(clientBean1.logic()).isEqualTo(1);
    assertThat(clientBean2.logic()).isEqualTo(2);
  }

  @Test
  @DisplayName("억지로 prototype bean을 client bean logic 내에서 getBean하여 prototype bean 개별 작동")
  void separatePrototypeBeanInClientBean() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        ClientBean2.class);

    ClientBean2 clientBean21 = ac.getBean(ClientBean2.class);
    ClientBean2 clientBean22 = ac.getBean(ClientBean2.class);

    assertThat(clientBean21.logic()).isEqualTo(1);
    assertThat(clientBean22.logic()).isEqualTo(1);
  }

  @Test
  @DisplayName("ObjectProvider를 사용한 Dependency Lookup")
  void dependencyLookupViaObjectProvider() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        ClientBeanWithObjectProvider.class, PrototypeBean.class);
    ClientBeanWithObjectProvider clientBean1 = ac.getBean(ClientBeanWithObjectProvider.class);
    ClientBeanWithObjectProvider clientBean2 = ac.getBean(ClientBeanWithObjectProvider.class);

    assertThat(clientBean1.logic()).isEqualTo(1);
    assertThat(clientBean2.logic()).isEqualTo(1);
  }

  @Test
  @DisplayName("JSR Provider를 사용한 Dependency Lookup")
  void dependencyLookupViaJSRProvider() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        ClientBeanWithJSRProvider.class, PrototypeBean.class);
    ClientBeanWithJSRProvider clientBean1 = ac.getBean(ClientBeanWithJSRProvider.class);
    ClientBeanWithJSRProvider clientBean2 = ac.getBean(ClientBeanWithJSRProvider.class);

    assertThat(clientBean1.logic()).isEqualTo(1);
    assertThat(clientBean2.logic()).isEqualTo(1);
  }

  @Scope("singleton")
  static class ClientBean {

    private final PrototypeBean prototypeBean;

    @Autowired
    public ClientBean(PrototypeBean prototypeBean) {
      this.prototypeBean = prototypeBean;
    }

    public int logic() {
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }

  @Scope("singleton")
  static class ClientBean2 {

    public int logic() {
      AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
          PrototypeBean.class);
      PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }

  @Scope("singleton")
  static class ClientBeanWithObjectProvider {

    public final ObjectProvider<PrototypeBean> prototypeBeanProvider;

    @Autowired
    public ClientBeanWithObjectProvider(ObjectProvider<PrototypeBean> prototypeBeanProvider) {
      this.prototypeBeanProvider = prototypeBeanProvider;
    }

    public int logic() {
      PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }

  @Scope("singleton")
  static class ClientBeanWithJSRProvider {

    private final Provider<PrototypeBean> prototypeBeanProvider;

    @Autowired
    public ClientBeanWithJSRProvider(Provider<PrototypeBean> prototypeBeanProvider) {
      this.prototypeBeanProvider = prototypeBeanProvider;
    }

    int logic() {
      PrototypeBean prototypeBean = prototypeBeanProvider.get();
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }

  }

  @Scope("prototype")
  static class PrototypeBean {

    private int count = 0;

    int getCount() {
      return count;
    }

    void addCount() {
      count++;
    }

    @PostConstruct
    void init() {
      System.out.println("PrototypeBean.init");
    }

    @PreDestroy
    void close() {
      System.out.println("PrototypeBean.destroy");
    }
  }
}
