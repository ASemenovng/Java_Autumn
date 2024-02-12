package atomic;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

class AtomicCounter {

  private AtomicInteger count = new AtomicInteger(0);

  public void increment() {
    count.incrementAndGet();
  }

  public int getCount() {
    return count.get();
  }

  public static void main(String[] args) throws InterruptedException {
    final AtomicCounter counter = new AtomicCounter();

    // Создаем 1000 потоков, каждый увеличивает счетчик на 1
    List<Thread> threads = Stream.generate(() -> new Thread(counter::increment))
        .limit(1000).peek(Thread::start)
        .toList();


    for (Thread t : threads) {
      t.join();
    }

    System.out.println("Expected: 1000, Actual: " + counter.getCount());
  }
}
