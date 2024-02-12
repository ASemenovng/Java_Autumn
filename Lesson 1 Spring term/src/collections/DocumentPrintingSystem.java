package collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DocumentPrintingSystem {
  public static void main(String[] args) {
    final BlockingQueue<String> printerQueue = new ArrayBlockingQueue<>(10);

    // Поток для печати документов
    Runnable printJob = () -> {
      try {
        while (!Thread.currentThread().isInterrupted()) {
          String document = printerQueue.take(); // Блокируется, пока не появится документ в очереди
          System.out.println("Печатается документ: " + document);
          Thread.sleep(1000); // Имитация времени, необходимого для печати
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    };

    // Создаем пул потоков для добавления документов в очередь
    ExecutorService executorService = Executors.newFixedThreadPool(4);

    // Запускаем поток принтера
    Thread printerThread = new Thread(printJob);
    printerThread.start();

    // Имитация работы нескольких пользователей, отправляющих документы на печать
    executorService.execute(() -> printerQueue.offer("Документ от пользователя 1"));
    executorService.execute(() -> printerQueue.offer("Документ от пользователя 2"));
    executorService.execute(() -> printerQueue.offer("Документ от пользователя 3"));
    executorService.execute(() -> printerQueue.offer("Документ от пользователя 4"));

    executorService.shutdown(); // Завершаем потоки добавления документов после выполнения задач

    // Добавляем ожидание для имитации работы системы перед её завершением
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    printerThread.interrupt(); // Принудительное завершение потока принтера
  }
}

