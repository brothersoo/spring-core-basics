package brothersoo.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import brothersoo.core.member.MemberService;
import brothersoo.core.member.MemberServiceImpl;
import brothersoo.core.spring.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextBasicFindTest {


  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("Bean 이름과 타입으로 조회")
  void searchBeanByNameAndType() {
    MemberService memberServiceBean = ac.getBean("memberService", MemberService.class);
    assertThat(memberServiceBean).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("Bean 타입으로만 조회")
  void searchBeanByType() {
    MemberService memberServiceBean = ac.getBean(MemberService.class);
    assertThat(memberServiceBean).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("Bean 이름으로만 검색")
  void searchBeanByName() {
    Object memberServiceBean = ac.getBean("memberService");
    assertThat(memberServiceBean).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("없는 Bean 이름 조회시 실패")
  void failSearchBeanByUnknown() {
    assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("undefinedBeanName"));
  }
}
