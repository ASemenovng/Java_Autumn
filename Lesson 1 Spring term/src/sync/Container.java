package sync;

import java.util.ArrayList;
import java.util.List;

public class Container {

  private static final List<String> list = new ArrayList<>();

  synchronized void addEntry(String s) {
    list.add(s);
  }

  public static void main(String[] args) throws InterruptedException {
    Container container = new Container();
    Runnable r = () -> {
      // Container container = new Container();
      for (int i = 0; i < 100_000; ++i) {
        container.addEntry("smth");
      }
    };

    List<Thread> threads = new ArrayList<>();
    for (int i = 10; i > 0; --i) {
      Thread t = new Thread(r);
      t.start();
      threads.add(t);
    }

    for (Thread t : threads) {
      t.join();
    }

    System.out.println(list.size());
  }

}
