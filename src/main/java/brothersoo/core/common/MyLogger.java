package brothersoo.core.common;

import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request")
public class MyLogger {

  private String uuid;
  private String requestURL;

  @PostConstruct
  void setUUID() {
    uuid = UUID.randomUUID().toString();
    System.out.printf("[%s] Request Scope Bean Created%n", uuid);
  }

  public void setRequestURL(String url) {
    requestURL = url;
  }

  public void log(String message) {
    System.out.printf("[%s] [%s] : %s%n", uuid, requestURL, message);
  }

  @PreDestroy
  void close() {
    System.out.printf("[%s] Request Scope Bean Destroyed%n", uuid);
  }
}
