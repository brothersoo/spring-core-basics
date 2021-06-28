package brothersoo.core.web;

import brothersoo.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

  private final ObjectProvider<MyLogger> myLoggerProvider;

  public void logic(String id) {
    MyLogger myLogger = myLoggerProvider.getObject();
    myLogger.log(String.format("id = %s", id));
  }
}
