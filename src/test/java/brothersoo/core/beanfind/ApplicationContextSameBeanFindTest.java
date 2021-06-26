package brothersoo.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import brothersoo.core.member.MemberRepository;
import brothersoo.core.member.MemoryMemberRepository;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
      DuplicateBeanTypeAppConfig.class);

  @Configuration
  static class DuplicateBeanTypeAppConfig {

    @Bean
    MemberRepository memberRepository1() {
      return new MemoryMemberRepository();
    }

    @Bean
    MemberRepository memberRepository2() {
      return new MemoryMemberRepository();
    }
  }

  @Test
  @DisplayName("타입으로 검색할 때, 둘 이상의 동일한 타입의 Bean이 있을 시 오류")
  void failSearchDuplicatedBeanByType() {
    assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
  }

  @Test
  @DisplayName("둘 이상의 동일한 타입의 Bean이 있을 경우에는 이름과 타입 모두 명시")
  void searchDuplicatedBeanByNameAndType() {
    MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
    assertThat(memberRepository1)
        .isInstanceOf(MemoryMemberRepository.class);
  }

  @Test
  @DisplayName("특정 타입의 Bean 모두 검색")
  void searchAllBeanByType() {
    Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
    for (String key: beansOfType.keySet()) {
      System.out.printf("key = %s : value = %s%n", key, beansOfType.get(key));
    }
    System.out.printf("beansOfType = %s", beansOfType);
    assertThat(beansOfType.size()).isEqualTo(2);
  }
}
