import static java.util.Arrays.asList;

import java.util.ArrayDeque;
import java.util.Queue;
import threads.CalcSquare;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    // task 1
    CalcSquare t1 = new CalcSquare(2);
    CalcSquare t2 = new CalcSquare(3);

    t1.start();
    t2.start();

    t1.join();
    // t2.join();

    // что если убрать .join()?
    System.out.println("task 1");
    System.out.printf("%d - %d", t1.getRes(), t2.getRes());


    // task 2 - dead lock
    System.out.println("task 1");
    Queue<String> in = new ArrayDeque<>(asList("foo", "bar", "bazz"));
    Queue<String> out = new ArrayDeque<>(asList("foo", "bar", "bazz"));

    Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 10_000; ++i) {
        System.out.println("thread1: " + i);
        transfer(in, out);
      }
    });

    Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 10_000; ++i) {
        System.out.println("thread2: " + i);
        transfer(out, in);
      }
    });

    System.out.println("\nstarted");
    thread1.start(); thread2.start();
    thread1.join(); thread2.join();
    System.out.println("finished");
  }

  static void transfer(Queue<String> in, Queue<String> out) {
    synchronized (in) {
      synchronized (out) {
        String res = in.poll();
        if (res != null) {
          out.add(res);
        }
      }
    }
  }
}