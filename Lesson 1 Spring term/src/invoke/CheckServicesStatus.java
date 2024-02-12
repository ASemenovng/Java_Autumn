package invoke;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CheckServicesStatus {

  static class ServiceChecker implements Callable<String> {
    private final String serviceName;

    public ServiceChecker(String serviceName) {
      this.serviceName = serviceName;
    }

    @Override
    public String call() {
      // имитация проверки сервиса
      return serviceName + " доступен";
    }
  }

  public static void main(String[] args) throws Exception {
    try (ExecutorService executor = Executors.newFixedThreadPool(3)) {
      List<ServiceChecker> checkTasks = new ArrayList<>();

      checkTasks.add(new ServiceChecker("Сервис A"));
      checkTasks.add(new ServiceChecker("Сервис B"));
      checkTasks.add(new ServiceChecker("Сервис C"));

      List<Future<String>> results = executor.invokeAll(checkTasks);

      for (Future<String> result : results) {
        System.out.println(result.get()); // Вывод результатов всех проверок
      }
      executor.shutdown();
    }
  }
}

