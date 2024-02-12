package singleton;

public class Singleton {

  private static volatile Singleton INSTANCE;

  int x = 1;

  static Singleton getInstance() {
    if (INSTANCE == null) {
      synchronized (Singleton.class) {
        if (INSTANCE == null) {
          INSTANCE = new Singleton();
        }
      }
    }
    return INSTANCE;
  }

  private Singleton() {
  }
}
