package singleton;

public class Singleton {

  private static final Singleton INSTANCE = new Singleton();

  int x = 1;

  static Singleton getInstance() {
    return INSTANCE;
  }

  private Singleton() {
  }
}
