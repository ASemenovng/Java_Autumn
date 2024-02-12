package threads;

import java.util.Random;

public class PerformanceComparisonApp {

  private static final int SIZE = 1_000_000;
  private static final int[] numbers = new int[SIZE];
  private static final Random r = new Random();

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < SIZE; i++) {
      numbers[i] = r.nextInt() * SIZE % 100;
    }

    // Однопоточное выполнение
    long startTime = System.currentTimeMillis();
    long sum = 0;
    for (int number : numbers) {
      sum += number;
    }
    long endTime = System.currentTimeMillis();

    System.out.println("Single-threaded execution time: " + (endTime - startTime) + " ms");
    System.out.println("Result: " + sum);

    // Многопоточное выполнение
    SumCalculatorThread calculator1 = new SumCalculatorThread(numbers, 0, SIZE / 4);
    SumCalculatorThread calculator2 = new SumCalculatorThread(numbers, SIZE / 4, SIZE / 2);
    SumCalculatorThread calculator3 = new SumCalculatorThread(numbers, SIZE / 2, 3 * SIZE / 4);
    SumCalculatorThread calculator4 = new SumCalculatorThread(numbers, 3 * SIZE / 4, SIZE);

    Thread thread1 = new Thread(calculator1);
    Thread thread2 = new Thread(calculator2);
    Thread thread3 = new Thread(calculator3);
    Thread thread4 = new Thread(calculator4);

    startTime = System.currentTimeMillis();
    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();

    thread1.join();
    thread2.join();
    thread3.join();
    thread4.join();
    endTime = System.currentTimeMillis();

    long totalSum =
        calculator1.getPartialSum() + calculator2.getPartialSum() + calculator3.getPartialSum()
            + calculator4.getPartialSum();
    System.out.println("Multi-threaded execution time: " + (endTime - startTime) + " ms");
    System.out.println("Result: " + totalSum);
  }

  private static class SumCalculatorThread implements Runnable {

    private final int[] numbers;
    private final int start;
    private final int end;
    private long partialSum = 0;

    public SumCalculatorThread(int[] numbers, int start, int end) {
      this.numbers = numbers;
      this.start = start;
      this.end = end;
    }

    @Override
    public void run() {
      for (int i = start; i < end; i++) {
        partialSum += numbers[i];
      }
    }

    public long getPartialSum() {
      return partialSum;
    }
  }
}