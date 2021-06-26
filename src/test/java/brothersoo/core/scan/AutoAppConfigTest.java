package brothersoo.core.scan;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.FilterType.ANNOTATION;

import brothersoo.core.member.MemberService;
import brothersoo.core.AutoAppConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

class AutoAppConfigTest {

  @Test
  @DisplayName("컴포넌트 스캔 Configuration 확인")
  void autoComponentScanConfigTest() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        AutoAppConfig.class);
    MemberService memberService = ac.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberService.class);
  }

  @Test
  @DisplayName("컴포넌트 스캔 필터링")
  void filteringComponentScan() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        TestAppConfig.class);

    BeanA beanA = ac.getBean(BeanA.class);
    assertThat(beanA).isInstanceOf(beanA.getClass());
    Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean(BeanB.class));
  }

  @Configuration
  @ComponentScan(
      includeFilters = @Filter(type = ANNOTATION, classes = MyIncludeComponent.class),
      excludeFilters = @Filter(type = ANNOTATION, classes = MyExcludeComponent.class)
  )
  static class TestAppConfig {
  }
}
