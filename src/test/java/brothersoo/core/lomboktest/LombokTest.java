package brothersoo.core.lomboktest;

import static org.assertj.core.api.Assertions.assertThat;

import brothersoo.core.scan.MyExcludeComponent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

public class LombokTest {

  @Test
  @DisplayName("lombok 테스트")
  void lombokGetterSetterTest() {

    Member member = new Member();
    member.setId(1L);
    member.setName("Brothersoo");
    assertThat(member.getId()).isEqualTo(1L);
    assertThat(member.getName()).isEqualTo("Brothersoo");
  }

  @Getter
  @Setter
  class Member {

    private Long id;
    private String name;
  }
}
