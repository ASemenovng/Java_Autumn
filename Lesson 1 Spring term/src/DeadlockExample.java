public class DeadlockExample {
  private final Object lock1 = new Object();
  private final Object lock2 = new Object();

  private void thread1Process() {
    synchronized (lock1) {
      System.out.println("Thread 1: Захвачен lock1");
      try {
        Thread.sleep(100); // Имитация загрузки
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Thread 1: Попытка захватить lock2");
      synchronized (lock2) {
        System.out.println("Thread 1: Захвачены lock1 и lock2");
      }
    }
  }

  private void thread2Process() {
    synchronized (lock2) {
      System.out.println("Thread 2: Захвачен lock2");
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Thread 2: Попытка захватить lock1");
      synchronized (lock1) {
        System.out.println("Thread 2: Захвачены lock2 и lock1");
      }
    }
  }

  public static void main(String[] args) {
    DeadlockExample example = new DeadlockExample();
    new Thread(example::thread1Process).start();
    new Thread(example::thread2Process).start();
  }
}

