package invoke;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CheckAnyServiceAvailable {

  static class ServiceChecker implements Callable<String> {

    private final String serviceName;

    public ServiceChecker(String serviceName) {
      this.serviceName = serviceName;
    }

    @Override
    public String call() throws Exception {
      // имитация проверки сервиса
      return serviceName + " доступен";
    }
  }

  public static void main(String[] args) throws Exception {
    ExecutorService executor = Executors.newFixedThreadPool(3);
    List<ServiceChecker> checkTasks = new ArrayList<>();

    checkTasks.add(new ServiceChecker("Сервис A"));
    checkTasks.add(new ServiceChecker("Сервис B"));
    checkTasks.add(new ServiceChecker("Сервис C"));

    String result = executor.invokeAny(checkTasks);

    System.out.println(
        result + " - первый доступный сервис"); // Вывод результата первой успешной проверки

    executor.shutdown();
  }
}
