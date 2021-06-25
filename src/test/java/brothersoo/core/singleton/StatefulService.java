package brothersoo.core.singleton;

public class StatefulService {

  private int price;

  public int getPrice() {
    return price;
  }

  public void order(int price) {
    this.price = price;
  }
}

