package atomic;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

// неблокирующий кэш
class AtomicCache<T, R> {

  private class CacheEntry {

    final T key;
    final R value;

    CacheEntry(T key, R value) {
      this.key = key;
      this.value = value;
    }
  }

  private AtomicReference<CacheEntry> cache = new AtomicReference<>();

  public R computeIfAbsent(T key, Function<T, R> mappingFunction) {
    CacheEntry currentEntry = cache.get();
    if (currentEntry != null && currentEntry.key.equals(key)) {
      return currentEntry.value;
    }

    R newValue = mappingFunction.apply(key);
    CacheEntry newEntry = new CacheEntry(key, newValue);

    // Попытка обновить кэш, если текущее значение соответствует ожидаемому (null или старое значение)
    cache.compareAndSet(currentEntry, newEntry);

    return newValue;
  }

  public static void main(String[] args) {
    AtomicCache<Integer, String> cache = new AtomicCache<>();

    // Потоки, обновляющие кэш
    for (int i = 0; i < 10; i++) {
      int key = i % 5;
      new Thread(() -> {
        String value = cache.computeIfAbsent(key, k -> "Value: " + k);
        System.out.println(Thread.currentThread().getName() + " got " + value);
      }).start();
    }
  }
}

