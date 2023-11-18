package org.example.spliterator;

import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Test {

  public static void main(String[] args) {
    System.out.println(diff(Stream.of(10, 2, 9, 3, 5)).toList());
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
