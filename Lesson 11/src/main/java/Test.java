import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntSupplier;

public class Test<E> {
  private static int x;

  public static void main(String[] args) {
    Runnable r = () -> {};
    IntSupplier s = () -> 0;
  }

  private <T> void foo(T t) {
    List<? super Number> list = new ArrayList<>();
  }

  class InnerTest{

  }
}
