package org.example.tmp;

import static java.util.Comparator.reverseOrder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class Test {

  public static final String IO_PATH = "./src/main/java/org/example/tmp";

  public static void main(String[] args) throws IOException {
    // clearDirectory(IO_PATH + "/srt");
    String s = "/from/fs/a/a_1";
    String t = "/from/fs";
    System.out.println(s.lastIndexOf(t));
    System.out.println(s.substring(s.lastIndexOf(t)));
    System.out.println(foo(s, t));

    Function<Integer, Integer> mn = i -> i + 1;
    Function<Integer, Integer> mn1 = bind(Integer::sum, 1);
    System.out.println(Objects.equals(mn.apply(10), mn1.apply(10)));

    List<A> list = List.of(new A("1"), new A("2"), new A("3"));
    list.forEach(A::foo);
    System.out.println(list);
    A a = new A("hel");
    Runnable foo = a::foo;
    foo.run();
  }

  static <A, B, C> Function<B, C> bind(BiFunction<A, B, C> fn, A a) {
    return b -> fn.apply(a, b);
  }

  static <A, B, C> Function<A, Function<B, C>> curry(BiFunction<A, B, C> fn){
    return a -> b -> fn.apply(a, b);
  }

  public static String foo(String s, String t) {
    char[] sArr = s.toCharArray();
    char[] tArr = t.toCharArray();
    StringBuilder builder = new StringBuilder();
    for (int i = tArr.length; i < sArr.length; ++i) {
      builder.append(sArr[i]);
    }
    return builder.toString();
  }
  public static void clearDirectory(String path) throws IOException {
    Path dir = Paths.get(path); //path to the directory
    try (Stream<Path> stream = Files.walk(dir)) {
      stream // Traverse the file tree in depth-first order
          .sorted(reverseOrder())
          .forEach(p -> {
            try {
              System.out.println("Существующая директория была удалена: " + p);
              Files.delete(p);  //delete each file or directory
            } catch (IOException e) {
              throw new RuntimeException("Произошла ошибка при очистке директории: " + e.getMessage());
            }
          });
    }
  }


}
