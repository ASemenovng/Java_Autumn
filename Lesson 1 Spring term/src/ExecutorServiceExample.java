import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {

  // Callable задача для вычисления факториала числа
  static class FactorialTask implements Callable<Long> {

    private int number;

    public FactorialTask(int number) {
      this.number = number;
    }

    @Override
    public Long call() throws Exception {
      long result = 1;
      for (int i = 2; i <= number; i++) {
        result *= i;
        Thread.sleep(100); // Имитация длительной задачи
      }
      System.out.println("Вычислен факториал для: " + number + ", результат: " + result);
      return result;
    }
  }

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(3); // Пул из 3 потоков

    List<Future<Long>> futures = new ArrayList<>();

    for (int i = 1; i <= 5; i++) {
      int number = i * 2; // Просто для разнообразия данных
      Callable<Long> task = new FactorialTask(number);
      Future<Long> future = executor.submit(task); // Запуск задачи
      futures.add(future);
    }

    // Получение и печать результатов выполнения задач
    futures.forEach(future -> {
      try {
        // Вывод результата выполнения отдельной Callable задачи
        System.out.println("Факториал рассчитан: " + future.get());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    });

    executor.shutdown(); // Важно закрывать ExecutorService, чтобы избежать утечек ресурсов
  }
}

