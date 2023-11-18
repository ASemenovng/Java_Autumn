package org.example.spliterator;

import java.util.List;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Test {

  public static final int Max = 6;

  public static void main(String[] args) {
    System.out.println("Task 1");
    List<Integer> list = IntStream.rangeClosed(1, Max).boxed().toList();
    Spliterator<Integer> first = list.spliterator();
//    System.out.println("forEachRemaining: ");
//    first.forEachRemaining(System.out::println);

    Spliterator<Integer> second = first.trySplit();
    Spliterator<Integer> third = first.trySplit();

    System.out.println("trySplit: ");
    System.out.println("first: ");
    first.forEachRemaining(System.out::println);

    System.out.println("second: ");
    second.forEachRemaining(System.out::println);

    System.out.println("third: ");
    third.forEachRemaining(System.out::println);

    System.out.println("\nTask2");
    System.out.println(diff(Stream.of(10, 2, 9, 3, 5)).toList());


    List<Integer> list1 = IntStream.rangeClosed(1, Max).boxed().toList();
    Spliterator<Integer> first1 = list.spliterator();
    System.out.println(first1.tryAdvance(i -> System.out.println(i + 1)));
    System.out.println(first1.characteristics());
  }

  public static Stream<Integer> diff(Stream<Integer> stream) {
    return pairMap(stream, (a, b) -> b - a);
  }

  // Task 1
  // по заданному потоку создать поток пар из соседних значений исходного потока.
  // Так как общепринятого типа для представления пары значений в Java нет и возможных вариантов слишком много
  // (использовать массив из двух значений, список из двух значений, Map.Entry с одинаковым типом ключа и значения и т. д.),
  // мы отдадим это на откуп пользователю: пусть сам решает, как объединить два значения.
  public static <T, R> Stream<R> pairMap(Stream<T> stream, BiFunction<T, T, R> mapper) {
    return StreamSupport.stream(new PairSpliterator<>(mapper, stream.spliterator()),
        stream.isParallel()).onClose(stream::close);
  }
}
