package collections;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StockPrices {
  private final ConcurrentSkipListMap<String, Double> stockPrices = new ConcurrentSkipListMap<>();

  public void updatePrice(String stockSymbol, Double price) {
    stockPrices.put(stockSymbol, price);
  }

  public Double getPrice(String stockSymbol) {
    return stockPrices.get(stockSymbol);
  }

  public static void main(String[] args) {
    StockPrices market = new StockPrices();

    // Имитация потоков, обновляющих цены акций
    ExecutorService executor = Executors.newFixedThreadPool(4);

    executor.submit(() -> market.updatePrice("AAPL", 175.65));
    executor.submit(() -> market.updatePrice("GOOG", 2725.12));
    executor.submit(() -> market.updatePrice("FB", 229.42));

    // Имитация чтения цен акций в параллельном потоке
    executor.submit(() -> System.out.println("Цена AAPL: " + market.getPrice("AAPL")));
    executor.submit(() -> System.out.println("Цена FB: " + market.getPrice("FB")));

    // Добавляем задержку для демонстрации работы
    try {
      Thread.sleep(100); // Добавим задержку для наглядности результатов
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      executor.shutdown();
    }

    // Демонстрация сортированного порядка
    market.stockPrices.forEach((symbol, price) -> System.out.println(symbol + ": " + price));
  }
}

