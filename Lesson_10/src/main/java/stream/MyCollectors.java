package stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MyCollectors {

  public static void main(String[] args) {
    Stream.of("one", "two", "three", "four", "five", "six", "seven")
        .collect(minMax(comparingInt(String::length),
            (min, max) -> min + " : " + max))
        .ifPresent(System.out::println);

  }

  static <T, R> Collector<T, ?, Optional<R>> minMax1(Comparator<? super  T> cmp,
      BiFunction<? super T, ? super T,? extends R > finisher) {
    return Collectors.teeing(
        minBy(cmp),
        maxBy(cmp),
        (o1, o2) -> o1.map(t -> finisher.apply(t, o2.get())));
  }

  static <T, R> Collector<T, ?, Optional<R>> minMax(Comparator<? super  T> cmp,
      BiFunction<? super T, ? super T,? extends R > finisher) {

    class Acc {
      T min;
      T max;
      boolean present;

      void add (T t) {
        if (present) {
          if (cmp.compare(t, min) < 0) {
            min = t;
          }
          if (cmp.compare(t, max) > 0) {
            max = t;
          }
        } else {
          min = max = t;
          present = true;
        }
      }

      Acc combine(Acc other) {
        if (!other.present) return this;
        if (!present) return other;
        if (cmp.compare(other.min, min) < 0) min = other.min;
        if (cmp.compare(other.max, max) > 0) max = other.max;
        return this;
      }
    }

    return Collector.of(
        Acc::new,
        Acc::add,
        Acc::combine,
        acc -> acc.present ? Optional.of(finisher.apply(acc.min, acc.max)) : Optional.empty());

  }

  static <T> Collector<T, ?, List<T>> toList() {
    return Collector.of(ArrayList::new, List::add,
        (a1, a2) -> {
          a1.addAll(a2);
          return a1;
        });
  }



}
