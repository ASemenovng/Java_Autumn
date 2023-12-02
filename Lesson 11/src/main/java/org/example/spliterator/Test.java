package org.example.spliterator;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Test {

  public static final int Max = 6;

  public static void main(String[] args) {
    //Если свой сплитератор кажется не к месту, лучше использовать Spliterators.spliterator()

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

    //
    System.out.println("Task 2");
    Date date = new Date();
    MessageRecord rec1 = new MessageRecord(1, date, "Hello");
    MessageRecord rec2 = new MessageRecord(1, date, "World");
    MessageRecord rec3 = new MessageRecord(1, date, "Java");
    MessageRecord rec4 = new MessageRecord(1, date, "The");
    MessageRecord rec5 = new MessageRecord(1, date, "Best");
    MessageRecord rec6 = new MessageRecord(1, date, "OMG");

    Messages messages = new Messages();
    messages.addMessage(rec1, rec2, rec3, rec4, rec5, rec6);

    Consumer<MessageRecord> print = System.out::println;

    Spliterator<MessageRecord> split = messages.getSpliterator();
    Spliterator<MessageRecord> newSplit = split.trySplit();

    System.out.println("split:");
    while (split.tryAdvance(print)) {
      System.out.println(split.estimateSize());
    }
    System.out.println("newSplit");
    while (newSplit.tryAdvance(print)) {
      System.out.println(newSplit.estimateSize());
    }

    // messages.stream()
//
    System.out.println("Task 3");
    diff(Stream.of(10, 5, 17, 3, 4)).forEach(System.out::println);
  }

  public static <T> Stream<Map.Entry<T, T>> pairs(Stream<T> stream) {
    return pairMap(stream, SimpleImmutableEntry::new);
  }

  public static Stream<Integer> diff(Stream<Integer> stream) {
    return pairMap(stream, (a, b) -> b - a);
  }

  // Task 4
  // по заданному потоку создать поток пар из соседних значений исходного потока.
  // Так как общепринятого типа для представления пары значений в Java нет и возможных вариантов слишком много
  // (использовать массив из двух значений, список из двух значений, Map.Entry с одинаковым типом ключа и значения и т.д.),
  // мы отдадим это на откуп пользователю: пусть сам решает, как объединить два значения.
  public static <T, R> Stream<R> pairMap(Stream<T> stream, BiFunction<T, T, R> mapper) {
    return StreamSupport.stream(new PairSpliterator<>(mapper, stream.spliterator()),
        stream.isParallel()).onClose(stream::close);
  }
}
