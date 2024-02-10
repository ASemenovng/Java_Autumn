package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SumCalculatorApp {

  private static final Random r = new Random();
  private static final int SIZE = 10_000;

  public static void main(String[] args) throws InterruptedException {
    List<Integer> numbers = new ArrayList<>(SIZE);
    for (int i = 0; i < SIZE; i++) {
      numbers.add(r.nextInt() * SIZE);
    }

    // Создание потоков
    SumCalculatorThread calculator1 = new SumCalculatorThread(numbers.subList(0, SIZE / 2));
    SumCalculatorThread calculator2 = new SumCalculatorThread(numbers.subList(SIZE / 2, SIZE));

    Thread thread1 = new Thread(calculator1);
    Thread thread2 = new Thread(calculator2);

    thread1.start();
    thread2.start();

    thread1.join();
    thread2.join();

    int totalSum = calculator1.getPartialSum() + calculator2.getPartialSum();
    System.out.println("Total sum: " + totalSum);
  }

  private static class SumCalculatorThread implements Runnable {
    private final List<Integer> numbers;
    private int partialSum = 0;

    public SumCalculatorThread(List<Integer> numbers) {
      this.numbers = numbers;
    }

    @Override
    public void run() {
      for (Integer number : numbers) {
        partialSum += number;
      }
    }

    public int getPartialSum() {
      return partialSum;
    }
  }
}
