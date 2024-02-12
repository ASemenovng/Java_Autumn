package completable_future;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HotelSearchAggregator {

  private static final Random r = new Random();

  // Эмуляция запросов к внешним сервисам предложений отелей
  private static CompletableFuture<List<String>> searchOffers(String service) {
    return CompletableFuture.supplyAsync(() -> {
      try {
        // Имитация задержки ответа от сервиса
        long delay = r.nextLong(3) * 2000 + 100;
        Thread.sleep(delay);

        // Возвращаем результат в виде списка предложений
        return Arrays.asList(service + " offer 1", service + " offer 2");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
  }

  public static void main(String[] args) {
    List<String> services = Arrays.asList("Service A", "Service B", "Service C");

    ExecutorService executor = Executors.newFixedThreadPool(3);

    // Инициируем поиск предложений параллельно для каждого сервиса
    List<CompletableFuture<List<String>>> futureOffers = services.stream()
        .map(service -> searchOffers(service).exceptionally(ex -> {
          // Обработка ошибок для каждого сервиса отдельно
          System.out.println("Ошибка при запросе к " + service + ": " + ex.getMessage());
          return List.of(service + " не доступен");
        }))
        .toList();

    // Объединяем результаты всех CompletableFuture в один и ожидаем их выполнения
    CompletableFuture<Void> allFutures = CompletableFuture.allOf(
        futureOffers.toArray(new CompletableFuture[0])
    ).thenRun(() -> {
      futureOffers.forEach(future -> {
        try {
          List<String> offers = future.get();
          offers.forEach(System.out::println);
        } catch (Exception e) {
          System.out.println("Ошибка при получении предложений: " + e.getMessage());
        }
      });
    });

    // Ожидаем завершения всех асинхронных операций
    allFutures.join();
    executor.shutdown();
  }
}
