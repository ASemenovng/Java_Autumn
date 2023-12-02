package org.example.in_out;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

  public static final String OUTPUT_PATH = "./src/main/java/org/example/in_out/";

  static AtomicInteger quota = new AtomicInteger(100);
  public static void main(String[] args) throws IOException {
    System.out.println(getQuota(10));
    System.out.println(getQuota(1000));
  }

  public static Optional<Integer> getQuota(int size) {
    if (size < quota.get()) {
      return Optional.of(quota.addAndGet(-size));
    }
    return Optional.empty();
  }

}
