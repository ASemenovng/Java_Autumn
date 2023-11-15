package utils.books;

import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class Test {

  public static void main(String[] args) {
    System.out.println(Stream.of(1, 2, 3, 4, 5)
        .collect(partitioningBy(x -> x > 0, toSet())));

    System.out.println(Stream.of(1, 2, 3, 4, 5)
        .collect(groupingBy(x -> x > 0, toSet())));
  }



  static Map<String, Integer> foo(List<Book> list) {
    return list.stream().collect(toMap(Book::getCategory, Book::price,
        BinaryOperator.minBy(naturalOrder())));
  }

  static Map<String, Integer> getLowerBookPrice(List<Book> list) {
    return list.stream().collect(groupingBy(Book::getCategory,
        collectingAndThen(
          mapping(Book::price,
            minBy(naturalOrder())), Optional::get)));
  }

}
