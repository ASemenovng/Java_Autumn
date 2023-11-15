package stream;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {

  static final String PREFIX = "--";

  public static void main(String[] args) {
    IntStream.iterate(1, x -> x * 2)
        .limit(10)
        .sorted()
        .forEach(System.out::println);

//    IntStream.iterate(1, x -> x * 2)
//        .sorted()
//        .limit(10)
//        .forEach(System.out::println);

    List<String> strings = List.of("abc", "abcdef", "jdnckjsdncjks", "djsf");

    int count = 0;
    for (String s : strings) {
      if (s.length() > 5) {
        count++;
      }
    }
    System.out.println("count: " + count);

    long streamCount = strings.stream()
        .filter(s -> s.length() > 5)
        .count();
    System.out.println("streamCount: " + streamCount);

    //
    int n0 = 0;
    int n1 = 1;
    int n2;
    System.out.println(n0 + " " + n1);
    for (int i = 3; i < 10; ++i) {
      n2 = n0 + n1;
      System.out.println(n2);
      n0 = n1;
      n1 = n2;
    }

    System.out.println(fib(5));

    Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
        .limit(10)
        .mapToInt(arr -> arr[0])
        .forEach(System.out::println);

    System.out.println("\n");
    System.out.println("stream mult");
    Stream.of(1, 3, 5, 7, 9)
        .reduce((x, y) -> x * y)
        .ifPresent(System.out::println);

    int fact = IntStream.rangeClosed(1, 5)
        .reduce((x, y) -> x * y)
        .getAsInt();
    System.out.println("stream fact");
    System.out.println(fact);

    Stream<String> words = Stream.of("мама", "мыла", "раму");
    Optional<String> sent = words.reduce((x, y) -> x.concat(" ").concat(y));
    System.out.println(sent.get());

    //
    System.out.println("\ncount");
    long c = Stream.of(1, 2, 3, 4, 5)
        .skip(1)
        .peek(System.out::println)
        .count();
    System.out.println("c: " + c);

    //
    String[] commands = {"a", "b", "c"};
    System.out.println("\nfindFirst");

    Stream.of(commands)
        .filter(s -> s.startsWith(PREFIX))
        .findFirst()
        .map(s -> s.substring(PREFIX.length()))
        .ifPresent(System.out::println);

    Stream.of(commands)
        .filter(s -> s.startsWith(PREFIX))
        .findFirst()
        .map(s -> s.substring(PREFIX.length()))
        .ifPresentOrElse(System.out::println, () -> System.out.println("nothing"));

    boolean hasParams = Stream.of(commands)
        .anyMatch(s -> s.startsWith(PREFIX));

    System.out.println("\nshort circuit");
    List<List<String>> listList =
        List.of(List.of("foo", "bar", "baz"),
            List.of("Java", "Kotlin", "C++"),
            List.of("Hello", "I'm", "Mike"));
    System.out.println(listList.stream()
        .flatMap(List::stream)
        .peek(System.out::println)
        .anyMatch("Java"::equals));

    System.out.println("\nreduce");
    System.out.println(fact(5));

    IntStream.of(1, 2, 3, 4, 5).reduce(0, Integer::sum);
    Stream.of(1, 2, 3, 4, 5).mapToInt(x -> x).sum();

    IntStream.of(1, 2, 3, 4, 5).reduce(Integer::max);
    IntStream.of(1, 2, 3, 4, 5).max();

    Stream.of("a", "b").reduce("", String::concat);
    System.out.println(Stream.of("a", "b").collect(Collectors.joining()));

    System.out.println("collect");
    System.out.println(strByLength(List.of("a", "bb", "c", "dd" ,"eee")));
    System.out.println(strByLength(List.of("a", "bb", "c", "dd" ,"eee"), ":"));
    System.out.println(strByLengthAndFirstLetter(List.of("a", "bb", "ab", "c", "dd" ,"eee")));
  }

  static Map<Integer, List<String>> strByLength(List<String> list) {
    return list.stream().collect(Collectors.groupingBy(String::length));
  }

  static Map<Integer, String> strByLength(List<String> list, String separator) {
    return list.stream().collect(Collectors.groupingBy(String::length, Collectors.joining(separator)));
  }

  static Map<Integer, Map<Character, List<String>>> strByLengthAndFirstLetter(List<String> list) {
    return list.stream().collect(Collectors.groupingBy(String::length,
        Collectors.groupingBy(s -> s.charAt(0))));
  }

  public List<String> process(List<String> list) {
    return list.stream()
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .collect(Collectors.toList());
  }

  static BigInteger fact(int n) {
    return IntStream.rangeClosed(1, n)
        .mapToObj(BigInteger::valueOf)
        .reduce(BigInteger.ONE, BigInteger::multiply);
  }

  public static int fib(int n) {
    if (n <= 1) {
      return 0;
    } else if (n == 2) {
      return 1;
    } else {
      return fib(n - 1) + fib(n - 2);
    }
  }

}
