package brothersoo.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean{

  private String url;

  public NetworkClient() {
    System.out.printf("생성자 호출, url = %s\n", this.url);
  }

  void setUrl(String url) {
    this.url = url;
  }

  void connect() {
    System.out.printf("connect: %s\n", url);
  }

  void call(String message) {
    System.out.printf("call: %s, message = %s\n", url, message);
  }

  void disconnect() {
    System.out.printf("disconnect: %s\n", url);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    connect();
    call("초기화 연결 메세지");
  }

  @Override
  public void destroy() throws Exception {
    disconnect();
  }
}
