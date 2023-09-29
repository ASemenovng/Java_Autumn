package refs.methods;

public class SimpleGen implements ISimpleGen {

  int val;

  @Override
  public int getNumber() {
    return val++;
  }

  public static int getRandomNumber() {
    return (int) (Math.random() * 10);
  }
}
