package threads;

public class CounterApp {

  private int counter = 0;

  public static void main(String[] args) throws InterruptedException {
    CounterApp app = new CounterApp();
    Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        app.increaseCounter();
      }
    });

    Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        app.increaseCounter();
      }
    });

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

    System.out.println("Final counter value: " + app.counter);
  }

  // метод не синхронизирован
  public void increaseCounter() {
    ++counter;
  }
}