package problems;

import java.util.stream.IntStream;

public class Test {

  static class DumbCounter {
    int count;

    void increment() {
      ++count; // на самом деле инкремент - не атомарная операция
    }
  }

  public static void main(String[] args) {
    DumbCounter c1 = new DumbCounter();
    IntStream.range(0, 1_000_000).forEach(i -> c1.increment());

    DumbCounter c2 = new DumbCounter();
    IntStream.range(0, 1_000_000).parallel().forEach(i -> c2.increment());

    System.out.println(c1.count + " - " + c2.count);
  }

}
