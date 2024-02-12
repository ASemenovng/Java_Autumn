import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class Doer {

  private volatile int flag = 0;

  private static final AtomicIntegerFieldUpdater<Doer> FLAG_UPDATER =
      AtomicIntegerFieldUpdater.newUpdater(Doer.class, "flag");

  public void doOnce(Runnable action) {
    if (FLAG_UPDATER.compareAndSet(this, 0, 1)) {
      action.run();
    }
  }

}
