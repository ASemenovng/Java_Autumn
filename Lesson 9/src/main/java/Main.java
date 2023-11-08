import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntSupplier;

public class Main {



  static int x = 5;
  static int y = 5;
  public static void main(String[] args) {

    // 1 часть
    // как создать функциональный интерфейс, например Runnable
    //
    Runnable r = () -> System.out.println("Hello");
    Object o = (Runnable)(() -> System.out.println("Hello"));

    Runnable rr = args.length< 5
        ? () -> System.out.println("<5")
        : () -> System.out.println(">=5");

    Object oo = args.length< 5
        ? (Runnable)() -> System.out.println("<5")
        : (Runnable)() -> System.out.println(">=5");



    // 2 часть
    // как их использовать
    //
    run(() -> System.out.println("Hello"));
    r.run();
    System.out.println("Runnable was called\n");

    // compile error
    // var rr = () -> System.out.println("Hello");



    // 3 часть
    // value-compatible & void-compatible
    //
    IntSupplier intSupplier = () -> 10;

    IntSupplier supplier = () -> {
      System.out.println("log smth");
      throw new RuntimeException();
    };

    Runnable rrr = () -> {
      throw new RuntimeException();
    };

    IntSupplier sup = () -> {
      while (true) {}
    };



    // 4 часть
    // захват значений
    //
    List<IntSupplier> suppliersX = new ArrayList<>();
    for (int i = 0; i < 10; ++i) {
      x++;
      int xx = x;
      IntSupplier supp = () -> xx * xx;
      suppliersX.add(supp);
    }

    for (IntSupplier sp : suppliersX) {
      System.out.println(sp.getAsInt());
    }
    System.out.println("suppliersX was printed\n");


    List<IntSupplier> suppliersY = new ArrayList<>();
    for (int i = 0; i < 10; ++i) {
      y++;
      IntSupplier supp = () -> y * y;
      suppliersY.add(supp);
    }

    for (IntSupplier sp : suppliersY) {
      System.out.println(sp.getAsInt());
    }
    System.out.println("suppliersY was printed\n");


    // 5 часть
    // лямбды и ссылки на методы
    //
    Function<String, String> tr = String::trim;
    System.out.println(tr.apply("  hello  "));
    System.out.println("tr was called\n");



    Consumer<Object> refPrinter = System.out::println;
    Consumer<Object> lambdaPrinter = obj -> System.out.println(obj);
    System.setOut(null);
    // работает
    refPrinter.accept("refPrinter hello\n");
    // падает с NPE
    lambdaPrinter.accept("lambdaPrinter hello\n");

    // 6 часть
    // функциональное программирование
    //

    // пример создания функции инкремента через bind
    Function<Integer, Integer> inc = bind(Integer::sum, 1);
    System.out.println("inc: " + inc.apply(10) + "\n");


    // пример использования каррирования
    System.out.println(curry(Integer::sum).apply(10).apply(11));

    // 7 часть
    // Optional
    //
    Optional<Integer> optionalI = toInt("10");
    if (optionalI.isPresent()) {
      int i = optionalI.get();
      System.out.println("i value from optional " + i + "\n");
    }
    int integer = Optional.of("123").flatMap(Main::toInt).orElse(-1);
  }

  void increaseCarma(UserRepository repository, String name) {
    repository.findUser(name)
        .filter(User::isActive)
        .orElseThrow(IllegalArgumentException::new)
        .updateCarma(1);
  }

  static Optional<Integer> toInt(String s) {
    try {
      return Optional.of(Integer.parseInt(s));
    } catch (NumberFormatException e) {
      return Optional.empty();
    }
  }

  static <A, B, C> Function<B, C> bind(BiFunction<A, B, C> fn, A a) {
    return b -> fn.apply(a, b);
  }

  static <A, B, C> Function<A, Function<B, C>> curry(BiFunction<A, B, C> fn){
    return a -> b -> fn.apply(a, b);
  }

  static void run(Runnable r) {
    r.run();
  }
}
