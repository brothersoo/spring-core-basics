package brothersoo.core.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Member {
  private final Long id;
  private final String name;
  private final Grade grade;
}
