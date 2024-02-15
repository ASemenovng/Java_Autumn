package singleton;

public class YTSingleton {
  int x = 1;

  static YTSingleton getInstance() {
    return Holder.INSTANCE;
  }

  private static class Holder {
    static final YTSingleton INSTANCE = new YTSingleton();
  }

}
