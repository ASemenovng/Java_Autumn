package completable_future;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {

  public static void main(String[] args) {
    // Создание CompletableFuture для асинхронного вычисления квадрата числа
    CompletableFuture<Integer> futureSquare = CompletableFuture.supplyAsync(() -> {
      try {
        // Имитация длительной операции
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        System.err.println("Ошибка во время вычисления.");
      }
      return 5 * 5; // Возврат квадрата числа 5
    });

    // Используем результат futureSquare для дальнейшей асинхронной операции
    CompletableFuture<Integer> futureResult = futureSquare.thenApply(square -> {
      System.out.println("Квадрат числа: " + square);
      return square * 2; // Удваиваем результат
    });

    // Получение и печать окончательного результата
    futureResult.thenAccept(result -> System.out.println("Результат после удвоения: " + result))
        .exceptionally(ex -> { // Обработка исключений
          System.err.println("Произошла ошибка: " + ex.getMessage());
          return null;
        });

    try {
      Thread.sleep(2000); // Главный поток ждет завершения всех асинхронных задач
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
