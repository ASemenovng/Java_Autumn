import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class DoerTest {

  static final int THREADS = Runtime.getRuntime().availableProcessors();

  public static void main(String[] args) throws InterruptedException {
    Doer doer = new Doer();
    CountDownLatch latch = new CountDownLatch(1);
    AtomicInteger atomic = new AtomicInteger();
    Runnable r = () -> {
      try {
        latch.await();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      doer.doOnce(atomic::incrementAndGet);
    };

    List<Thread> threads = Stream.generate(() -> new Thread(r))
        .limit(THREADS).peek(Thread::start)
        .toList();

    latch.countDown();

    for (Thread t : threads) {
      t.join();
    }

    if (atomic.get() != 1) {
      System.err.println("Oops");
    }
  }

}
