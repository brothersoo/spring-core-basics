package brothersoo.core.member;

import brothersoo.core.spring.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

  MemberService memberService;

  @BeforeEach
  void beforeEach() {
    AppConfig appConfig = new AppConfig();
    this.memberService = appConfig.memberService();
  }

  @Test
  void 회원가입(){
    //given
    Member member = new Member(1L, "brothersoo", Grade.VIP);

    //when
    memberService.join(member);

    //then
    Assertions.assertThat(member).isEqualTo(memberService.findMember(1L));
  }
}
