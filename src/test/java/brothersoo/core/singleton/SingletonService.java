package brothersoo.core.singleton;

public class SingletonService {

  private static SingletonService singletonService = new SingletonService();

  private SingletonService() {}

  public static SingletonService getInstance() {
    return singletonService;
  }
}
