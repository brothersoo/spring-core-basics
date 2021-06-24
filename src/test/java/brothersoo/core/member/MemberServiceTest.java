package brothersoo.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

  MemberService memberService = new MemberServiceImpl();

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
